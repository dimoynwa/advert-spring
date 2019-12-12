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
@Table(name="education_detail")
public class EducationDetail extends UserDateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "seeker_id")
	private SeekerProfile seekerProfile;
	
	@Column(name="institute_university_name")
	private String instituteUniversityName;
	
	@Column(name="major")
	private String major;
	
	@Column(name="certificate_degree_name")
	private String certificateDegreeName;
	
	@Column(name="start_date", nullable=false)
	private Date startDate;
	
	@Column(name="endDate")
	private Date endDate;
	
	@Column(name="percentage")
	private Double percentage;
	
	@Column(name="cgpa")
	private Double cgpa;

	public EducationDetail() {
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

	public String getInstituteUniversityName() {
		return instituteUniversityName;
	}

	public void setInstituteUniversityName(String instituteUniversityName) {
		this.instituteUniversityName = instituteUniversityName;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getCertificateDegreeName() {
		return certificateDegreeName;
	}

	public void setCertificateDegreeName(String certificateDegreeName) {
		this.certificateDegreeName = certificateDegreeName;
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

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public Double getCgpa() {
		return cgpa;
	}

	public void setCgpa(Double cgpa) {
		this.cgpa = cgpa;
	}
}
