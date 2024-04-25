package com.Havenly.Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Havenly.Backend.Entity.TestPhoto;
import com.Havenly.Backend.Repo.TestPhotoRepo;
import com.Havenly.Backend.Service.TestPhotoService;

@RestController
@RequestMapping("/testphoto")
public class TestPhotoController {
	
	@Autowired
	TestPhotoService photoService;
	
	@PostMapping("/upload")
	public ResponseEntity<List<TestPhoto>> savePhoto(@RequestBody List<TestPhoto> testPhoto) {
		return new ResponseEntity<List<TestPhoto>>(photoService.savePhotos(testPhoto), HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<TestPhoto>> getPhotos() {
		return new ResponseEntity<List<TestPhoto>>(photoService.getPhotos(), HttpStatus.OK);
	}

}
