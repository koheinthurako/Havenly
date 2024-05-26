package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.Entity.Posts;

@Service
public interface Posts_Service {
	
	public List<Posts> getAllPendingPosts();
	public List<Posts> getAllCompletePosts();
	public List<Posts> getAllSubuserPosts(int subUserId);
//	public List<Posts> getAllSubuserRentPosts(int subUserId);
	public Posts getPostById(int postId);
	public Posts update(Posts post);
	public Posts decline(Posts post);

}
