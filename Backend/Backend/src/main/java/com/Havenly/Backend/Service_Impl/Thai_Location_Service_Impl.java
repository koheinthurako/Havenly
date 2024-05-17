package com.Havenly.Backend.Service_Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.DTO.Thai_Location_DTO;
import com.Havenly.Backend.DTO.Thai_Location_DTO;
import com.Havenly.Backend.DTO.Thai_Location_DTO;
import com.Havenly.Backend.Entity.Thai_Location;
import com.Havenly.Backend.Entity.Thai_Location;
import com.Havenly.Backend.Entity.Thai_Location;
import com.Havenly.Backend.Repo.Thai_Location_Repo;
import com.Havenly.Backend.Service.Thai_Location_Service;

@Configuration
public class Thai_Location_Service_Impl implements Thai_Location_Service{
	
	@Autowired
	Thai_Location_Repo thaiLocationRepo;
	
	Thai_Location_DTO thaiDTO = new Thai_Location_DTO();

	@Override
	public List<Thai_Location_DTO> getAll() {
		List<Thai_Location> locations = thaiLocationRepo.findAll();
		List<Thai_Location_DTO> thaiLocationDTO = new ArrayList<Thai_Location_DTO>();
		thaiLocationDTO.clear();
		for(Thai_Location location : locations) {
			thaiLocationDTO.add(thaiDTO.convertToObject(location));
		}
		return thaiLocationDTO;
	}

	@Override
	public List<Thai_Location_DTO> saveAll(List<Thai_Location_DTO> thaiLocationsDTO) {
		List<Thai_Location> myanLocations = new ArrayList<Thai_Location>();
		for(Thai_Location_DTO location : thaiLocationsDTO) {
			myanLocations.add(thaiDTO.convertToEntity(location));
		}
		List<Thai_Location> locationsList = thaiLocationRepo.saveAll(myanLocations);
		List<Thai_Location_DTO> locationsDTO = new ArrayList<Thai_Location_DTO>();
		for(Thai_Location location : locationsList) {
			locationsDTO.add(thaiDTO.convertToObject(location));
		}
		return locationsDTO;
	}

	
	
}