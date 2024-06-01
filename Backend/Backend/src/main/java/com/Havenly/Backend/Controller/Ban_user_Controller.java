package com.Havenly.Backend.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Havenly.Backend.Entity.Ban_user;
import com.Havenly.Backend.Service.Ban_user_Service;


@RestController
@RequestMapping("/ban")
public class Ban_user_Controller {
	
	@Autowired
	Ban_user_Service banService;
	

	@PostMapping("/register")
	public ResponseEntity<Ban_user> register(@RequestBody Ban_user user){
		return new ResponseEntity<Ban_user> (banService.save(user),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{email}")
	public String deleteByEmail(@PathVariable String email) {
		String user1=banService.deleteByEmail(email);
		return user1;
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Ban_user>> getAll() {
		return new ResponseEntity<List<Ban_user>>(banService.findAll(), HttpStatus.OK);
	}
	
	

	

}
