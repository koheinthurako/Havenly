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

import com.Havenly.Backend.DTO.PackageTypesDTO;
import com.Havenly.Backend.Service.PackageTypesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/package-types")
public class PackageTypesController {

	@Autowired
	PackageTypesService packTypesService;
	
	@GetMapping("/getall")
	public ResponseEntity<List<PackageTypesDTO>> getPackageTypes(){
		return new ResponseEntity<List<PackageTypesDTO>>(packTypesService.getAll(),HttpStatus.OK);
		
	}
	
	@PostMapping("/save")
	public ResponseEntity <List<PackageTypesDTO>> save(@Valid @RequestBody List<PackageTypesDTO> dto){
		return new ResponseEntity <List<PackageTypesDTO>>(packTypesService.saveAll(dto),HttpStatus.OK);
		
	}
	
}
