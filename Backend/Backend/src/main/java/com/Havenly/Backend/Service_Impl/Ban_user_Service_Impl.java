package com.Havenly.Backend.Service_Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.Entity.Ban_user;
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

}
