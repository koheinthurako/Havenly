package com.Havenly.Backend.Service_Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.Entity.Ban_user;
import com.Havenly.Backend.Entity.Reg_user;
import com.Havenly.Backend.Repo.Ban_user_Repo;
import com.Havenly.Backend.Service.Ban_user_Service;

@Configuration
public class Ban_user_Service_Impl implements Ban_user_Service{
	
	@Autowired
	Ban_user_Repo banRepo;

	@Override
	public Ban_user save(Ban_user user) {
		// TODO Auto-generated method stub
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

	

	

}
