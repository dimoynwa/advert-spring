package com.sirma.advertisement.api.model;

import java.io.Serializable;

import com.sirma.advertisement.api.entity.JobPostSkill;

public class SkillLevelModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private SkillModel skill;
	private String skillLevel;

	public SkillLevelModel() {
		super();
	}

	public SkillLevelModel(JobPostSkill entity) {
		super();
		this.id = entity.getId();
		this.skill = new SkillModel(entity.getSkill());
		this.skillLevel = entity.getLevel().name();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SkillModel getSkill() {
		return skill;
	}

	public void setSkill(SkillModel skill) {
		this.skill = skill;
	}

	public String getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}

}
