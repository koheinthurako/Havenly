package com.Havenly.Backend.Service_Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.Entity.Thai_Location;
import com.Havenly.Backend.Repo.Thai_Location_Repo;
import com.Havenly.Backend.Service.Thai_Location_Service;

@Configuration
public class Thai_Location_Service_Impl implements Thai_Location_Service{
	
	@Autowired
	Thai_Location_Repo thaiLocationRepo;

	@Override
	public List<Thai_Location> getAll() {
		return thaiLocationRepo.findAll();
	}

	@Override
	public List<Thai_Location> saveAll(List<Thai_Location> thaiLocations) {
		return thaiLocationRepo.saveAll(thaiLocations);
	}

	
	
}
