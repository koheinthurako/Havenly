package com.Havenly.Backend.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Havenly.Backend.Entity.Admin;
import com.Havenly.Backend.Service.AdminService;



@RestController
@RequestMapping("/admin")
public class Admin_Controller {
	
	@Autowired
	AdminService adminService;
	
	
	@PostMapping("/register")
	public ResponseEntity<Admin> register(@RequestBody Admin admin){
		
		
		return new ResponseEntity<Admin> (adminService.save(admin),HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Admin> login(
			 @RequestBody Admin admin
	) {
		
		Admin admin1 = adminService
				.Login(admin.getEmail(), admin.getPassword());
		if (admin1 == null) {
			return ResponseEntity.badRequest().build();
		}
	
		return ResponseEntity.ok().body(admin1);
	}

}
