package com.sirma.advertisement.api.service;

import javax.servlet.http.HttpServletRequest;

import com.sirma.advertisement.api.model.ForgotPasswordRequest;
import com.sirma.advertisement.api.model.ResetPasswordRequest;
import com.sirma.advertisement.api.model.UserInfo;

public interface AuthService {

	String forgotPasswordGenerateToken(ForgotPasswordRequest request, HttpServletRequest httpRequest);
	UserInfo resetPassword(ResetPasswordRequest request);
	UserInfo activateUser(String token);
}
