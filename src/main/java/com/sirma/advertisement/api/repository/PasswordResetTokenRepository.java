package com.sirma.advertisement.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sirma.advertisement.api.entity.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

	Optional<PasswordResetToken> findByToken(String token);
}
