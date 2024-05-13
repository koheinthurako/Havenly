package com.Havenly.Backend.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Havenly.Backend.Entity.Posts;
import com.Havenly.Backend.Entity.SellPost;
import com.Havenly.Backend.Repo.Posts_Repo;
import com.Havenly.Backend.Repo.SellPost_Repo;
import com.Havenly.Backend.Service.SellPost_Service;

@RestController
@RequestMapping("/")
public class SellPost_Controller {
	
	@Autowired
	SellPost_Repo sellRepo;
	
	@Autowired
	SellPost_Service sellPostService;
	
	@Autowired
	Posts_Repo postsRepo;
	
	private int sellCount;
	
	public SellPost_Controller(SellPost_Repo sellRepo) {
//        this.sellRepo = sellRepo;
//        this.sellCount = (int) sellRepo.count() + 1; // Initialize sellCount based on the count of existing records
		System.out.println("Sell Post Count Number : " + this.sellCount);
    }
	
	@GetMapping("/getsellpost")
	public ResponseEntity<List<SellPost>> getAllSellPosts() {
		return new ResponseEntity<List<SellPost>>(sellRepo.getAllPosts(), HttpStatus.OK);
	}
	
	@PostMapping("/savesellpost")
	public SellPost saveSellPost(@RequestBody SellPost sellpost) {
		
		sellCount = (int) sellRepo.count();
		sellCount++;
		String customId = "s" + sellCount;
		
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
	
//	@PostMapping("/savesellpost")
//    public ResponseEntity<?> saveSellPost(@RequestParam("photos") MultipartFile[] photos, @RequestPart("sellPost") SellPost sellPost) {
//        // Save photos to storage (you can customize this according to your storage mechanism)
//        // Save sell post details
//		
//		sellCount = (int) sellRepo.count();
//		String customId = "s" + sellCount++;
//		
//		sellPost.setSell_post_id(customId);
//		sellPost.setDate(LocalDate.now());
//		sellPost.setTime(LocalTime.now());
//		sellRepo.save(sellPost);
//		
//		Posts post = new Posts();
//		post.setPost_type(customId);
//		post.setStatus("pending");
//		postsRepo.save(post);
//		
//		sellRepo.save(sellPost);
//        return ResponseEntity.ok("Sell post saved successfully.");
//    }

}