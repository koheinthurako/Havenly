package com.Havenly.Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Havenly.Backend.DTO.Subscription_DTO;
import com.Havenly.Backend.Entity.PackageTypes;
import com.Havenly.Backend.Entity.Subscription;
import com.Havenly.Backend.Repo.PackageTypesRepo;
import com.Havenly.Backend.Service.SubscribeService;

@RestController
@RequestMapping("/Subscribe")
public class SubscriptionController {
	
	@Autowired
	SubscribeService subService;
	PackageTypesRepo packRepo;

	
	@GetMapping("/Cancel")
	public ResponseEntity<String> cancelSub(int subId){
		return new ResponseEntity <String>(subService.cancel(subId),HttpStatus.OK);
		
	}
	@GetMapping("/Packages")
	public ResponseEntity <List<PackageTypes>> showPackages(){
		return new ResponseEntity <List<PackageTypes>>(packRepo.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("/Confirm")
	public ResponseEntity<Subscription_DTO> subscribe(String nrc, int packTypeId){
		return new ResponseEntity <Subscription_DTO>(subService.subscribe(nrc, packTypeId),HttpStatus.ACCEPTED);
		
	}
}
