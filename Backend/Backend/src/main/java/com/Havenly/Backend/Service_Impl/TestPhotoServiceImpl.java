package com.Havenly.Backend.Service_Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.Entity.TestPhoto;
import com.Havenly.Backend.Repo.TestPhotoRepo;
import com.Havenly.Backend.Service.TestPhotoService;

@Configuration
public class TestPhotoServiceImpl implements TestPhotoService{
	
	@Autowired
	TestPhotoRepo testRepo;
	
	@Override
	public List<TestPhoto> savePhotos(List<TestPhoto> photos) {
		return testRepo.saveAll(photos);
	}

	@Override
	public List<TestPhoto> getPhotos() {
		return testRepo.findAll();
	}
	
	

}
