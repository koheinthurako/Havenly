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

import com.Havenly.Backend.DTO.All_Location_DTO;
import com.Havenly.Backend.Service.All_Location_Service;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/alllocations")
public class All_Location_Controller {
	
	@Autowired
	All_Location_Service allLocationService;
	
	@GetMapping("/getall")
	public ResponseEntity<List<All_Location_DTO>> getAllLocations() {
		return new ResponseEntity<List<All_Location_DTO>>(allLocationService.getAll(), HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<All_Location_DTO> saveAllLocations(@Valid @RequestBody All_Location_DTO allLocationsDTO) {
		return new ResponseEntity<All_Location_DTO>(allLocationService.save(allLocationsDTO), HttpStatus.OK);
	}

}
