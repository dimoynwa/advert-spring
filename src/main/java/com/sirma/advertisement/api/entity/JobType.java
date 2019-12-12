package com.sirma.advertisement.api.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sirma.advertisement.api.entity.audit.DateAudit;
import com.sirma.advertisement.api.model.JobTypeModel;

@Entity
@Table(name = "job_type")
public class JobType extends DateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "jobType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<JobPost> jobPosts;

	public JobType() {
		super();
	}

	public JobType(JobTypeModel model) {
		super();
		this.id = model.getId();
		this.name = model.getName();
	}
	
	public JobType(String name) {
		super();
		this.name = name;
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

	public Set<JobPost> getJobPosts() {
		return jobPosts;
	}

	public void setJobPosts(Set<JobPost> jobPosts) {
		this.jobPosts = jobPosts;
	}

}
