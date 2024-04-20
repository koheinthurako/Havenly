package com.Havenly.Backend.Controller;

import java.time.LocalDate;
import java.time.LocalTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Havenly.Backend.DTO.Reg_user_DTO;
import com.Havenly.Backend.Entity.Login;
import com.Havenly.Backend.Entity.Reg_user;
import com.Havenly.Backend.Service.Reg_user_Service;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/")
public class Reg_user_Controller {
	
	@Autowired
	Reg_user_Service regService;
	
	@PostMapping("/register")
	public ResponseEntity<Reg_user_DTO> register(@Valid @RequestBody Reg_user_DTO dto){
		dto.setDate(LocalDate.now());
		dto.setTime(LocalTime.now());
		
		return new ResponseEntity<Reg_user_DTO> (regService.register(dto),HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Reg_user> login(
			 @RequestBody Login login
	) {
		
		Reg_user user = regService
				.Login(login.getEmail(), login.getPassword());
		if (user == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().body(user);
	}
}
