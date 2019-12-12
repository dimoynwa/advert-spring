package com.sirma.advertisement.api.model;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import com.sirma.advertisement.api.config.ApiConfig;
import com.sirma.advertisement.api.entity.JobPost;

public class JobPostInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private UserInfo poster;
	private String companyName;
	private String title;
	private String shortDescription;
	private String description;
	private Boolean active;
	private JobTypeModel jobType;
	private Set<SkillLevelModel> skills;
	private String created;
	private Boolean imageChanged;
	private String image;
	
	public JobPostInfo() {
		super();
	}

	public JobPostInfo(JobPost entity) {
		super();
		this.id = entity.getId();
		this.poster = new UserInfo(entity.getPoster());
		this.companyName = entity.getCompanyName();
		this.title = entity.getTitle();
		this.shortDescription = entity.getShortDescription();
		this.description = entity.getDescription();
		this.active = entity.getActive();
		this.jobType = new JobTypeModel(entity.getJobType());
		this.image = entity.getAvatar();
		this.skills = entity.getSkills().stream().map(s -> {
			return new SkillLevelModel(s);
		}).collect(Collectors.toSet());
		this.created = ApiConfig.DATE_FORMATTER.format(entity.getCreatedAt());
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserInfo getPoster() {
		return poster;
	}

	public void setPoster(UserInfo poster) {
		this.poster = poster;
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

	public Set<SkillLevelModel> getSkills() {
		return skills;
	}

	public void setSkills(Set<SkillLevelModel> skills) {
		this.skills = skills;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public Boolean getImageChanged() {
		return imageChanged;
	}

	public void setImageChanged(Boolean imageChanged) {
		this.imageChanged = imageChanged;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
