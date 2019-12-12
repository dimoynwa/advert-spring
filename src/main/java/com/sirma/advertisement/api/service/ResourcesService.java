package com.sirma.advertisement.api.service;

import java.util.List;

import com.sirma.advertisement.api.model.SkillModel;

public interface ResourcesService {

	List<SkillModel> getAllSkills();
	List<String> getAllRoles();
	List<String> getAllSkillLevels();
	
}
