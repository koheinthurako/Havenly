package com.Havenly.Backend.Service_Impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Havenly.Backend.Service.Reg_user_Service;
import com.Havenly.Backend.DTO.Reg_user_DD;
import com.Havenly.Backend.DTO.Reg_user_DTO;
import com.Havenly.Backend.Entity.Ban_user;
import com.Havenly.Backend.Entity.Reg_user;
import com.Havenly.Backend.Repo.Ban_user_Repo;
import com.Havenly.Backend.Repo.Reg_user_Repo;
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
	
	@Autowired
	Ban_user_Repo banRepo;
	
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
		Ban_user banUser=banRepo.findByEmail(user.getEmail());
		if(banUser==null) {
			Reg_user user1=regRepo.save(user);
			Reg_user_DTO user2=user_dto.covertToObject(user1);
			return user2;

				}
		
		
		
		return null;
	}
	@Override
	public Reg_user_DD Login(String gmail, String password) {
		Reg_user user = regRepo.findByEmail(gmail);
		
		if (user == null || !pwencoder.matches(password,user.getPassword())) {
			return null;
		}
//		if (!pwencoder.matches(password, user.getPassword())) {
//			return null;
//		}
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
		String encodedNewPassword = pwencoder.encode(new_password);
		user.setPassword(encodedNewPassword);
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

	@Override
	public String forgotPassword(String email) {
		// TODO Auto-generated method stub
	Reg_user user=	regRepo.findByEmail(email);
	if(user!=null) {
			try {
				emailUtil.sendSetPasswordEmail(email);
				
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Unable to send set password email please try again");
			}
				
				return "Please check your email to set new password to your account";
			}
	return "failed to send msg";
	
	}

	
	@Override
	public String setPassword(String email, String newPassword) {
		// TODO Auto-generated method stub
		Reg_user user=regRepo.findByEmail(email);
		String encodedNewPassword = pwencoder.encode(newPassword);
		user.setPassword(encodedNewPassword);
		regRepo.save(user);
		return "New password is set succeessfully.";
	}
	
	
	
	

}
