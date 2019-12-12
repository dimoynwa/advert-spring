package com.sirma.advertisement.api.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class ResetPasswordRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private String password;
	@NotEmpty
	private String confirmPassword;
	@NotEmpty
	private String resetToken;

	public ResetPasswordRequest() {
		super();
	}

	public ResetPasswordRequest(String password, String confirmPassword, String resetToken) {
		super();
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.resetToken = resetToken;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

}
