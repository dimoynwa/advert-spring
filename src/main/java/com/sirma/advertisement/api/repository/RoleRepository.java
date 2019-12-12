package com.sirma.advertisement.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sirma.advertisement.api.entity.Role;
import com.sirma.advertisement.api.entity.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByRoleName(RoleName roleName);
	
}
