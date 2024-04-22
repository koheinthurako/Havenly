package com.Havenly.Backend.Service_Impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import com.Havenly.Backend.Entity.Category;
import com.Havenly.Backend.Entity.Photo;
import com.Havenly.Backend.Repo.Category_Repo;
import com.Havenly.Backend.Repo.Photo_Repo;
import com.Havenly.Backend.Service.Category_Service;

@Configuration
public class Category_Service_Impl implements Category_Service{
	
	@Autowired
	Category_Repo categoryRepo;
	
	@Autowired
	Photo_Repo photoRepo;

	@Override
	public Category saveCategory(Category category) {
		return categoryRepo.save(category);
	}

//	@Override
//	public void addPhotoToCategory(Long categoryId, MultipartFile file) throws IOException {
//		Category categories = categoryRepo.findById(categoryId)
//				.orElseThrow(() -> new RuntimeException("Category not found"));
//		Photo photo = new Photo();
//		photo.setData(file.getBytes());
//		photo.setCategory(categories);
//		photoRepo.save(photo);
//	}

	@Override
	public List<Category> getAll() {
		return categoryRepo.findAll();
	}

//	@Override
//	public List<Photo> getPhotosInCategory(Long categoryId) {
//		return photoRepo.findByCategoryId(categoryId);
//	}

	
	
	
	
	
	
	
//	@Override
//	public void saveCategory(Category category) {
//		categoryRepo.save(category);
//	}
//
//	@Override
//	public Category getCategoryById(Long CategoryId) {
//		return categoryRepo.findById(CategoryId).orElse(null);
//	}
	
	

}
