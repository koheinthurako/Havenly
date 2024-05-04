  package com.Havenly.Backend.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;




@Component
public class EmailUtil {
	
	@Autowired
	 JavaMailSender javaMailSender;
	
	public void setMailSender(JavaMailSender mailSender) {  
        this.javaMailSender = mailSender;  
    }  
	
	
	public void sendSetPasswordEmail(String email)throws MessagingException{
//		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
//		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//		
		SimpleMailMessage msg=new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject("Set Password");
		msg.setText("""
				<div>
				<a href="http://localhost:8083/setpassword?email=%s">click to set password</a>
				</div>
				""".formatted(email) );
		
		javaMailSender.send(msg);

		
	}

}
