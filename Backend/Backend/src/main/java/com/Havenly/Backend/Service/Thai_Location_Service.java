package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.DTO.Thai_Location_DTO;

@Service
public interface Thai_Location_Service {
	
	public List<Thai_Location_DTO> getAll();
	public List<Thai_Location_DTO> saveAll(List<Thai_Location_DTO> thaiLocationsDTO);

}
