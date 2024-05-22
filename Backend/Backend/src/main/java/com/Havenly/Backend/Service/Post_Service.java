package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.Entity.Post;

@Service
public interface Post_Service {
	
	public List<Post> getAllPosts();
	public List<Post> saveAllPosts(List<Post> posts);

}
