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

import com.Havenly.Backend.Entity.Location;
import com.Havenly.Backend.Service.Location_Service;

@RestController
@RequestMapping("/location")
public class Location_Controller {
	
	@Autowired
	Location_Service locationService;
	
	@GetMapping("/getall")
	public ResponseEntity<List<Location>> getAll() {
		return new ResponseEntity<List<Location>>(locationService.getAll(), HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<List<Location>> save(@RequestBody List<Location> location) {
		return new ResponseEntity<List<Location>>(locationService.save(location), HttpStatus.OK);
	}

}
