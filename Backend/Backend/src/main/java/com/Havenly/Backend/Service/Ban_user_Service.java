package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.Entity.Ban_user;

@Service
public interface Ban_user_Service {

	public Ban_user save(Ban_user user);
	public String deleteByEmail(String email);
	public List<Ban_user> findAll();
	
}
