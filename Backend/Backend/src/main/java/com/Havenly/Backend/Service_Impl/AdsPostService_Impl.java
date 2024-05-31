package com.Havenly.Backend.Service_Impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Havenly.Backend.Entity.AdsPost;
import com.Havenly.Backend.Entity.Packages;
import com.Havenly.Backend.Entity.Posts;
import com.Havenly.Backend.Entity.Subscription;
import com.Havenly.Backend.Repo.AdsPost_Repo;
import com.Havenly.Backend.Repo.PackagesRepo;
import com.Havenly.Backend.Repo.Posts_Repo;
import com.Havenly.Backend.Repo.SubscribeRepo;
import com.Havenly.Backend.Service.AdsPostService;
@Configuration
public class AdsPostService_Impl implements AdsPostService{
	
	@Autowired
	AdsPost_Repo adsRepo;
	
//	@Autowired
//	Posts_Repo postsRepo;
	
	@Autowired
	PackagesRepo packageRepo;
	
	@Autowired
	SubscribeRepo subRepo;
	
	private int adsCount;
	@Override
	public AdsPost createAdsPost(MultipartFile img, int subUserId, String title) {
			AdsPost ad = new AdsPost();
			ad.setDate(LocalDate.now());
			ad.setTime(LocalDateTime.now());
			ad.setTitle(title);
			if(img != null && !img.isEmpty()) {
				
				 String fileName = LocalDate.now().getYear() + "_" + StringUtils.cleanPath(img.getOriginalFilename());
			        if(fileName.contains("..")) {
			            System.out.println("Invalid file format!");
			        }
			        System.out.println("----------------------------------------------------------------");
			        System.out.println("File Name when upload from frontend : " + fileName);
			        System.out.println("----------------------------------------------------------------");
			        try {
			            byte[] imageBytes  = img.getBytes();
			            String base64Encoded = Base64.getEncoder().encodeToString(imageBytes);
			            String imageUrl = "data:image/jpeg;base64,"+base64Encoded;
			            ad.setImageUrl(imageUrl);
			        	} catch (IOException e) {
			            e.printStackTrace();
			        } 
			}
			 adsCount = (int) adsRepo.count();
			 adsCount++;
			    String customId = "a" + adsCount;
			    ad.setAds_post_id(customId);
			    
			    Subscription subUser = subRepo.findById(subUserId).orElseThrow();
			    ad.setSubUser(subUser);
			    
//			    Posts post = new Posts();
//			    post.setPost_type(customId);
//			    post.setStatus("pending");
//			    post.setSubUser(subUser);
//			    post.setAdsPosts(ad);
//			    postsRepo.save(post);
//			    
			    Packages pack = packageRepo.findByUserId(subUserId);
			    if (pack == null) {
			        throw new RuntimeException("Package not found for user ID: " + subUserId);
			    } else {
			    	int adsCount = pack.getAvailAds()-1;
			    	System.out.println("call UpdateAds method");
			    	System.out.println("adsCount value : " + adsCount);
				    packageRepo.updateAds(adsCount, subUserId);
				    System.out.println("Successfully updated avail_ads in database!");
			    }
			    AdsPost adPost = adsRepo.save(ad);
				return adPost;
	}
	@Override
	public void deleteAdsPost(int subUserid, String ads_post_id) {
			Optional<AdsPost> adsOptional = adsRepo.findById(ads_post_id);
			 if (!adsOptional.isPresent()) {
		            throw new IllegalArgumentException("Ads post not found");
		        }
			AdsPost adsPost = adsOptional.get();
	        if (!(adsPost.getSubUser().getSubUserId()==subUserid)) {
	            throw new IllegalArgumentException("Ads post does not belong to the user");
	        }
	        
//			Posts post = postsRepo.getPostsByAdsPostId(ads_post_id);
			adsRepo.delete(adsPost);
//			postsRepo.delete(post);
	}
	
	@Override
	public List<AdsPost> getAllSubUserAds(int subUserId) {
		List<AdsPost> adsList = adsRepo.findAllSubUserAds(subUserId);
		return adsList;
	}


}
