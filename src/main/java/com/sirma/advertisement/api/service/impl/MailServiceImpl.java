package com.sirma.advertisement.api.service.impl;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sirma.advertisement.api.entity.Mail;
import com.sirma.advertisement.api.entity.User;
import com.sirma.advertisement.api.service.MailService;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail(Mail mail) throws MailException, MessagingException {
		
		MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
        		MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(mail.getReceiver().getEmail());
        helper.setText(mail.getText(), true);
        helper.setSubject(mail.getSubject());

        javaMailSender.send(message);
	}

	@Override
	public void sendEmailWithAttachment(User user) throws MailException, MessagingException {
		// TODO Auto-generated method stub
		
	}

}
