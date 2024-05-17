package com.Havenly.Backend.Service_Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.Service.Locations_Service;
import com.Havenly.Backend.DTO.Locations_DTO;
import com.Havenly.Backend.Entity.Locations;
import com.Havenly.Backend.Repo.Locations_Repo;

@Configuration
public class Locations_Service_Impl implements Locations_Service{
	
	@Autowired
	Locations_Repo locationRepo;
	
	Locations_DTO locationDTO = new Locations_DTO();

	@Override
	public List<Locations_DTO> getAllLocations() {
		List<Locations> locations = locationRepo.findAll();
		List<Locations_DTO> newLocationDTO = new ArrayList<Locations_DTO>();
		newLocationDTO.clear();
		for(Locations location : locations) {
			newLocationDTO.add(locationDTO.convertToObject(location));
		}
		return newLocationDTO;
	}

	@Override
	public List<Locations_DTO> saveAllLocations(List<Locations_DTO> locationsDTO) {
		List<Locations> locations = new ArrayList<Locations>();
		for(Locations_DTO locationdto : locationsDTO) {
			locations.add(locationDTO.convertToEntity(locationdto));
		}
		List<Locations> locationsList = locationRepo.saveAll(locations);
		List<Locations_DTO> locationsDTOList = new ArrayList<Locations_DTO>();
		for(Locations location : locationsList) {
			locationsDTOList.add(locationDTO.convertToObject(location));
		}
		return locationsDTOList;
	}
	
	

}
