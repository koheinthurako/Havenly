package com.Havenly.Backend.Service_Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Havenly.Backend.DTO.Reg_user_DD;
import com.Havenly.Backend.DTO.Reg_user_DTO;
import com.Havenly.Backend.Entity.Reg_user;
import com.Havenly.Backend.Repo.Reg_user_Repo;
import com.Havenly.Backend.Service.Reg_user_Service;

@Configuration
public class Reg_user_Service_Impl implements Reg_user_Service{
	
	@Autowired
	Reg_user_Repo regRepo;
	
	@Autowired
	PasswordEncoder pwencoder;
	
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
	
	

}
