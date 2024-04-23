package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.DTO.All_Location_DTO;
import com.Havenly.Backend.Entity.All_Location;

@Service
public interface All_Location_Service {
	
	public List<All_Location_DTO> getAll();
	public All_Location_DTO save(All_Location_DTO all_location);

}
