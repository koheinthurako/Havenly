package com.Havenly.Backend.Service_Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.Entity.Myanmar_Location;
import com.Havenly.Backend.Repo.Myanmar_Location_Repo;
import com.Havenly.Backend.Service.Myanmar_Location_Service;

@Configuration
public class Myanmar_Location_Service_Impl implements Myanmar_Location_Service{
	
	@Autowired
	Myanmar_Location_Repo myanmarLocationRepo;

	@Override
	public List<Myanmar_Location> getAll() {
		return myanmarLocationRepo.findAll();
	}

	@Override
	public List<Myanmar_Location> saveAll(List<Myanmar_Location> myanmarLocations) {
		return myanmarLocationRepo.saveAll(myanmarLocations);
	}
	
	

}
