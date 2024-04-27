package com.Havenly.Backend.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.Havenly.Backend.DTO.Reg_user_DD;
import com.Havenly.Backend.DTO.Reg_user_DTO;
import com.Havenly.Backend.Entity.Change_password;
import com.Havenly.Backend.Entity.Login;
import com.Havenly.Backend.Entity.Reg_user;
import com.Havenly.Backend.Service.Reg_user_Service;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class Reg_user_Controller {
	
	@Autowired
	Reg_user_Service regService;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Reg_user_DTO>> getAll() {
		return new ResponseEntity<List<Reg_user_DTO>>(regService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Reg_user_DTO> register(@Valid @RequestBody Reg_user_DTO dto){
		dto.setDate(LocalDate.now());
		dto.setTime(LocalTime.now());
		
		return new ResponseEntity<Reg_user_DTO> (regService.register(dto),HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Reg_user_DD> login(
			 @RequestBody Login login
	) {
		
		Reg_user_DD user = regService
				.Login(login.getEmail(), login.getPassword());
		if (user == null) {
			return ResponseEntity.badRequest().build();
		}
	
		return ResponseEntity.ok().body(user);
	}
	
	@PutMapping("/profile/update")
	public ResponseEntity<Reg_user_DD> updateProfile( @RequestBody  Reg_user_DD user) {
		
		Reg_user_DD updatedUser = regService.update(user.getName(),user.getPhone(),user.getEmail());
		if (updatedUser == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(updatedUser);
	}
	@PutMapping("/pwdUpdate")
	public ResponseEntity<Reg_user_DTO> updatePassword( @RequestBody Change_password change) {
		
		Reg_user_DTO updatedUser = regService.pwdUpdate(change.getUsername(),change.getPassword(),change.getNew_password());
		if (updatedUser == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(updatedUser);
	}




}
