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

import com.Havenly.Backend.DTO.Countries_DTO;
import com.Havenly.Backend.Service.Countries_Service;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/countries")
public class Countries_Controller {
	
	@Autowired
	Countries_Service countryService;
	
	@GetMapping("/getall")
	public ResponseEntity<List<Countries_DTO>> getAll() {
		return new ResponseEntity<List<Countries_DTO>>(countryService.getAllCountries(), HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<List<Countries_DTO>> saveCountry(@Valid @RequestBody List<Countries_DTO> countryDTO) {
		return new ResponseEntity<List<Countries_DTO>>(countryService.saveCountry(countryDTO), HttpStatus.OK);	}

}
