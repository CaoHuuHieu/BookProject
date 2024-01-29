package com.bookory.server.helper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@Component
public class EmailProvider {
	@Autowired
	JavaMailSender mailSender ;
	public void sendEmail(String emailTo, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("caohuuhieu12b8@gmail.com");
		message.setTo(emailTo);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}
}
