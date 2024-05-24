package com.Havenly.Backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.Entity.Posts;

@Service
public interface Posts_Service {
	
	public List<Posts> getAllPendingPosts();
	public List<Posts> getAllCompletePosts();
	public List<Posts> getAllPosts(int subUserId);
	public Posts update(Posts post);
	public Posts decline(Posts post);
	Posts getPostById(String id);

}
