package com.Havenly.Backend.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.Havenly.Backend.DTO.Reg_user_DD;
import com.Havenly.Backend.DTO.Reg_user_DTO;
import com.Havenly.Backend.Entity.Change_password;
import com.Havenly.Backend.Entity.Login;

import com.Havenly.Backend.Repo.Reg_user_Repo;
//import com.Havenly.Backend.Repo.TokenRepository;
import com.Havenly.Backend.Service.Reg_user_Service;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/")
public class Reg_user_Controller {
	
	@Autowired
	Reg_user_Service regService;
	
	@Autowired
	Reg_user_Repo regRepo;
	
//	@Autowired
//	TokenRepository tokenRepository;
	
	@Autowired
	Reg_user_Repo userRepo;
	
	@Autowired
	PasswordEncoder pwencoder;
	
	
	
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Reg_user_DTO>> getAll() {
		return new ResponseEntity<List<Reg_user_DTO>>(regService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Reg_user_DTO> register(@Valid @RequestBody Reg_user_DTO dto){
		dto.setDate(LocalDate.now());
		dto.setTime(LocalTime.now());
		Reg_user_DTO dto1=regService.register(dto);
		if (dto1 == null) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok().body(dto1);
	}

	@PostMapping("/login")
	public ResponseEntity<Reg_user_DD> login(
			 @RequestBody Login login
	) {
		
		Reg_user_DD user = regService.Login(login.getEmail(), login.getPassword());
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
	
	@DeleteMapping("/delete/{email}")
	public String deleteById(@PathVariable String email) {
		String user1=regService.deleteByEmail(email);
		return user1;
	}
	
//	@GetMapping("/forgotPassword")
//	public String forgotPassword() {
//		return "forgotPassword";
//	}
//	
//	@PostMapping("/forgotPassword")
//	public String forgotPasswordProcess(@RequestBody Reg_user user) {
//		String output="";
//		Reg_user user1=regRepo.findByEmail(user.getEmail());
//		if(user1 != null ) {
//			output = regService.sendEmail(user1);
//		}
//		
//		 if(output.equals("success")) {
//			return "success";
//		}
//		 return "error";
//		
//	}
//
//	@GetMapping("/resetPassword/{token}")
//	public String resetPasswordForm(String token,Model model) {
//		
//		PasswordResetToken reset= tokenRepository.findByToken(token);
//		if(reset != null && regService.hasExpired(reset.getExpiryDateTime())) {
//			model.addAttribute("email",reset.getUser().getEmail());
//			return  "resetPassword";
//		}
//		return "redirect:/forgotPassword?error";
//	}
//	
//	@PostMapping("/resetPassword/{token}")
//	public String passwordResetProcess(@ModelAttribute Reg_user user){
//		Reg_user user1=userRepo.findByEmail(user.getEmail());
//		if(user1 != null) {	
//			user1.setPassword(this.pwencoder.encode(user.getPassword()));
//			userRepo.save(user1);
//			}
//		return "redirect:/login";
//	}
	
	
	@PutMapping("/forgotpassword")
	public ResponseEntity<String> forgotpassword(@RequestParam String email){
		return new ResponseEntity<>(regService.forgotPassword(email),HttpStatus.OK);
	}
	
	@PutMapping("/setpassword")
	public ResponseEntity<String> setPassword(@RequestParam String email,@RequestHeader String newPassword){
		return new ResponseEntity<>(regService.setPassword(email,newPassword),HttpStatus.OK);
	}
	


}
