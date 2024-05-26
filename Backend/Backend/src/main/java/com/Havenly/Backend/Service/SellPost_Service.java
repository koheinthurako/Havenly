package com.Havenly.Backend.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.Havenly.Backend.Entity.Locations;

@Service
public interface SellPost_Service {
	
	public void saveSellPost(MultipartFile[] files, int subUserId, String title, String description, String price,
			String area, String property_type, Locations location_id);
	
	public void updateSellPost(MultipartFile[] files, int postId, String sellPostId, String title, String description, String price,
			String area, String property_type, Locations location_id);

}
