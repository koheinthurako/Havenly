package com.Havenly.Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Havenly.Backend.Entity.Posts;
import com.Havenly.Backend.Service.Posts_Service;

@RestController
@RequestMapping("/posts")
public class Posts_Controller {
	
	@Autowired
	Posts_Service service;
	
	@GetMapping("/all")
	public ResponseEntity<List<Posts>> getAllPosts(){
		return new ResponseEntity<List<Posts>>(service.getAllPosts(),HttpStatus.OK);
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
