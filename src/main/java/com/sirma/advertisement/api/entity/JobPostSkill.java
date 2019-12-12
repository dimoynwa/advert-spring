package com.sirma.advertisement.api.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sirma.advertisement.api.entity.audit.UserDateAudit;

@Entity
@Table(name="job_post_skill")
public class JobPostSkill extends UserDateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "job_post_id")
	private JobPost jobPost;
	
	@ManyToOne(targetEntity = Skill.class, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "skill_id")
	private Skill skill;
	
	@Enumerated(EnumType.STRING)
	@Column(name="level")
	private SkillLevel level;

	public JobPostSkill() {
		super();
	}

	public JobPostSkill(Long id, JobPost jobPost, Skill skill, SkillLevel level) {
		super();
		this.id = id;
		this.jobPost = jobPost;
		this.skill = skill;
		this.level = level;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JobPost getJobPost() {
		return jobPost;
	}

	public void setJobPost(JobPost jobPost) {
		this.jobPost = jobPost;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public SkillLevel getLevel() {
		return level;
	}

	public void setLevel(SkillLevel level) {
		this.level = level;
	}

}
