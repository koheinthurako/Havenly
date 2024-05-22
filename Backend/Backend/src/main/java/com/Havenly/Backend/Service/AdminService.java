package com.Havenly.Backend.Service;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.Entity.Admin;


@Service
public interface AdminService {
	
	public Admin save(Admin admin);
	public Admin Login(String gmail, String password);

}
