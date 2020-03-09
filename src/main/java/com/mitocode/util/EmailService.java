package com.mitocode.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring4.SpringTemplateEngine;//en Spring Boot 1.5 por defecto es 4, en Spring Boot 2 se debe cambiar a 5

@Component
public class EmailService {
	
	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	public void sendEmail(Mail mail) {
		
	}
	
}
