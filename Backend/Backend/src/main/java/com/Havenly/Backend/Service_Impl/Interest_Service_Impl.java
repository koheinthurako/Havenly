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

@Configuration
public class Interest_Service_Impl implements Interest_Service{
	
	@Autowired
	Interest_Repo repo;
	
	@Autowired
	Reg_user_Repo user_repo;
	
	@Autowired
	Posts_Repo posts_repo;

	@Override
	public Interest save(int user_id, int posts_id) {
		// TODO Auto-generated method stub
		Optional<Reg_user> user=user_repo.findById(user_id);
		Optional<Posts> pp=posts_repo.findById(posts_id);
		
		Reg_user user1=user.get();
		Posts pp1=pp.get();
		
		Interest in=new Interest();
		in.setPosts(pp1);
		in.setUser(user1);
		return repo.save(in);
	}

	@Override
	public List<Interest> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
