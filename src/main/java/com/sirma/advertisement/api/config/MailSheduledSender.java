package com.sirma.advertisement.api.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.sirma.advertisement.api.entity.Mail;
import com.sirma.advertisement.api.entity.PasswordResetToken;
import com.sirma.advertisement.api.entity.User;
import com.sirma.advertisement.api.repository.MailRepository;
import com.sirma.advertisement.api.service.MailService;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MailSheduledSender {
	
	private static final Logger logger = LogManager.getLogger(MailSheduledSender.class);
	
	@Autowired
	private MailService mailService;

	@Autowired
	private MailRepository mailRepository;
	
	@Autowired
    private SpringTemplateEngine templateEngine;
	
	@Value("${mail.job.threads}")
	private Integer mailJobThreads;
	
	private ExecutorService executor = Executors.newFixedThreadPool(4);
	
	@Scheduled(fixedRateString = "${mail.job.delay}")
	public void sendMails() {
		logger.debug("Into sendMails.");
		Pageable pageble =  PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt");
		List<Mail> mails = mailRepository.findBySuccess(false, pageble);
		
		logger.debug("Found " + (mails != null ? mails.size() : 0) + " mails for sending.");
		
		for(Mail mail : mails) {
			executor.submit(new MailSendTask(mail, mailService, mailRepository));
		}
		
		logger.debug((mails != null ? mails.size() : 0) + " processed.");
	}
	
	public Mail resetPasswordMail(User receiver, PasswordResetToken token) {
		String template = "email-forgot-password";
		Map<String, Object> model = new HashMap<>();
		model.put("token", token);
        model.put("user", receiver);
        model.put("signature", "http://localhost:3000.com");
        String url = "http://localhost:3000";
        model.put("resetUrl", url + "/resetPassword/?token=" + token.getToken());
        
        Context context = new Context();
    	context.setVariables(model);
    	String html = templateEngine.process(template, context);
    	
    	return new Mail(null, template, html, "Reset password", receiver, false, null, 0);
	}
	
	public Mail activateUserMail(User receiver, PasswordResetToken token) {
		String template = "activate-user";
		Map<String, Object> model = new HashMap<>();
		model.put("token", token);
        model.put("user", receiver);
        model.put("signature", "http://localhost:3000.com");
        String url = "http://localhost:3000";
        model.put("activateUrl", url + "/activateUser/?token=" + token.getToken());
        
        Context context = new Context();
    	context.setVariables(model);
    	String html = templateEngine.process(template, context);
    	
    	return new Mail(null, template, html, "Activate user", receiver, false, null, 0);
	}
	
	@PreDestroy
	public void cleanUp() throws Exception {
	  System.out.println("MailSheduledSender is destroy! Going to close threads.");
	  executor.shutdown();
	  System.out.println("MailSheduledSender is destroy! Threads closed.");
	}
}
