package com.sirma.advertisement.api.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sirma.advertisement.api.config.MailSheduledSender;
import com.sirma.advertisement.api.entity.Mail;
import com.sirma.advertisement.api.entity.PasswordResetToken;
import com.sirma.advertisement.api.entity.User;
import com.sirma.advertisement.api.exception.ResourceNotFoundException;
import com.sirma.advertisement.api.model.ForgotPasswordRequest;
import com.sirma.advertisement.api.model.ResetPasswordRequest;
import com.sirma.advertisement.api.model.UserInfo;
import com.sirma.advertisement.api.repository.MailRepository;
import com.sirma.advertisement.api.repository.PasswordResetTokenRepository;
import com.sirma.advertisement.api.repository.UserRepository;
import com.sirma.advertisement.api.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MailRepository mailRepository;
	
	@Autowired
	private MailSheduledSender mailScheduledSender;
	
	@Override
	public String forgotPasswordGenerateToken(ForgotPasswordRequest request, HttpServletRequest httpRequest) {
		Optional<User> user = userRepository.findByUserNameOrEmail(null, request.getEmail());
		
		if(!user.isPresent()) {
			throw ResourceNotFoundException.userByEmailNotFound(request.getEmail());
		}
		
		PasswordResetToken token = new PasswordResetToken();
		token.setExpiryDate(new Date());
		token.setExpiryDate(30);
		token.setUser(user.get());
		token.setToken(UUID.randomUUID().toString());
		
		token = passwordResetTokenRepository.save(token);
        
        Mail mail = mailScheduledSender.resetPasswordMail(user.get(), token);
        
        mailRepository.save(mail);
		
		return token.getToken();
	}

	@Override
	public UserInfo resetPassword(ResetPasswordRequest request) {
		if(!request.getPassword().equals(request.getConfirmPassword())) {
			//TODO : throw exception
		}
		Optional<PasswordResetToken> resetToken = passwordResetTokenRepository.findByToken(request.getResetToken());
		if(!resetToken.isPresent()) {
			throw ResourceNotFoundException.passwordResetLinkNotFound(request.getResetToken());
		}
		
		User user = resetToken.get().getUser();
		
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		passwordResetTokenRepository.deleteById(resetToken.get().getId());
		
		return new UserInfo(userRepository.save(user));
	}

	@Override
	public UserInfo activateUser(String token) {
		if(token == null || "".equals(token)) {
			//TODO : throw exception
		}
		Optional<PasswordResetToken> resetToken = passwordResetTokenRepository.findByToken(token);
		if(!resetToken.isPresent()) {
			throw ResourceNotFoundException.passwordResetLinkNotFound(token);
		}
		User user = resetToken.get().getUser();
		user.setActive(true);
		
		user = userRepository.save(user);
		
		passwordResetTokenRepository.deleteById(resetToken.get().getId());
		
		return new UserInfo(user);
	}

}
