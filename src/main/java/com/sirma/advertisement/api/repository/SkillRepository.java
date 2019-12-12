package com.sirma.advertisement.api.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sirma.advertisement.api.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

	List<Skill> findByNameIn(Collection<String> names);
	
}
