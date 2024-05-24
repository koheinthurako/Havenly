package com.Havenly.Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Havenly.Backend.Entity.Interest;
import com.Havenly.Backend.Service.Interest_Service;

@RestController
@RequestMapping("/interest")
public class Interest_Controller {

	@Autowired
	Interest_Service in_service;
	
	@PostMapping("/add/{user_id}/{posts_id}")
    public ResponseEntity<Interest> addInterest(@PathVariable int user_id,@PathVariable int posts_id) {
       return new ResponseEntity<Interest>(in_service.save(user_id, posts_id),HttpStatus.OK);
    }
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Interest>> get(){
		return new ResponseEntity<List<Interest>>(in_service.getAll(),HttpStatus.OK);
	}
	
	@GetMapping("/getAllNoti")
	public ResponseEntity<List<Interest>> getAllNoti(){
		return new ResponseEntity<List<Interest>>(in_service.getAllInterestForNoti(),HttpStatus.OK);
	}
}
