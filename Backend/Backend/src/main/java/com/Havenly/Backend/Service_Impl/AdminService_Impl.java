package com.Havenly.Backend.Service_Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.DTO.Reg_user_DD;
import com.Havenly.Backend.Entity.Admin;
import com.Havenly.Backend.Entity.Reg_user;
import com.Havenly.Backend.Repo.AdminRepo;
import com.Havenly.Backend.Service.AdminService;

@Configuration
public class AdminService_Impl implements AdminService{
	
	@Autowired
	AdminRepo adminRepo;

	@Override
	public Admin save(Admin admin) {
		
		
		
		return adminRepo.save(admin);
	}

	@Override
	public Admin Login(String gmail, String password) {
		
			Admin admin = adminRepo.findByEmail(gmail);
			
			if (admin == null) {
				return null;
			}
			
			
			return admin;
		
	}

}
