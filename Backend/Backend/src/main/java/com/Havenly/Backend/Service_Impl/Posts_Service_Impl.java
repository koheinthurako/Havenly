package com.Havenly.Backend.Service_Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.Havenly.Backend.Entity.Packages;
import com.Havenly.Backend.Entity.Posts;
import com.Havenly.Backend.Repo.PackagesRepo;
import com.Havenly.Backend.Repo.Posts_Repo;
import com.Havenly.Backend.Service.Posts_Service;

@Configuration
public class Posts_Service_Impl implements Posts_Service {

	@Autowired
	Posts_Repo postsRepo;
	
	@Autowired
	PackagesRepo packageRepo;
	
	@Override
	public List<Posts> getAllSubuserPosts(int subUserId) {
		return postsRepo.getAllSubuserPosts(subUserId);
	}
	
//	@Override
//	public List<Posts> getAllSubuserRentPosts(int subUserId) {
//		return repo.getAllSubuserRentPosts(subUserId);
//	}

	@Override
	public List<Posts> getAllPendingPosts() {
		// TODO Auto-generated method stub
		return postsRepo.getAllPendingPosts();
	}
	
	@Override
	public List<Posts> getAllCompletePosts() {
		// TODO Auto-generated method stub
		return postsRepo.getAllCompletePosts();
	}
	
	@Override
	public Posts getPostById(int postId) {
		return postsRepo.findById(postId).orElse(null);
	}
	
	@Override
	public List<Posts> getAllPostsByLocation(int locationId) {
		return postsRepo.getAllPostsByLocation(locationId);
	}

	public Posts update(Posts post) {

		Posts new_user = postsRepo.findById(post.getPost_id()).orElse(null);
		if (new_user != null) {
			new_user.setStatus("complete");
		}

		Posts user1 = postsRepo.save(new_user);
		return user1;
	}

	@Transactional
	@Override
	public void decliePost(@RequestParam int subUserId, @RequestParam int postId) {
		Posts post = postsRepo.findById(postId)
	            .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
		if(post != null) {
			post.setStatus("rejected");
		}
		Packages pack = packageRepo.findByUserId(subUserId);
        if (pack == null) {
	        throw new RuntimeException("Package not found for user ID: " + subUserId);
	    } else {
	    	int postCount = pack.getAvailPosts()+1;
		    packageRepo.updatePost(postCount, subUserId);
		    System.out.println("Successfully updated plus 1 avail_post count in database!");
	    }
//		postsRepo.delete(post);
	}

	@Override
	public List<Posts> getInterestPostByRegId(int id) {
		return postsRepo.getInterestedPostsByRegId(id);
	}

	

	


}
