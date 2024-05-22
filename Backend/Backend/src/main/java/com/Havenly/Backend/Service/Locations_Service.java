package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.DTO.Locations_DTO;

@Service
public interface Locations_Service {
	
	public List<Locations_DTO> getAllLocations();
	public List<Locations_DTO> saveAllLocations(List<Locations_DTO> locationsDTO);

}
