package com.Havenly.Backend.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.Havenly.Backend.Entity.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Havenly.Backend.DTO.Subscription_DTO;
import com.Havenly.Backend.Repo.Reg_user_Repo;
import com.Havenly.Backend.Repo.SubscribeRepo;
import com.Havenly.Backend.Service.SubscriptionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class SubscriptionController {
	
	@Autowired
	SubscriptionService subService;
	
	@Autowired
	Reg_user_Repo regRepo;
	
	@Autowired
	SubscribeRepo subRepo;

	
	@GetMapping("/subscribe/getSubUserInfo")
	public ResponseEntity<Subscription_DTO> getSubUserInfo(@RequestParam int registerId) {
		return new ResponseEntity<Subscription_DTO>(subRepo.getSubUserInfo(registerId), HttpStatus.OK);
	}
	
	
	@PostMapping("/subscribe")
	public ResponseEntity<Subscription_DTO> subscribe(@Valid @RequestBody Subscription_DTO dto){
		if(dto==null) {
			return ResponseEntity.noContent().build();
		}
		dto.setSubStartDate(LocalDate.now());
		dto.setSubStartTime(LocalDateTime.now());
		if(dto.getPackageType() == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<Subscription_DTO>(subService.subscribe(dto), HttpStatus.OK);
//		Subscription_DTO dto1 = subService.subscribe(dto);
//		
//			return ResponseEntity.ok().body(dto1);		
	}
	
	@GetMapping("/subscribe/getAll")
	public ResponseEntity<List<Subscription>> getAllUserInfo() {
		List<Subscription> data = subService.getAllSubUserInfo();
		if (data == null || data.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
}
