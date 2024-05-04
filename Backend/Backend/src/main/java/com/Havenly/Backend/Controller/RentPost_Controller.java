package com.Havenly.Backend.Controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Havenly.Backend.Entity.Posts;
import com.Havenly.Backend.Entity.RentPost;
import com.Havenly.Backend.Repo.Posts_Repo;
import com.Havenly.Backend.Repo.RentPost_Repo;

@RestController
@RequestMapping("/")
public class RentPost_Controller {
	
	@Autowired
	RentPost_Repo rentRepo;
	
	@Autowired
	Posts_Repo postsRepo;
	
	private int rentCount;
	
	@PostMapping("/saverentpost")
	public RentPost saveRentPost(@RequestBody RentPost rentpost) {
		
		rentCount = (int) rentRepo.count();
		
		String customId = "r" + rentCount++;
		
		rentpost.setRent_post_id(customId);
		rentpost.setDate(LocalDate.now());
		rentpost.setTime(LocalTime.now());
		rentRepo.save(rentpost);
		
		Posts post = new Posts();
		post.setPost_type(customId);
		post.setStatus("pending");
		postsRepo.save(post);
		
		return rentpost;
	}
	
}