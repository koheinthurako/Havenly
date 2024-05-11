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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Havenly.Backend.Entity.Locations;
import com.Havenly.Backend.Entity.MultiPartFormData;
import com.Havenly.Backend.Entity.Posts;
import com.Havenly.Backend.Entity.TestSellPost;
import com.Havenly.Backend.Repo.Posts_Repo;
import com.Havenly.Backend.Repo.TestSellPost_Repo;
import com.Havenly.Backend.Service.TestSellPost_Service;

@RestController
@RequestMapping("/")
public class TestSellPost_Controller {
	
	@Autowired
	TestSellPost_Repo testSellRepo;
	
	@Autowired
	TestSellPost_Service testSellService;
	
//	private int sellCount;
	
	@GetMapping("/gettestsellpost")
	public ResponseEntity<List<TestSellPost>> getAllTestSellPosts() {
		return new ResponseEntity<List<TestSellPost>>(testSellRepo.getAllTestPosts(), HttpStatus.OK);
	}
	
	
//	@PostMapping("/savetestsellpost")
//	public ResponseEntity<TestSellPost> saveSellPost(@RequestParam("file") MultipartFile file,
//				@RequestParam("title") String title,
//				@RequestParam("description") String description, @RequestParam("price") String price,
//				@RequestParam("area") String area, @RequestParam("house_type") String house_type,
//				@RequestParam("property_type") String property_type,
//				@RequestParam("location_id") Locations location_id
//			) {
//		testSellService.savePhotosToDB(file, title, description, price, area, house_type, property_type, location_id);
//		return null;
//		
//	}
	
	
	@PostMapping("/savetestsellpost")
	public ResponseEntity<TestSellPost> saveSellPost(@RequestParam("files") MultipartFile[] files,
				@RequestParam("title") String title,
				@RequestParam("description") String description, @RequestParam("price") String price,
				@RequestParam("area") String area, @RequestParam("house_type") String house_type,
				@RequestParam("property_type") String property_type,
				@RequestParam("location_id") Locations location_id
			) {
		for (MultipartFile file : files) {
	        System.out.println("Uploaded file: " + file.getOriginalFilename());
	    }
		testSellService.savePhotosToDB(files, title, description, price, area, house_type, property_type, location_id);
		return null;
		
	}
	
	
	
//	@PostMapping("/savetestsellpost")
//	public ResponseEntity<TestSellPost> saveSellPost(@RequestBody MultiPartFormData formData) {
//	    MultipartFile[] files = formData.getFiles();
//	    String title = formData.getTitle();
//	    String description = formData.getDescription();
//	    String price = formData.getPrice();
//	    String area = formData.getArea();
//	    String houseType = formData.getHouseType();
//	    String propertyType = formData.getPropertyType();
//	    Locations locationId = formData.getLocationId();
//
//	    for (MultipartFile file : files) {
//	        System.out.println("Uploaded file: " + file);
//	    }
//
//	    testSellService.savePhotosToDB(files, title, description, price, area, houseType, propertyType, locationId);
//	    return null;
//	}

	
	
	
	
	

}
