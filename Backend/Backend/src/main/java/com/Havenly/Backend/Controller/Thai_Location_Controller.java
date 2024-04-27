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

import com.Havenly.Backend.DTO.Thai_Location_DTO;
import com.Havenly.Backend.Entity.Thai_Location;
import com.Havenly.Backend.Service.Thai_Location_Service;

@RestController
@RequestMapping("/thailocation")
public class Thai_Location_Controller {
	
	@Autowired
	Thai_Location_Service thaiLocationService;
	
	@GetMapping("/getall")
	public ResponseEntity<List<Thai_Location_DTO>> getAllLocations() {
		return new ResponseEntity<List<Thai_Location_DTO>>(thaiLocationService.getAll(), HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<List<Thai_Location_DTO>> saveAllLocations(@RequestBody List<Thai_Location_DTO> thaiLocations) {
		return new ResponseEntity<List<Thai_Location_DTO>>(thaiLocationService.saveAll(thaiLocations), HttpStatus.OK);
	}

}
