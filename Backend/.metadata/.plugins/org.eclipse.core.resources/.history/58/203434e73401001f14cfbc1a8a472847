package com.Havenly.Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Havenly.Backend.Entity.Myanmar_Location;
import com.Havenly.Backend.Service.Myanmar_Location_Service;

@RestController
@RequestMapping("/myanmarlocation")
public class Myanmar_Location_Controller {
	
	@Autowired
	Myanmar_Location_Service myanmarLocationService;
	
	public ResponseEntity<List<Myanmar_Location>> getAllLocations() {
		return new ResponseEntity<List<Myanmar_Location>>(myanmarLocationService.getAll(), HttpStatus.OK);
	}
	
	public ResponseEntity<List<Myanmar_Location>> saveAllLocations(@RequestBody List<Myanmar_Location> myanmarLocations) {
		return new ResponseEntity<List<Myanmar_Location>>(myanmarLocationService.saveAll(myanmarLocations), HttpStatus.OK);
	}

}
