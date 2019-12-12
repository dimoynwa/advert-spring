package com.sirma.advertisement.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sirma.advertisement.api.entity.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Long> {
	
	@EntityGraph(value="jop_post_full_info", type=EntityGraphType.FETCH)
	Page<JobPost> findByPosterId(Long posterId, Pageable pageable);
	
	@EntityGraph(value="jop_post_full_info", type=EntityGraphType.FETCH)
	Page<JobPost> findAll(Pageable pageble);
}
