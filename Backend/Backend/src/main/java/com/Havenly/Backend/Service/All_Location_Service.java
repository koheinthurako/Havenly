package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.Entity.All_Location;

@Service
public interface All_Location_Service {
	
	public List<All_Location> getAll();
	public All_Location save(All_Location all_location);

}
