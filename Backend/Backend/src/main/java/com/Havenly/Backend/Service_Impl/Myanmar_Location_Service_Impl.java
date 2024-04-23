package com.Havenly.Backend.Service_Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.DTO.Myanmar_Location_DTO;
import com.Havenly.Backend.Entity.Myanmar_Location;
import com.Havenly.Backend.Repo.Myanmar_Location_Repo;
import com.Havenly.Backend.Service.Myanmar_Location_Service;

@Configuration
public class Myanmar_Location_Service_Impl implements Myanmar_Location_Service{
	
	@Autowired
	Myanmar_Location_Repo myanmarLocationRepo;
	
	Myanmar_Location_DTO myanDTO = new Myanmar_Location_DTO();

	@Override
	public List<Myanmar_Location_DTO> getAll() {
		List<Myanmar_Location> locations = myanmarLocationRepo.findAll();
		List<Myanmar_Location_DTO> myanLocationDTO = new ArrayList<Myanmar_Location_DTO>();
		myanLocationDTO.clear();
		for(Myanmar_Location location : locations) {
			myanLocationDTO.add(myanDTO.convertToObject(location));
		}
		return myanLocationDTO;
	}

	@Override
	public List<Myanmar_Location_DTO> saveAll(List<Myanmar_Location_DTO> myanmarLocationsDTO) {
		List<Myanmar_Location> myanLocations = new ArrayList<Myanmar_Location>();
		for(Myanmar_Location_DTO location : myanmarLocationsDTO) {
			myanLocations.add(myanDTO.convertToEntity(location));
		}
		List<Myanmar_Location> locationsList = myanmarLocationRepo.saveAll(myanLocations);
		List<Myanmar_Location_DTO> locationsDTO = new ArrayList<Myanmar_Location_DTO>();
		for(Myanmar_Location location : locationsList) {
			locationsDTO.add(myanDTO.convertToObject(location));
		}
		return locationsDTO;
	}
	
	

}