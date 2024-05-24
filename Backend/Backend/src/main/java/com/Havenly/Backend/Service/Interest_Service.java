package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.Entity.Interest;

@Service
public interface Interest_Service {

	public Interest save(int user_id,int posts_id);
	public List<Interest> getAll();
	public List<Interest> getAllInterestForNoti();
	
}
