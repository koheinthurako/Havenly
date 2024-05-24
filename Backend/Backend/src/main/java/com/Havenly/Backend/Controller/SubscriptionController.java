package com.Havenly.Backend.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@DeleteMapping("/subscribe/cancel")
	public ResponseEntity<?> cancelSub(@Valid @RequestBody Subscription_DTO dto){
		return new ResponseEntity <>(subService.cancel(dto),HttpStatus.OK);		
	}
	
//	@GetMapping("/subscribe/getSubUserInfo")
//	public ResponseEntity<List<Subscription_DTO>> getSubUserInfo() {
//		return new ResponseEntity<List<Subscription_DTO>>(subRepo.getSubUserInfo(), HttpStatus.OK);
//	}
	
	@GetMapping("/subscribe/getSubUserInfo")
	public ResponseEntity<Subscription_DTO> getSubUserInfo(@RequestParam int registerId) {
		return new ResponseEntity<Subscription_DTO>(subRepo.getSubUserInfo(registerId), HttpStatus.OK);
	}
	
	
	@PostMapping("/subscribe")
	public ResponseEntity<Subscription_DTO> subscribe(@Valid @RequestBody Subscription_DTO dto){
		if(dto==null) {
			return ResponseEntity.unprocessableEntity().build();
		}

		if(this.regRepo.findByEmail(dto.getEmail()) == null) {
			return ResponseEntity.notFound().build();
		}
		if(this.subRepo.findByNrc(dto.getNrc()) != null) {
			return ResponseEntity.badRequest().build();
		}
		dto.setSubStartDate(LocalDate.now());
		dto.setSubStartTime(LocalDateTime.now());
		
			return new ResponseEntity <Subscription_DTO>(subService.subscribe(dto),HttpStatus.OK);		
	}
	
	
	
}
