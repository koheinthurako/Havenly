package com.Havenly.Backend.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Havenly.Backend.DTO.Subscription_DTO;
import com.Havenly.Backend.Entity.PackageTypes;
import com.Havenly.Backend.Repo.PackageTypesRepo;
import com.Havenly.Backend.Service.SubscribeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class SubscriptionController {
	
	@Autowired
	SubscribeService subService;
	
	@Autowired
	PackageTypesRepo packRepo;

	
	@DeleteMapping("/cancel")
	public ResponseEntity<String> cancelSub(@Valid @RequestBody Subscription_DTO dto){
		return new ResponseEntity <String>(subService.cancel(dto),HttpStatus.OK);		
	}
	
	@GetMapping("/packages")
	public ResponseEntity <List<PackageTypes>> showPackages(){
		return new ResponseEntity <List<PackageTypes>>(packRepo.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("/subscribe")
	public ResponseEntity<Subscription_DTO> subscribe(@Valid @RequestBody Subscription_DTO dto){
		dto.setSubStartDate(LocalDate.now());
		dto.setSubStartTime(LocalDateTime.now());
		if(dto.getPackageType()=="Free Trial") {
			return new ResponseEntity <Subscription_DTO>(subService.freeTrial(dto),HttpStatus.ACCEPTED);
		}
		else if(dto.getPackageType() =="Normal") {
			return new ResponseEntity <Subscription_DTO>(subService.subNormal(dto),HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity <Subscription_DTO>(subService.subPremium(dto),HttpStatus.ACCEPTED);
		}
		
	}
}
