package com.Havenly.Backend.Service_Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.Havenly.Backend.Entity.Post;
import com.Havenly.Backend.Repo.Post_Repo;
import com.Havenly.Backend.Service.Post_Service;


@Configuration
public class Post_Service_Impl implements Post_Service{
	
	@Autowired
	Post_Repo postRepo;
	
	@Override
	public List<Post> getAllPosts() {
//		List<Post> posts = postRepo.findAll();
//		List<Post> postList = new ArrayList<Post>();
//		for(Post post : posts) {
//			postList.add(post);
//		}
		return postRepo.findAll();
	}


	@Override
	public List<Post> saveAllPosts(List<Post> posts) {
//		List<Post> postList = new ArrayList<Post>();
//		for(Post post : posts) {
//			postList.add(postRepo.save(post));
//		}
//		return postList;
		return postRepo.saveAll(posts);
	}
	
}
