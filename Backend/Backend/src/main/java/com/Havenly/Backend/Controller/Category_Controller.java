package com.Havenly.Backend.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Havenly.Backend.Entity.Category;
import com.Havenly.Backend.Entity.Photo;
import com.Havenly.Backend.Service.Category_Service;
import com.Havenly.Backend.Service.Photo_Service;

@RestController
@RequestMapping("/category")
public class Category_Controller {
	
	@Autowired
	Category_Service cateService;
	
	@Autowired
	Photo_Service photoService;
	
	@GetMapping("/getAll")
	public List<Category> getAll(){
		return cateService.getAll();
	}
	
	@PostMapping("/save")
	public ResponseEntity<Category> save(@RequestBody Category category) {
		Category newCategory = cateService.saveCategory(category);
		return ResponseEntity.ok(newCategory);
	}
	
//	@PostMapping("/save")
//	public void createCategory(@RequestBody Category category) {
//        cateService.saveCategory(category);
//    }
	
//	@PostMapping("/{categoryId}/photos")
//    public void addPhotoToCollection(@PathVariable Long categoryId, @RequestParam("file") MultipartFile file) throws IOException {
//       cateService.addPhotoToCategory(categoryId, file);
//    }
//	
//	@GetMapping("/{categoryId}/photos")
//	public List<Photo> getPhotosInCategory(@PathVariable Long categoryId) {
//		return cateService.getPhotosInCategory(categoryId);
//	}
	
	
	
	
	

//    @PostMapping("/{categoryId}/photos")
//    public void addPhotoToCategory(@PathVariable int categoryId, @RequestBody List<Photo> photos) {
//        Category category = cateService.getCategoryById(categoryId);
//        if (category != null) {
//            for (Photo photo : photos) {
//                photo.setCategory(category);
//                photoService.savePhoto(photo);
//            }
//        }
//    }

}
