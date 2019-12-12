package com.sirma.advertisement.api.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sirma.advertisement.api.entity.Role;
import com.sirma.advertisement.api.entity.RoleName;
import com.sirma.advertisement.api.entity.User;
import com.sirma.advertisement.api.repository.RoleRepository;
import com.sirma.advertisement.api.repository.UserRepository;

@Component
public class OnApplicationStartUp {

	private static final Logger logger = LogManager.getLogger(OnApplicationStartUp.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		logger.info("Start application. Insert initial roles and users");
		
		Role role1 = new Role(null, RoleName.ROLE_ADMIN, "Admin role");
		Role role2 = new Role(null, RoleName.ROLE_USER, "User role");
		Role role3 = new Role(null, RoleName.ROLE_ADVERTISER, " role");

		roleRepository.saveAll(Arrays.asList(role1, role2, role3));

		User admin = new User(null, "Dimo", "Drangov", "admin", "admin@gmail.com", passwordEncoder.encode("admin"));
		admin.setActive(true);
		Set<Role> roles = new HashSet<>();
		roles.addAll(Arrays.asList(role1, role2, role3));

		admin.setRoles(roles);
		
		userRepository.save(admin);
		
		User ad = new User(null, "Dimo", "Drangov", "dimo", "admin@gmail.com", passwordEncoder.encode("1234"));
		ad.setActive(true);
		Set<Role> rol1 = new HashSet<>();
		rol1.addAll(Arrays.asList(role3));

		ad.setRoles(rol1);
		
		userRepository.save(ad);
	}

}