package com.sirma.advertisement.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sirma.advertisement.api.entity.InformationFile;

public interface InformationFileRepository extends JpaRepository<InformationFile, String> {
	void deleteByJobPostIdAndAvatar(Long jobPostId, Boolean avatar);
}
