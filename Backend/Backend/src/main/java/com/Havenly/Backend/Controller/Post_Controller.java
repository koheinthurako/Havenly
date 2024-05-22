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

import com.Havenly.Backend.Entity.Post;
import com.Havenly.Backend.Service.Post_Service;

@RestController
@RequestMapping("/")
public class Post_Controller {

	@Autowired
	Post_Service postService;
	
	@GetMapping("/")
	public ResponseEntity<List<Post>> getAllLocations() {
		return new ResponseEntity<List<Post>>(postService.getAllPosts(), HttpStatus.OK);
	}
	
	@PostMapping("/savepost")
	public ResponseEntity<List<Post>> saveAllLocations(@RequestBody List<Post> posts) {
		return new ResponseEntity<List<Post>>(postService.saveAllPosts(posts), HttpStatus.OK);
	}
	
}
