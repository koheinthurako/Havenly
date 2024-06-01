package com.Havenly.Backend.Service_Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.Entity.Interest;
import com.Havenly.Backend.Entity.Posts;
import com.Havenly.Backend.Entity.Reg_user;
import com.Havenly.Backend.Repo.Interest_Repo;
import com.Havenly.Backend.Repo.Posts_Repo;
import com.Havenly.Backend.Repo.Reg_user_Repo;
import com.Havenly.Backend.Service.Interest_Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Configuration
public class Interest_Service_Impl implements Interest_Service{
	
	@Autowired
	Interest_Repo repo;
	
	@Autowired
	Reg_user_Repo user_repo;
	
	@Autowired
	Posts_Repo posts_repo;

	@Override
	public Interest save(Interest data) {
		return repo.save(data);
	}

	@Override
	public List<Interest> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public List<Interest> getAllInterestForNoti(int id) {
		// TODO Auto-generated method stub
		
		return repo.getAllInterestForNoti(id);
	}

	@Override
	public Boolean deleteByPostIdAndUserId(int post_id, int user_id) {
		// check duplicate or not
		Long count = repo.countByPostIdAndUserId(user_id, post_id);
		if (count > 0) {
			repo.deleteByPostIdAndEmail(post_id, user_id);
			return true;
		}else {
			return false;
		}
	}

}
