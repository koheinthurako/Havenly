package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.Entity.Location;

@Service
public interface Location_Service {
	
	public List<Location> getAll();
	public List<Location> save(List<Location> location);
	
}
