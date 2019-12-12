package com.sirma.advertisement.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sirma.advertisement.api.entity.JobType;

public interface JobTypeRepository extends JpaRepository<JobType, Long> {

	Optional<JobType> findByName(String name);
	
}
