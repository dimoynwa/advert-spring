package com.sirma.advertisement.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sirma.advertisement.api.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	@EntityGraph(value="user_with_roles", type=EntityGraphType.FETCH)
	Page<User> findAll(Pageable pageable);
	
	@EntityGraph(value="user_with_roles", type=EntityGraphType.FETCH)
	Optional<User> findById(Long id);
	
	@EntityGraph(value="user_with_roles", type=EntityGraphType.FETCH)
	Optional<User> findByUserNameOrEmail(String userName, String email);
	
	Boolean existsByUserName(String userName);
	
	Boolean existsByEmail(String email);
}
