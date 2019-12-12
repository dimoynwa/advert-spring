package com.sirma.advertisement.api.model;

import java.io.Serializable;
import java.util.List;

public class CreateJobPostRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String companyName;
	private String title;
	private String shortDescription;
	private String description;
	private Boolean active;
	private JobTypeModel jobType;
	private List<SkillLevelModel> skills;
	private Boolean imageChanged;

	public CreateJobPostRequest() {
		super();
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public JobTypeModel getJobType() {
		return jobType;
	}

	public void setJobType(JobTypeModel jobType) {
		this.jobType = jobType;
	}

	public List<SkillLevelModel> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillLevelModel> skills) {
		this.skills = skills;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public Boolean getImageChanged() {
		return imageChanged;
	}

	public void setImageChanged(Boolean imageChanged) {
		this.imageChanged = imageChanged;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
