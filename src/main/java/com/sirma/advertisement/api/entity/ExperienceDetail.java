package com.sirma.advertisement.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sirma.advertisement.api.entity.audit.UserDateAudit;

@Entity
@Table(name="experience_datail")
public class ExperienceDetail extends UserDateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name="seeker_id")
	private SeekerProfile seekerProfile;
	
	@Column(name="is_current_job")
	private Boolean isCurrentJob;
	
	@Column(name="start_date", nullable=false)
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="job_title")
	private String jobTitle;
	
	@Column(name="companyName")
	private String companyName;
	
	@Column(name="description", columnDefinition="text")
	private String description;

	public ExperienceDetail() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SeekerProfile getSeekerProfile() {
		return seekerProfile;
	}

	public void setSeekerProfile(SeekerProfile seekerProfile) {
		this.seekerProfile = seekerProfile;
	}

	public Boolean getIsCurrentJob() {
		return isCurrentJob;
	}

	public void setIsCurrentJob(Boolean isCurrentJob) {
		this.isCurrentJob = isCurrentJob;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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

}
