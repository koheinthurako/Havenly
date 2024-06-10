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
import com.Havenly.Backend.Entity.Locations;
import com.Havenly.Backend.Entity.Packages;
import com.Havenly.Backend.Entity.Posts;
import com.Havenly.Backend.Entity.Subscription;
import com.Havenly.Backend.Entity.SellPost;
import com.Havenly.Backend.Repo.PackagesRepo;
import com.Havenly.Backend.Repo.Posts_Repo;
import com.Havenly.Backend.Repo.SubscribeRepo;
import com.Havenly.Backend.Repo.SellPost_Repo;
import com.Havenly.Backend.Service.SellPost_Service;

@Configuration
public class SellPost_Service_Impl implements SellPost_Service{
	
	@Autowired
	SellPost_Repo sellRepo;
	
	@Autowired
	Posts_Repo postsRepo;
	
	@Autowired
	PackagesRepo packageRepo;
	
	@Autowired
	SubscribeRepo subRepo;
	
	private int sellCount;

	public void saveSellPost(MultipartFile[] files, int subUserId, String title, String description, String price,
	        String area, String property_type, Locations location_id) {

	    SellPost tp = new SellPost();

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

	    sellCount = (int) sellRepo.count();
	    sellCount++;
	    String customId = "s" + sellCount;

	    tp.setSell_post_id(customId);
	    tp.setDate(LocalDate.now());
	    tp.setTime(LocalTime.now());

	    sellRepo.save(tp);
	    
	    SellPost savedSellPost = sellRepo.findById(customId).orElseThrow();
	    Subscription subUser = subRepo.findById(subUserId).orElseThrow();
	    Posts post = new Posts();
	    post.setPost_type("Sell Post");
	    post.setStatus("pending");
	    post.setSubUser(subUser);
	    post.setSellpost(savedSellPost);
	    postsRepo.save(post);
	    
	    Packages pack = packageRepo.findByUserId(subUserId);
	    if (pack == null) {
	        throw new RuntimeException("Package not found for user ID: " + subUserId);
	    } else {
	    	int postCount = pack.getAvailPosts()-1;
	    	System.out.println("UpdatePost method ko thwar call p");
	    	System.out.println("postCount value : " + postCount);
		    packageRepo.updatePost(postCount, subUserId);
		    System.out.println("Successfully updated avail_posts in database!");
	    }
	}

	@Override
	public void updateSellPost(MultipartFile[] files, int postId, String sellPostId, String title, String description,
			String price, String area, String property_type, Locations location_id) {
		
		SellPost sellPost = sellRepo.findById(sellPostId).orElse(null);
		Posts post = postsRepo.findById(postId).orElse(null);
		
		if(sellPost != null) {
			sellPost.setTitle(title);
			sellPost.setDescription(description);
			sellPost.setPrice(price);
			sellPost.setArea(area);
			sellPost.setProperty_type(property_type);
			sellPost.setLocations(location_id);
			
			List<String> imageUrls = new ArrayList<>();

	        for(MultipartFile file : files) {
	            String fileName = LocalDate.now().getYear() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
	            if(fileName.contains("..")) {
	                System.out.println("Invalid file format!");
	                continue;
	            }
	            try {
	                byte[] fileData = file.getBytes();
	                String base64Encoded = Base64.getEncoder().encodeToString(fileData);
	                imageUrls.add("data:image/jpeg;base64," + base64Encoded);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }

	        String[] imageUrlsArray = imageUrls.toArray(new String[0]);
	        sellPost.setImage(imageUrlsArray);
	        
	        sellRepo.save(sellPost);
	        
	        if(post != null) {
	            post.setStatus("pending");
	            postsRepo.save(post);
	        }
		} else {
	        throw new RuntimeException("RentPost not found for ID: " + sellPostId);
	    }
		
	}

	@Override
	public List<SellPost> getAllSubuserSellPosts(int subUserId) {
		return sellRepo.getAllSubuserSellPosts(subUserId);
	}


}
