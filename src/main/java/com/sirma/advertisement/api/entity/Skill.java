package com.sirma.advertisement.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sirma.advertisement.api.entity.audit.DateAudit;
import com.sirma.advertisement.api.model.SkillModel;

@Entity
@Table(name="skill")
public class Skill extends DateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name="name", unique = true)
	private String name;

	public Skill() {
		super();
	}

	public Skill(SkillModel model) {
		this.id = model.getId();
		this.name = model.getName();
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
