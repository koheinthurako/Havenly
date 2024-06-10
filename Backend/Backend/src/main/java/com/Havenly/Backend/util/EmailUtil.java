package com.Havenly.Backend.util;

import com.Havenly.Backend.Repo.TokenRepo;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.stereotype.Component;

import java.time.Instant;




@Component
//@EnableJdbcHttpSession
public class EmailUtil {

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	TokenRepo repo;




	public void sendSetPasswordEmail(String email)throws MessagingException{
//		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
//		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

		 String token = TokenUtil.generateToken();
		    Instant expiryTime = TokenUtil.getExpiryTime(3); 
		    
		    repo.savePasswordResetToken(email, token, expiryTime);
		    
		    String resetUrl = "http://localhost:8080/akmakmset?token=" + token;
		    
         

		SimpleMailMessage msg=new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject("Set Password");
		msg.setText("Click the following link to reset your password.You can reset your password within 3 minutes..Note ::: you can't reset the password once you updated the password or if it goes over 3 minutes.. " + resetUrl );
		System.out.println("akmakm");
		javaMailSender.send(msg);


	}

}