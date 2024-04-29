package com.Havenly.Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Havenly.Backend.DTO.Locations_DTO;
import com.Havenly.Backend.Service.Locations_Service;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/locations")
public class Locations_Controller {
	
	@Autowired
	Locations_Service locationService;
	
	@GetMapping("/getall")
	public ResponseEntity<List<Locations_DTO>> getAllLocations() {
		return new ResponseEntity<List<Locations_DTO>>(locationService.getAllLocations(), HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<List<Locations_DTO>> saveAllLocations(@Valid @RequestBody List<Locations_DTO> locationsDTO) {
		return new ResponseEntity<List<Locations_DTO>>(locationService.saveAllLocations(locationsDTO), HttpStatus.OK);
	}

}
