package com.Havenly.Backend.Service_Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.Entity.Location;
import com.Havenly.Backend.Repo.Location_Repo;
import com.Havenly.Backend.Service.Location_Service;

@Configuration
public class Location_Service_Impl implements Location_Service{
	
	@Autowired
	Location_Repo locationRepo;

	@Override
	public List<Location> getAll() {
		return locationRepo.findAll();
	}

	@Override
	public List<Location> save(List<Location> location) {
		return locationRepo.saveAll(location);
	}
	
}
