package com.Havenly.Backend.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Havenly.Backend.Entity.Locations;
import com.Havenly.Backend.Entity.TestSellPost;

@Service
public interface TestSellPost_Service {
	
	public TestSellPost saveTestSellPost();
	
	public void savePhotosToDB(MultipartFile files, String title, String description, String price,
			String area, String house_type, String property_type, Locations location_id);
	
//	public void savePhotosToDB(MultipartFile[] files, String title, String description, String price,
//			String area, String house_type, String property_type, Locations location_id);

}
