package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.DTO.Myanmar_Location_DTO;
import com.Havenly.Backend.Entity.Myanmar_Location;

@Service
public interface Myanmar_Location_Service {

	public List<Myanmar_Location_DTO> getAll();
	public List<Myanmar_Location_DTO> saveAll(List<Myanmar_Location_DTO> myanmarLocationsDTO);
	
}
