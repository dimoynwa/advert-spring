package com.sirma.advertisement.api.model;

import java.io.Serializable;

import com.sirma.advertisement.api.entity.Skill;

public class SkillModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	public SkillModel() {
		super();
	}

	public SkillModel(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public SkillModel(Skill entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
