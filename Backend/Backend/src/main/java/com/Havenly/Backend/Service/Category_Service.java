package com.Havenly.Backend.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.Havenly.Backend.Entity.Category;

@Service
public interface Category_Service {
	
	public Category saveCategory(Category category);
//	public void addPhotoToCategory(Long categoryId, MultipartFile file) throws IOException;
//	public List<Photo> getPhotosInCategory(Long categoryId);
	public List<Category> getAll();
	
//	public void saveCategory(Category category);
//	public Category getCategoryById(Long CategoryId);
	
}
