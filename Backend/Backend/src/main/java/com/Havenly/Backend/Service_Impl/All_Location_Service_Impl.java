package com.Havenly.Backend.Service_Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.DTO.All_Location_DTO;
import com.Havenly.Backend.Entity.All_Location;
import com.Havenly.Backend.Repo.All_Location_Repo;
import com.Havenly.Backend.Service.All_Location_Service;

@Configuration
public class All_Location_Service_Impl implements All_Location_Service{
	
	@Autowired
	All_Location_Repo allLocationRepo;
	
	All_Location_DTO allDTO = new All_Location_DTO();

	@Override
	public List<All_Location_DTO> getAll() {
		List<All_Location> allLocations = allLocationRepo.findAll();
		List<All_Location_DTO> allLocationsDTO = new ArrayList<All_Location_DTO>();
		allLocationsDTO.clear();
		for(All_Location location : allLocations) {
			allLocationsDTO.add(allDTO.convertToObject(location));
		}
		return allLocationsDTO;
	}

	@Override
	public All_Location_DTO save(All_Location_DTO allLocationDTO) {
		All_Location location = allDTO.convertToEntity(allLocationDTO);
		All_Location newLocation = allLocationRepo.save(location);
		All_Location_DTO newAllLocationDTO = allDTO.convertToObject(newLocation);
		return newAllLocationDTO;
	}

	
	

}
