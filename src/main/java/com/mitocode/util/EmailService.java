package com.mitocode.util;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;//en Spring Boot 1.5 por defecto es 4, en Spring Boot 2 se debe cambiar a 5

@Component
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	public void sendEmail(Mail mail) {
		try {
			//configuración del correo
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			Context context = new Context(); //ojo importar de import org.thymeleaf.context.Context; variable de contexto de thymeleaf, la cual contiene los valores pasados a ${user} y ${resetUrl} en la plantilla 
			context.setVariables(mail.getModel());
			
			String html = templateEngine.process("email/email-template", context);
			
			helper.setTo(mail.getTo());
			helper.setText(html, true);
			helper.setSubject(mail.getSubject());
			helper.setFrom(mail.getFrom());
			
			emailSender.send(message);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
