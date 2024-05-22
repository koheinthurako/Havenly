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

import com.Havenly.Backend.DTO.NRC_DTO;
import com.Havenly.Backend.Service.NRC_Service;

@RestController
@RequestMapping("/nrc")
public class NRC_Controller {

	@Autowired
	NRC_Service nrcService;
	
	@GetMapping("/getall")
	public ResponseEntity<List<NRC_DTO>> getNRC(){
		return new ResponseEntity<List<NRC_DTO>> (nrcService.getAll(), HttpStatus.OK);	
	}
	
	@PostMapping("/save")
	public ResponseEntity<List<NRC_DTO>> save(@RequestBody List<NRC_DTO> nrc){
		return new ResponseEntity<List<NRC_DTO>>(nrcService.saveAll(nrc), HttpStatus.OK);
		
	}
}
