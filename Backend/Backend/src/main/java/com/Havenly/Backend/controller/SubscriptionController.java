package com.Havenly.Backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Havenly.Backend.entity.PackageTypes;
import com.Havenly.Backend.entity.Subscribe;
import com.Havenly.Backend.repo.PackageTypesRepo;
import com.Havenly.Backend.service.SubscribeService;

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
	public ResponseEntity<Subscribe> subscribe(String email, int packTypeId){
		return new ResponseEntity <Subscribe>(subService.subscribe(email, packTypeId),HttpStatus.ACCEPTED);
		
	}
}
