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

import com.Havenly.Backend.DTO.Myanmar_Location_DTO;
import com.Havenly.Backend.Service.Myanmar_Location_Service;

@RestController
@RequestMapping("/myanmarlocation")
public class Myanmar_Location_Controller {
	
	@Autowired
	Myanmar_Location_Service myanmarLocationService;
	
	@GetMapping("/getall")
	public ResponseEntity<List<Myanmar_Location_DTO>> getAllLocations() {
		return new ResponseEntity<List<Myanmar_Location_DTO>>(myanmarLocationService.getAll(), HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<List<Myanmar_Location_DTO>> saveAllLocations(@RequestBody List<Myanmar_Location_DTO> myanmarLocationsDTO) {
		return new ResponseEntity<List<Myanmar_Location_DTO>>(myanmarLocationService.saveAll(myanmarLocationsDTO), HttpStatus.OK);
	}

}
