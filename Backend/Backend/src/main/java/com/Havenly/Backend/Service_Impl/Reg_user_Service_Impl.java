package com.Havenly.Backend.Service_Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Havenly.Backend.DTO.Reg_user_DD;
import com.Havenly.Backend.DTO.Reg_user_DTO;
//import com.Havenly.Backend.Entity.PasswordResetToken;
import com.Havenly.Backend.Entity.Reg_user;
import com.Havenly.Backend.Repo.Reg_user_Repo;
//import com.Havenly.Backend.Repo.TokenRepository;
import com.Havenly.Backend.Service.Reg_user_Service;
import com.Havenly.Backend.util.EmailUtil;


import jakarta.mail.MessagingException;

@Configuration
public class Reg_user_Service_Impl implements Reg_user_Service{
	
	@Autowired
	Reg_user_Repo regRepo;
	
	@Autowired
	PasswordEncoder pwencoder;
	
	@Autowired
	JavaMailSender mail;
	
	
	@Autowired
	EmailUtil emailUtil;
	
//	@Autowired
//	TokenRepository tokenRepository;
	
	Reg_user_DTO user_dto= new Reg_user_DTO();
	Reg_user_DD user3= new Reg_user_DD();
	@Override
	public List<Reg_user_DTO> findAll() {
		List<Reg_user> users = regRepo.findAll();
		List<Reg_user_DTO> reg_user_list = new ArrayList<Reg_user_DTO>();
		reg_user_list.clear();
		for(Reg_user user : users) {
			reg_user_list.add(user_dto.covertToObject(user));
		}
		return reg_user_list;
	}
	@Override
	public Reg_user_DTO register(Reg_user_DTO dto) {
		Reg_user user=user_dto.covertToEntity(dto);
		user.setPassword(this.pwencoder.encode(user.getPassword()));
		Reg_user user1=regRepo.save(user);
		Reg_user_DTO user2=user_dto.covertToObject(user1);
		
		return user2;
	}
	@Override
	public Reg_user_DD Login(String gmail, String password) {
		Reg_user user = regRepo.findByEmail(gmail);
		
		if (user == null) {
			return null;
		}
		if (!pwencoder.matches(password, user.getPassword())) {
			return null;
		}
		Reg_user_DD user1=user3.covertToObject(user);
		return user1;
	}
	@Override
	public Reg_user_DD update(String name,String phone,String gmail) {
		
		Reg_user updateUser=regRepo.findByEmail(gmail);
		if (updateUser == null) {
			return null;
		}
		updateUser.setName(name);
		updateUser.setPhone(phone);
		Reg_user user2=regRepo.save(updateUser);
		Reg_user_DD user4=user3.covertToObject(user2);
		

		return user4;
	}
	@Override
	public Reg_user_DTO pwdUpdate(String username, String password , String new_password) {
		Reg_user user = regRepo.findByEmail(username);

		if (user == null) {
			return null;
		}
		if (!pwencoder.matches(password, user.getPassword())) {
			return null;
		}
		user.setPassword(this.pwencoder.encode(new_password));
		Reg_user user1=regRepo.save(user);
		Reg_user_DTO user2=user_dto.covertToObject(user1);
		
		return user2;
	}
	

	@Override
	public String deleteByEmail(String email) {
	
		Reg_user user1 = regRepo.findByEmail(email);
		if(user1!=null) {
			regRepo.deleteByEmail(email);
			return "Deleted!";
		}
		return "error";
	}
//	@Override
//	
//	public String sendEmail(Reg_user user) {
//		// TODO Auto-generated method stub
//		try {
//			String resetLink=generateResetToken(user);
//			
//			
//			SimpleMailMessage msg =new SimpleMailMessage();
//			msg.setFrom("aungkhantm33@gmail.com");
//			msg.setTo(user.getEmail());
//			
//			msg.setSubject("Welcome To My cahnnel");
//			msg.setText("Hello \n\n"+"Please click on this link to reset your Password : "+resetLink +". \n\n"+"Regards \n"+ "ABC");
//			mail.send(msg);
//			return "Success";
//		}catch(Exception e) {
//			e.printStackTrace();
//			return "error";
//		}		
//	}
//	@Override
//	public String generateResetToken(Reg_user user) {
//		// TODO Auto-generated method stub
//		UUID uuid=UUID.randomUUID();
//		LocalDateTime currentDateTime=LocalDateTime.now();
//		LocalDateTime expiryDateTime = currentDateTime.plusMinutes(30);
//		PasswordResetToken resetToken =new PasswordResetToken();
//		resetToken.setUser(user);
//		resetToken.setToken(uuid.toString());
//		resetToken.setExpiryDateTime(expiryDateTime);
//		resetToken.setUser(user);
//		PasswordResetToken token=tokenRepository.save(resetToken);
//		if(token != null) {
//			String endpointUrl="http://localhost:8083/resetPassword";
//			return endpointUrl + "/" + resetToken.getToken();
//		}
//		return "";
//	}
//	@Override
//	public boolean hasExpired(LocalDateTime expiryDateTime) {
//		// TODO Auto-generated method stub
//		LocalDateTime currentDateTime=LocalDateTime.now();
//		return expiryDateTime.isAfter(currentDateTime);
//	}
	@Override
	public String forgotPassword(String email) {
		// TODO Auto-generated method stub
	Reg_user user=	regRepo.findByEmail(email);
	try {
		emailUtil.sendSetPasswordEmail(user.getEmail());
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		throw new RuntimeException("Unable to send set password email please try again");
	}
		
		return "Please check your email to set new password to your account";
	}
	@Override
	public String setPassword(String email, String newPassword) {
		// TODO Auto-generated method stub
		Reg_user user=regRepo.findByEmail(email);
		user.setPassword(this.pwencoder.encode(newPassword));
		regRepo.save(user);
		return "New password is set succeessfully.";
	}
	
	
	
	

}
