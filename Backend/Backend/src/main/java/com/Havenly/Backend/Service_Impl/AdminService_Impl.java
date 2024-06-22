package com.Havenly.Backend.Service_Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.Service.AdminService;
import com.Havenly.Backend.Entity.Admin;
import com.Havenly.Backend.Repo.AdminRepo;

@Configuration
public class AdminService_Impl implements AdminService{
	
	@Autowired
	AdminRepo adminRepo;

	@Override
	public Admin save(Admin admin) {
		
		return adminRepo.save(admin);
	}

	@Override
	public Admin Login(String gmail, String ppassword) {
		
			Admin admin = adminRepo.findByEmail(gmail);
			System.out.println(admin.getPassword());
			if (admin == null || !(admin.getPassword().equals(ppassword) )) {
				return null;
			}
			
			
			return admin;
		
	}

}
