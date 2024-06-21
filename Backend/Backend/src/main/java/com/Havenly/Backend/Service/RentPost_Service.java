package com.Havenly.Backend.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Havenly.Backend.Entity.Locations;

@Service
public interface RentPost_Service {

	public void saveRentPost(MultipartFile[] files, int subUserId, String title, String description, String price,
			String area, String property_type, String deposit, String least_contract, Locations location_id);
	
	public void updateRentPost(MultipartFile[] files, int postId, String rentPostId, String title, String description, String price,
			String area, String property_type, String deposit, String least_contract, Locations location_id);
	
//	public List<RentPost> getAllSubuserRentPosts(int subUserId);
	
}
