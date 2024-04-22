package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.Entity.Myanmar_Location;

@Service
public interface Myanmar_Location_Service {

	public List<Myanmar_Location> getAll();
	public List<Myanmar_Location> saveAll(List<Myanmar_Location> myanmarLocations);
	
}
