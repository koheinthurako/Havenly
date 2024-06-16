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
import com.Havenly.Backend.Entity.RentPost;
import com.Havenly.Backend.Entity.Subscription;
import com.Havenly.Backend.Repo.PackagesRepo;
import com.Havenly.Backend.Repo.Posts_Repo;
import com.Havenly.Backend.Repo.RentPost_Repo;
import com.Havenly.Backend.Repo.SubscribeRepo;
import com.Havenly.Backend.Service.RentPost_Service;

@Configuration
public class RentPost_Service_Impl implements RentPost_Service{
	
	@Autowired
	RentPost_Repo rentRepo;
	
	@Autowired
	Posts_Repo postsRepo;
	
	@Autowired
	PackagesRepo packageRepo;
	
	@Autowired
	SubscribeRepo subRepo;
	
	private int rentCount;

	@Override
	public void saveRentPost(MultipartFile[] files, int subUserId, String title, String description, String price, String area,
			String property_type, String deposit, String least_contract, Locations location_id) {
		
		RentPost rp = new RentPost();

	    rp.setTitle(title);
	    rp.setDescription(description);
	    rp.setPrice(price);
	    rp.setArea(area);
	    rp.setProperty_type(property_type);
	    rp.setLocations(location_id);
	    rp.setDeposit(deposit);
	    rp.setLeast_contract(least_contract);

	 // Create a list to store image URLs
	    List<String> imageUrls = new ArrayList<String>();

	    for(MultipartFile file: files) {
	        String fileName = LocalDate.now().getYear() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
	        if(fileName.contains("..")) {
	            System.out.println("Invalid file format!");
	            continue;
	        }
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
	    rp.setImage(imageUrlsArray);

	    rentCount = (int) rentRepo.count();
	    rentCount++;
	    String customId = "r" + rentCount;

	    rp.setRent_post_id(customId);
	    rp.setDate(LocalDate.now());
	    rp.setTime(LocalTime.now());

	    rentRepo.save(rp);

	    RentPost rentpost = rentRepo.findById(customId).orElseThrow();
	    Subscription subUser = subRepo.findById(subUserId).orElseThrow();
	    Posts post = new Posts();
	    post.setPost_type("Rent Post");
	    post.setStatus("pending");
	    post.setSubUser(subUser);
	    post.setRentpost(rentpost);
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
	public void updateRentPost(MultipartFile[] files, int postId, String rentPostId, String title, String description, String price,
			String area, String property_type, String deposit, String least_contract, Locations location_id) {
		
		
	    RentPost rentPost = rentRepo.findById(rentPostId).orElse(null);
	    Posts post = postsRepo.findById(postId).orElse(null);
	    
	    if(rentPost != null) {
	        rentPost.setTitle(title);
	        rentPost.setDescription(description);
	        rentPost.setPrice(price);
	        rentPost.setArea(area);
	        rentPost.setProperty_type(property_type);
	        rentPost.setDeposit(deposit);
	        rentPost.setLeast_contract(least_contract);
	        rentPost.setLocations(location_id);

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
	        rentPost.setImage(imageUrlsArray);

	        rentRepo.save(rentPost);

	        if(post != null) {
	            post.setStatus("pending");
	            postsRepo.save(post);
	        }
	    } else {
	        throw new RuntimeException("RentPost not found for ID: " + rentPostId);
	    }
	    
	}
	
	@Override
	public List<RentPost> getAllSubuserRentPosts(int subUserId) {
		return rentRepo.getAllSubuserRentPosts(subUserId);
	}

}
