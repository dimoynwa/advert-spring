package com.sirma.advertisement.api.config;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sirma.advertisement.api.security.UserPrincipal;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

	@Bean
	public AuditorAware<Long> auditorProvider() {
		return new UsernameAuditorAware();
	}
}

class UsernameAuditorAware implements AuditorAware<Long> {
	
	private static final Logger logger = LogManager.getLogger(UsernameAuditorAware.class);
	
	@Override
	public Optional<Long> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		logger.debug("Authentication found");
		if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() instanceof String) {
			return Optional.ofNullable(null);
		}
		return Optional.ofNullable(((UserPrincipal)authentication.getPrincipal()).getId());
	}
}