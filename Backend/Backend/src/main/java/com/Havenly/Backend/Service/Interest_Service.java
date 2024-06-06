package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.Entity.Interest;

@Service
public interface Interest_Service {

	public Interest save(Interest data);
	public List<Interest> getAll();
	public List<Interest> getAllInterestForNoti(int id);
	Boolean deleteByPostIdAndUserId(int post_id, int user_id);
	List<Interest> getDataByPostId(int id);
	
}
