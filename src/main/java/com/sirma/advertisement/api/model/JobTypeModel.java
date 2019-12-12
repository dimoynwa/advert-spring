package com.sirma.advertisement.api.model;

import java.io.Serializable;

import com.sirma.advertisement.api.entity.JobType;

public class JobTypeModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	public JobTypeModel() {
		super();
	}

	public JobTypeModel(JobType entity) {
		super();
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
