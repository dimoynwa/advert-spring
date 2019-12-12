package com.sirma.advertisement.api.service;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;

import com.sirma.advertisement.api.entity.Mail;
import com.sirma.advertisement.api.entity.User;

public interface MailService {

	void sendEmail(Mail mail) throws MailException, MessagingException;
	void sendEmailWithAttachment(User user) throws MailException, MessagingException;
	
}
