package com.Havenly.Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import com.Havenly.Backend.Entity.Locations;
import com.Havenly.Backend.Entity.SellPost;
import com.Havenly.Backend.Repo.PackagesRepo;
import com.Havenly.Backend.Repo.SellPost_Repo;
import com.Havenly.Backend.Service.SellPost_Service;

@RestController
@RequestMapping("/sellpost")
public class SellPost_Controller {
	
	@Autowired
	SellPost_Service sellService;
	
	@Autowired
	SellPost_Repo repo;
	
	@Autowired
	PackagesRepo packRepo;
	
	@GetMapping("/gettestsellpost")
	public ResponseEntity<List<SellPost>> getAllTestSellPosts() {
		return new ResponseEntity<List<SellPost>>(repo.getAllTestSellPosts(), HttpStatus.OK);
	}
	
	@PostMapping("/savesellpost")
	public ResponseEntity<SellPost> saveSellPost(@RequestParam("files") MultipartFile[] files,
				@RequestParam("subUserId") int subUserId,
				@RequestParam("title") String title,
				@RequestParam("description") String description, @RequestParam("price") String price,
				@RequestParam("area") String area,
				@RequestParam("property_type") String property_type,
				@RequestParam("location_id") Locations location_id
			) {
		for (MultipartFile file : files) {
	        System.out.println("Uploaded file: " + file.getOriginalFilename());
	    }
		sellService.saveSellPost(files, subUserId, title, description, price, area, property_type, location_id);
		return null;
	}
	
	@PutMapping("/editsellpost")
	public ResponseEntity<?> editSellPost(@RequestParam("files") MultipartFile[] files,
			@RequestParam("postId") int postId,
			@RequestParam("sellPostId") String sellPostId,
			@RequestParam("title") String title,
			@RequestParam("description") String description, @RequestParam("price") String price,
			@RequestParam("area") String area,
			@RequestParam("property_type") String property_type,
			@RequestParam("location_id") Locations location_id) {
		
	        try {
	        	sellService.updateSellPost(files, postId, sellPostId, title, description, price, area, property_type, location_id);
	            return ResponseEntity.ok("Post updated successfully");
	        } catch (MaxUploadSizeExceededException e) {
	            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File size exceeds limit!");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the post.");
	        }
	}
	
	
	
	
	

}
