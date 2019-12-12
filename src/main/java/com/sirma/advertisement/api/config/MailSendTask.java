package com.sirma.advertisement.api.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sirma.advertisement.api.entity.Mail;
import com.sirma.advertisement.api.repository.MailRepository;
import com.sirma.advertisement.api.service.MailService;

public class MailSendTask implements Runnable {

	private static final Logger logger = LogManager.getLogger(MailSheduledSender.class);
	
	private Mail mail;
	private MailService mailService;
	private MailRepository mailRepository;
	
	public MailSendTask(Mail mail, MailService mailService, MailRepository mailRepository) {
		super();
		this.mail = mail;
		this.mailService = mailService;
		this.mailRepository = mailRepository;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public MailRepository getMailRepository() {
		return mailRepository;
	}

	public void setMailRepository(MailRepository mailRepository) {
		this.mailRepository = mailRepository;
	}

	@Override
	public void run() {
		try {
			mailService.sendEmail(mail);
			
			mail.setSuccess(true);
			mail.setErrorMessage(null);
			
			mailRepository.save(mail);
		} catch(Exception e) {
			logger.error("Error executing mail task : " + mail, e);
			mail.setSuccess(false);
			mail.setErrorMessage(e.getLocalizedMessage());
			mail.setRetryCount(mail.getRetryCount() + 1);
			
			mailRepository.save(mail);
		}
	}
	
}
