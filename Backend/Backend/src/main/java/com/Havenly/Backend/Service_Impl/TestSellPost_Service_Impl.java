package com.Havenly.Backend.Service_Impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Havenly.Backend.Entity.Ban_user;
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

//	@Override
//	public void savePhotosToDB(MultipartFile file, String title, String description, String price,
//			String area, String house_type, String property_type, Locations location_id) {
//		
//		TestSellPost tp = new TestSellPost();
//		
//		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		
//		if(fileName.contains("..")) {
//			System.out.println("Invalid file format!");
//		} 
//		tp.setTitle(title);
//		tp.setDescription(description);
//		tp.setPrice(price);
//		tp.setArea(area);
//		tp.setHouse_type(house_type);
//		tp.setProperty_type(property_type);
//		tp.setLocations(location_id);
//		
//		try {
//			tp.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		sellCount = (int) testSellRepo.count();
//		sellCount++;
//		String customId = "s" + sellCount;
//		
//		tp.setSell_post_id(customId);
//		tp.setDate(LocalDate.now());
//		tp.setTime(LocalTime.now());
//		
//		testSellRepo.save(tp);
//		
//		Posts post = new Posts();
//		post.setPost_type(customId);
//		post.setStatus("pending");
//		postsRepo.save(post);
//		
//	}
	
	
	
	
//	@Override
//	public void savePhotosToDB(MultipartFile[] files, String title, String description, String price,
//			String area, String house_type, String property_type, Locations location_id) {
//		
//		TestSellPost tp = new TestSellPost();
//
//		tp.setTitle(title);
//		tp.setDescription(description);
//		tp.setPrice(price);
//		tp.setArea(area);
//		tp.setHouse_type(house_type);
//		tp.setProperty_type(property_type);
//		tp.setLocations(location_id);
//		
//		StringBuilder imagesStringBuilder = new StringBuilder();
//		
//		for(MultipartFile file: files) {
//			String fileName = LocalDate.now().getYear() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
//			if(fileName.contains("..")) {
//				System.out.println("Invalid file format!");
//				continue;
//			}
//			try {
//	            // Convert image data to base64 string
//	            String imageData = Base64.getEncoder().encodeToString(file.getBytes());
//	            // Append image data to the images string, separated by a delimiter (e.g., ";")
//	            imagesStringBuilder.append(imageData).append(";");
//				tp.setImage(imageData);
////				byte[] fileData = file.getBytes();
//	            // Append raw file data to the images string, separated by a delimiter (e.g., ";")
////	            imagesStringBuilder.append(new String(fileData)).append(fileName + ";");
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//				
//		}
//		
//		tp.setImage(imagesStringBuilder.toString());
//		
//		sellCount = (int) testSellRepo.count();
//		sellCount++;
//		String customId = "s" + sellCount;
//		
//		tp.setSell_post_id(customId);
//		tp.setDate(LocalDate.now());
//		tp.setTime(LocalTime.now());
//		
//		testSellRepo.save(tp);
//		
//		Posts post = new Posts();
//		post.setPost_type(customId);
//		post.setStatus("pending");
//		postsRepo.save(post);
//		
//	}
	
	
	
	
	
//	@Override
//	public void savePhotosToDB(MultipartFile[] files, String title, String description, String price,
//			String area, String house_type, String property_type, Locations location_id) {
//		
//		TestSellPost tp = new TestSellPost();
//
//		tp.setTitle(title);
//		tp.setDescription(description);
//		tp.setPrice(price);
//		tp.setArea(area);
//		tp.setHouse_type(house_type);
//		tp.setProperty_type(property_type);
//		tp.setLocations(location_id);
//		
//		StringBuilder imagesStringBuilder = new StringBuilder();
//		
//		for(MultipartFile file: files) {
//			String fileName = LocalDate.now().getYear() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
//			if(fileName.contains("..")) {
//				System.out.println("Invalid file format!");
//				continue;
//			}
//			System.out.println("----------------------------------------------------------------");
//			System.out.println("File Name when upload from frontend : " + fileName);
//			System.out.println("----------------------------------------------------------------");
//			try {
//				byte[] fileData = file.getBytes();
//		        // Convert byte array to base64 string and append it to the StringBuilder
//		        String base64Encoded = Base64.getEncoder().encodeToString(fileData);
//		        imagesStringBuilder.append("data:image/jpeg;base64,").append(base64Encoded).append(";"); // Separate images by a delimiter
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//				
//		}
//		
//		String imageUrls = imagesStringBuilder.toString();
//		tp.setImage(imageUrls);
//		
//		sellCount = (int) testSellRepo.count();
//		sellCount++;
//		String customId = "s" + sellCount;
//		
//		tp.setSell_post_id(customId);
//		tp.setDate(LocalDate.now());
//		tp.setTime(LocalTime.now());
//		
//		testSellRepo.save(tp);
//		
//		Posts post = new Posts();
//		post.setPost_type(customId);
//		post.setStatus("pending");
//		postsRepo.save(post);
//		
//	}
	
	
	
	
	@Override

	public void savePhotosToDB(MultipartFile[] files, String title, String description, String price,
	        String area, String property_type, Locations location_id) {

	    TestSellPost tp = new TestSellPost();

	    tp.setTitle(title);
	    tp.setDescription(description);
	    tp.setPrice(price);
	    tp.setArea(area);
	    tp.setProperty_type(property_type);
	    tp.setLocations(location_id);

	 // Create a list to store image URLs
	    List<String> imageUrls = new ArrayList<String>(); 

	    for(MultipartFile file: files) {
	        String fileName = LocalDate.now().getYear() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
	        if(fileName.contains("..")) {
	            System.out.println("Invalid file format!");
	            continue;
	        }
	        System.out.println("----------------------------------------------------------------");
	        System.out.println("File Name when upload from frontend : " + fileName);
	        System.out.println("----------------------------------------------------------------");
	        try {
	            byte[] fileData = file.getBytes();
	            // Convert byte array to base64 string and add it to the list of image URLs
	            String base64Encoded = Base64.getEncoder().encodeToString(fileData);
	            imageUrls.add("data:image/jpeg;base64," + base64Encoded);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    String[] imageUrlsArray = imageUrls.toArray(new String[0]); // Convert list to array
	    tp.setImage(imageUrlsArray);

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
