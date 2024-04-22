package com.Havenly.Backend.Service_Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.Entity.All_Location;
import com.Havenly.Backend.Repo.All_Location_Repo;
import com.Havenly.Backend.Service.All_Location_Service;

@Configuration
public class All_Location_Service_Impl implements All_Location_Service{
	
	@Autowired
	All_Location_Repo allLocationRepo;

	@Override
	public List<All_Location> getAll() {
		return allLocationRepo.findAll();
	}

	@Override
	public All_Location save(All_Location all_location) {
		return allLocationRepo.save(all_location);
	}

	
	

}
