package com.Havenly.Backend.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.Havenly.Backend.Entity.Locations;

@Service
public interface RentPost_Service {

	public void savePhotosToDB(MultipartFile[] files, String title, String description, String price,
			String area, String property_type, String deposit, String least_contract, Locations location_id);
	
}
