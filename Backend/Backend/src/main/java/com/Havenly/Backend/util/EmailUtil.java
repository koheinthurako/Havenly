  package com.Havenly.Backend.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;




@Component
public class EmailUtil {
	
	@Autowired
	 JavaMailSender javaMailSender;
	
	
	
	
	public void sendSetPasswordEmail(String email)throws MessagingException{
//		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
//		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
         System.out.println("hahahahhah");
		SimpleMailMessage msg=new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject("Set Password");
		msg.setText("""
				
				<a href="http://localhost:8080/akmakmset">click to set password</a>
				
				""".formatted(email) );
		System.out.println("akmakm");
		javaMailSender.send(msg);

		
	}

}
