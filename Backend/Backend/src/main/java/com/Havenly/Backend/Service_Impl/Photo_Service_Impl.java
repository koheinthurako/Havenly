package com.Havenly.Backend.Service_Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.Entity.Photo;
import com.Havenly.Backend.Repo.Photo_Repo;
import com.Havenly.Backend.Service.Photo_Service;

@Configuration
public class Photo_Service_Impl implements Photo_Service{
	
	@Autowired
	Photo_Repo photoRepo;

	@Override
	public void savePhoto(Photo photo) {
		photoRepo.save(photo);
	}
	
	

}
