package com.sirma.advertisement.api.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.stream.Collectors;

import com.sirma.advertisement.api.config.ApiConfig;
import com.sirma.advertisement.api.entity.User;

public class UserInfo implements Serializable {

	public static DateFormat DATE_FORMAT = new SimpleDateFormat(ApiConfig.DATE_FORMAT);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private boolean active;
	private String avatar;
	private String created;
	private Set<String> roles;

	public UserInfo() {
		super();
	}
	
	public UserInfo(User user) {
		if (user != null) {
			this.id = user.getId();
			this.firstName = user.getFirstName();
			this.lastName = user.getLastName();
			this.userName = user.getUserName();
			this.email = user.getEmail();
			this.active = user.getActive();
			this.avatar = user.getAvatar();
			this.created = DATE_FORMAT.format(user.getCreatedAt());
			this.roles = user.getRoles().stream().map(r -> r.getRoleAsString()).collect(Collectors.toSet());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
