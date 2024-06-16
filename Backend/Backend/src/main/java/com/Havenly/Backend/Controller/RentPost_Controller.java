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
import com.Havenly.Backend.Entity.RentPost;
import com.Havenly.Backend.Entity.SellPost;
import com.Havenly.Backend.Repo.RentPost_Repo;
import com.Havenly.Backend.Service.RentPost_Service;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/rentpost")
public class RentPost_Controller {
	
	@Autowired
	RentPost_Service rentService;
	
	@Autowired
	RentPost_Repo rentRepo;
	
	@GetMapping("/getallrentposts")
	public ResponseEntity<List<RentPost>> getAllRentPosts() {
		return new ResponseEntity<List<RentPost>>(rentRepo.getAllRentPosts(), HttpStatus.OK);
	}
	
	@GetMapping("/allSubuserRentPosts")
	public ResponseEntity<List<RentPost>> getAllSubuserRentPosts(@RequestParam int subUserId) {
		return new ResponseEntity<List<RentPost>>(rentService.getAllSubuserRentPosts(subUserId), HttpStatus.OK);
	}
	
	@PostMapping("/saverentpost")
	public ResponseEntity<SellPost> saveSellPost(@RequestParam("files") MultipartFile[] files,
				@RequestParam("subUserId") int subUserId,
				@RequestParam("title") String title,
				@RequestParam("description") String description, @RequestParam("price") String price,
				@RequestParam("area") String area,
				@RequestParam("property_type") String property_type,
				@RequestParam("deposit") String deposit,
				@RequestParam("least_contract") String least_contract,
				@RequestParam("location_id") Locations location_id
			) {
		for (MultipartFile file : files) {
	        System.out.println("Uploaded file: " + file.getOriginalFilename());
	    }
		rentService.saveRentPost(files, subUserId, title, description, price, area, property_type, deposit, least_contract, location_id);
		return null;
		
	}
	
	@PutMapping("/editRentPost")
	public ResponseEntity<?> editRentPost(@RequestParam("files") MultipartFile[] files,
			@RequestParam("postId") int postId,
			@RequestParam("rentPostId") String rentPostId,
			@RequestParam("title") String title,
			@RequestParam("description") String description, @RequestParam("price") String price,
			@RequestParam("area") String area,
			@RequestParam("property_type") String property_type,
			@RequestParam("deposit") String deposit,
			@RequestParam("least_contract") String least_contract,
			@RequestParam("location_id") Locations location_id) {
		
	        try {
	            rentService.updateRentPost(files, postId, rentPostId, title, description, price, area, property_type, deposit, least_contract, location_id);
	            return ResponseEntity.ok("Post updated successfully");
	        } catch (MaxUploadSizeExceededException e) {
	            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File size exceeds limit!");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the post.");
	        }
	}
	
}