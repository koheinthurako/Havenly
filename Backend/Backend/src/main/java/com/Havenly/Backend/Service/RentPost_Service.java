package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Havenly.Backend.Entity.Locations;
import com.Havenly.Backend.Entity.RentPost;

@Service
public interface RentPost_Service {

	public void saveRentPost(MultipartFile[] files, int subUserId, String title, String description, String price,
			String area, String property_type, String deposit, String least_contract, Locations location_id);
	
	public List<RentPost> getAllSubuserRentPosts(int subUserId);
	
}
