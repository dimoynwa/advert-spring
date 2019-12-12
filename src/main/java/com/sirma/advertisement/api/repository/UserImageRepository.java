package com.sirma.advertisement.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sirma.advertisement.api.entity.UserImage;

public interface UserImageRepository extends JpaRepository<UserImage, String> {

	Optional<UserImage> findByUserId(Long userId);
	
}
