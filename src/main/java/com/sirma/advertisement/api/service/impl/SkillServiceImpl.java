package com.sirma.advertisement.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirma.advertisement.api.entity.Skill;
import com.sirma.advertisement.api.model.SkillModel;
import com.sirma.advertisement.api.repository.SkillRepository;
import com.sirma.advertisement.api.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepository skillRepository;
	
	@Override
	public List<SkillModel> getAllSkills() {
		List<Skill> skills = skillRepository.findAll();
		return skills.stream().map(p -> { return new SkillModel(p);}).collect(Collectors.toList());
	}

}
