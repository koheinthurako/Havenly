package com.Havenly.Backend.Service_Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.Havenly.Backend.Service.Ban_user_Service;
import com.Havenly.Backend.Entity.Ban_user;
import com.Havenly.Backend.Repo.Ban_user_Repo;

@Configuration
public class Ban_user_Service_Impl implements Ban_user_Service{
	
	@Autowired
	Ban_user_Repo banRepo;

	@Override
	public Ban_user save(Ban_user user) {
		// TODO Auto-generated method stub
		if (user.getBanTimestamp() == null) {
	        user.setBanTimestamp(LocalDateTime.now());
	    }
		return banRepo.save(user);
	}

	@Override
	public String deleteByEmail(String email) {
		// TODO Auto-generated method stub
		Ban_user user1 = banRepo.findByEmail(email);
		if(user1!=null) {
			banRepo.deleteByEmail(email);
			return "Deleted!";
		}
		return "error";
	}

	@Override
	public List<Ban_user> findAll() {
		// TODO Auto-generated method stub
		return banRepo.findAll();
		
		
	}

	@Override
	public boolean isEmailBanned(String email) {
		// TODO Auto-generated method stub
		 return banRepo.existsByEmail(email);
	}
	
	@Scheduled(fixedRate = 60000) // Runs every 1 minute
	public void removeExpiredBans() {
	    List<Ban_user> bannedUsers = banRepo.findAll();
	    LocalDateTime now = LocalDateTime.now();
	    for (Ban_user bannedUser : bannedUsers) {
	        LocalDateTime banTimestamp = bannedUser.getBanTimestamp();
	        if (banTimestamp != null && banTimestamp.plusMinutes(1).isBefore(now)) {
	            banRepo.delete(bannedUser);
	        }
	    }
	}

	

	

}
