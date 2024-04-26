//package com.Havenly.Backend.Service_Impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//import com.Havenly.Backend.Entity.Photo;
//import com.Havenly.Backend.Repo.Photo_Repo;
//import com.Havenly.Backend.Service.Photo_Service;
//
//@Configuration
//public class Photo_Service_Impl implements Photo_Service{
//	
//	@Autowired
//	Photo_Repo testRepo;
//	
//	@Override
//	public List<Photo> savePhotos(List<Photo> photos) {
//		return testRepo.saveAll(photos);
//	}
//
//	@Override
//	public List<Photo> getPhotos() {
//		return testRepo.findAll();
//	}
//	
//	
//
//}
