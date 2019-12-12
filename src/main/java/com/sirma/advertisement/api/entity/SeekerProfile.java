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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sirma.advertisement.api.entity.audit.UserDateAudit;

@Entity
@Table(name="seeker_profile")
public class SeekerProfile extends UserDateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
	private User seeker;
	
	@Column(name="salary")
	private Double salary;
	
	@Column(name="currency")
	private String currency;

	@OneToMany(mappedBy = "seekerProfile", cascade = CascadeType.ALL)
	private Set<EducationDetail> education;
	
	@OneToMany(mappedBy = "seekerProfile", cascade = CascadeType.ALL)
	private Set<ExperienceDetail> experience;
	
	@OneToMany(mappedBy = "seekerProfile", cascade = CascadeType.ALL)
	private Set<SeekerSkill> skills;
	
	public SeekerProfile() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getSeeker() {
		return seeker;
	}

	public void setSeeker(User seeker) {
		this.seeker = seeker;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Set<EducationDetail> getEducation() {
		return education;
	}

	public void setEducation(Set<EducationDetail> education) {
		this.education = education;
	}

	public Set<ExperienceDetail> getExperience() {
		return experience;
	}

	public void setExperience(Set<ExperienceDetail> experience) {
		this.experience = experience;
	}

	public Set<SeekerSkill> getSkills() {
		return skills;
	}

	public void setSkills(Set<SeekerSkill> skills) {
		this.skills = skills;
	}

}
