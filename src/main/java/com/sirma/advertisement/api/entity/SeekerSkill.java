package com.sirma.advertisement.api.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sirma.advertisement.api.entity.audit.UserDateAudit;

@Entity
@Table(name="seeker_skill")
public class SeekerSkill extends UserDateAudit {

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
	
	@OneToOne(targetEntity = Skill.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "skill_id")
	private Skill skill;
	
	@Enumerated(EnumType.STRING)
	@Column(name="level")
	private SkillLevel level;

	public SeekerSkill() {
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
