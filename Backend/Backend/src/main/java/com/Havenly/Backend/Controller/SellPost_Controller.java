package com.Havenly.Backend.Controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Havenly.Backend.Entity.Posts;
import com.Havenly.Backend.Entity.SellPost;
import com.Havenly.Backend.Repo.Posts_Repo;
import com.Havenly.Backend.Repo.SellPost_Repo;

@RestController
@RequestMapping("/")
public class SellPost_Controller {
	
	@Autowired
	SellPost_Repo sellRepo;
	
	@Autowired
	Posts_Repo postsRepo;
	
	private int sellCount = 1;
	
	@PostMapping("/savesellpost")
	public SellPost saveSellPost(@RequestBody SellPost sellpost) {
		
		String customId = "s" + sellCount++;
		
		sellpost.setSell_post_id(customId);
		sellpost.setDate(LocalDate.now());
		sellpost.setTime(LocalTime.now());
		sellRepo.save(sellpost);
		
		Posts post = new Posts();
		post.setPost_type(customId);
		post.setStatus("pending");
		postsRepo.save(post);
		
		return sellpost;
	}

}
