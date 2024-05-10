package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.Entity.Posts;

@Service
public interface Posts_Service {
	
	public List<Posts> getAllPosts();
	public Posts update(Posts post);
	public Posts decline(Posts post);

}
