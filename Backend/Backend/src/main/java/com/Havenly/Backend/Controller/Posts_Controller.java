package com.Havenly.Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Havenly.Backend.Entity.Posts;
import com.Havenly.Backend.Service.Posts_Service;

@RestController
@RequestMapping("/posts")
public class Posts_Controller {
	
	@Autowired
	Posts_Service service;
	
	@GetMapping("/allPosts")
	public ResponseEntity<List<Posts>> getAllPosts(@RequestParam int subUserId){
		return new ResponseEntity<List<Posts>>(service.getAllPosts(subUserId),HttpStatus.OK);
	}
	
	@GetMapping("/allPending")
	public ResponseEntity<List<Posts>> getAllPendingPosts(){
		return new ResponseEntity<List<Posts>>(service.getAllPendingPosts(),HttpStatus.OK);
	}
	
	@GetMapping("/allComplete")
	public ResponseEntity<List<Posts>> getAllCompletePosts(){
		return new ResponseEntity<List<Posts>>(service.getAllCompletePosts(),HttpStatus.OK);
	}

	@GetMapping("/getPostById/{id}")
	public ResponseEntity<Posts> getPostById(@PathVariable String id) {
		return new ResponseEntity<>(service.getPostById(id), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Posts> update(@RequestBody Posts post){
		return new ResponseEntity<Posts>(service.update(post),HttpStatus.OK);
	}
	@PutMapping("/decline")
	public ResponseEntity<Posts> decline(@RequestBody Posts post){
		return new ResponseEntity<Posts>(service.decline(post),HttpStatus.OK);
	}

}
