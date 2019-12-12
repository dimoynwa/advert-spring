package com.sirma.advertisement.api.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sirma.advertisement.api.entity.audit.UserDateAudit;
import com.sirma.advertisement.api.model.CreateJobPostRequest;

@NamedEntityGraphs(value = { @NamedEntityGraph(name = "jop_post_full_info", attributeNodes = {
		@NamedAttributeNode(value = "poster", subgraph = "userRolesSubGraph"), @NamedAttributeNode(value = "jobType"),
		@NamedAttributeNode(value = "skills", subgraph = "skillSubGraph") }, subgraphs = {
				@NamedSubgraph(name = "skillSubGraph", attributeNodes = { @NamedAttributeNode(value = "skill") }),
				@NamedSubgraph(name = "userRolesSubGraph", attributeNodes = {
						@NamedAttributeNode(value = "roles") }) }) })

@Entity
@Table(name = "job_post")
public class JobPost extends UserDateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "poster_id")
	private User poster;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "title")
	private String title;
	
	@Column(name = "short_description", columnDefinition = "text")
	private String shortDescription;

	@Column(name = "description", columnDefinition = "text")
	private String description;

	@Column(name = "active")
	private Boolean active;

	@ManyToOne
	@JoinColumn(name = "job_type_id")
	private JobType jobType;

	@OneToMany(mappedBy = "jobPost", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<JobPostSkill> skills;

	@Column(name = "avatar")
	private String avatar;
	
	public JobPost() {
		super();
	}

	public JobPost(CreateJobPostRequest request) {
		super();
		this.id = request.getId();
		this.companyName = request.getCompanyName();
		this.shortDescription = request.getShortDescription();
		this.description = request.getDescription();
		this.shortDescription = request.getShortDescription();
		this.title = request.getTitle();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getPoster() {
		return poster;
	}

	public void setPoster(User poster) {
		this.poster = poster;
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

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public Set<JobPostSkill> getSkills() {
		return skills;
	}

	public void setSkills(Set<JobPostSkill> skills) {
		if (this.skills == null) {
			this.skills = skills;
		} else {
			this.skills.retainAll(skills);
			this.skills.addAll(skills);
		}
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
