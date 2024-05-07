package com.Havenly.Backend.Service_Impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Havenly.Backend.Entity.Locations;
import com.Havenly.Backend.Entity.Posts;
import com.Havenly.Backend.Entity.TestSellPost;
import com.Havenly.Backend.Repo.Posts_Repo;
import com.Havenly.Backend.Repo.TestSellPost_Repo;
import com.Havenly.Backend.Service.TestSellPost_Service;

@Configuration
public class TestSellPost_Service_Impl implements TestSellPost_Service{
	
	@Autowired
	TestSellPost_Repo testSellRepo;
	
	@Autowired
	Posts_Repo postsRepo;
	
	private int sellCount;

	@Override
	public TestSellPost saveTestSellPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePhotosToDB(MultipartFile file, String title, String description, String price,
			String area, String house_type, String property_type, Locations location_id) {
		
		TestSellPost tp = new TestSellPost();
//		Locations location = new Locations();
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		if(fileName.contains("..")) {
			System.out.println("Invalid file format!");
		} 
		tp.setTitle(title);
		tp.setDescription(description);
		tp.setPrice(price);
		tp.setArea(area);
		tp.setHouse_type(house_type);
		tp.setProperty_type(property_type);
		tp.setLocations(location_id);
		
		try {
			tp.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		sellCount = (int) testSellRepo.count();
		sellCount++;
		String customId = "s" + sellCount;
		
		tp.setSell_post_id(customId);
		tp.setDate(LocalDate.now());
		tp.setTime(LocalTime.now());
		
		testSellRepo.save(tp);
		
		Posts post = new Posts();
		post.setPost_type(customId);
		post.setStatus("pending");
		postsRepo.save(post);
		
	}
	
	

}