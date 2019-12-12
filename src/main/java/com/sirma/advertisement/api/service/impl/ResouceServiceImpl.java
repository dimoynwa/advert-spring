package com.sirma.advertisement.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirma.advertisement.api.entity.Role;
import com.sirma.advertisement.api.entity.Skill;
import com.sirma.advertisement.api.entity.SkillLevel;
import com.sirma.advertisement.api.model.SkillModel;
import com.sirma.advertisement.api.repository.RoleRepository;
import com.sirma.advertisement.api.repository.SkillRepository;
import com.sirma.advertisement.api.service.ResourcesService;

@Service
public class ResouceServiceImpl implements ResourcesService {

	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<SkillModel> getAllSkills() {
		List<Skill> skills = skillRepository.findAll();
		return skills.stream().map(p -> { return new SkillModel(p);}).collect(Collectors.toList());
	}

	@Override
	public List<String> getAllRoles() {
		List<Role> roles = roleRepository.findAll();
		return roles.stream().map(r -> {return r.getRoleAsString();}).collect(Collectors.toList());
	}

	@Override
	public List<String> getAllSkillLevels() {
		return Stream.of(SkillLevel.values()).map(l -> l.toString()).collect(Collectors.toList());
	}

}
