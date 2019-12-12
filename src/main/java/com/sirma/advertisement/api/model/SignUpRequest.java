package com.sirma.advertisement.api.model;

import javax.validation.constraints.NotBlank;

public class SignUpRequest {

	@NotBlank(message="First name is required")
	private String firstName;
	
	@NotBlank(message="Last name is required")
	private String lastName;
	
	@NotBlank(message="Username is required")
	private String userName;
	
	@NotBlank(message="Email is required")
	private String email;
	
	@NotBlank(message="Password is required")
	private String password;
	
	@NotBlank(message="User role is required")
	private String role;

	public SignUpRequest() {
		super();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
