package com.Havenly.Backend.Service_Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.Service.Posts_Service;
import com.Havenly.Backend.Entity.Posts;
import com.Havenly.Backend.Repo.Posts_Repo;

@Configuration
public class Posts_Service_Impl implements Posts_Service {

	@Autowired
	Posts_Repo repo;
	
	@Override
	public List<Posts> getAllPosts(int subUserId) {
		return repo.getAllPosts(subUserId);
	}

	@Override
	public List<Posts> getAllPendingPosts() {
		// TODO Auto-generated method stub
		return repo.getAllPendingPosts();
	}
	
	@Override
	public List<Posts> getAllCompletePosts() {
		// TODO Auto-generated method stub
		return repo.getAllCompletePosts();
	}

	public Posts update(Posts post) {

		Posts new_user = repo.findById(post.getPost_id()).orElse(null);
//		Posts new_user= new Posts();
		if (new_user != null) {
//			new_user.setPost_id(post.getPost_id());
//			new_user.setPost_type(post.getPost_type());
			new_user.setStatus("complete");
			
//			new_user.setTestsellpostss(post.getTestsellpostss());
		}

		Posts user1 = repo.save(new_user);
		return user1;
	}

	public Posts decline(Posts post) {

		Posts new_user = repo.findById(post.getPost_id()).orElse(null);
//		Posts new_user= new Posts();
		if (new_user != null) {
//			new_user.setPost_id(post.getPost_id());
//			new_user.setPost_type(post.getPost_type());
			new_user.setStatus("Declined");
//			new_user.setTestsellpostss(post.getTestsellpostss());
		}

		Posts user1 = repo.save(new_user);
		return user1;
	}

	

}
