package com.Havenly.Backend.Service_Impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Havenly.Backend.Entity.AdsPost;
import com.Havenly.Backend.Entity.Packages;
import com.Havenly.Backend.Entity.Subscription;
import com.Havenly.Backend.Repo.AdsPost_Repo;
import com.Havenly.Backend.Repo.PackagesRepo;
import com.Havenly.Backend.Repo.SubscribeRepo;
import com.Havenly.Backend.Service.AdsPostService;

@Configuration
public class AdsPostService_Impl implements AdsPostService{
	
	@Autowired
	AdsPost_Repo adsRepo;
	
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
			    ad.setStatus("pending");
			    
			    Packages pack = packageRepo.findByUserId(subUserId);
			    if (pack == null) {
			        throw new RuntimeException("Package not found for user ID: " + subUserId);
			    } else {
			    	int adsCount = pack.getAvailAds()-1;
			    	packageRepo.updateAds(adsCount, subUserId);
			    	
			    	System.out.println("call UpdateAds method");
			    	System.out.println("adsCount value : " + adsCount);
				    System.out.println("Successfully updated avail_ads in database!");
			    }
			    AdsPost adPost = adsRepo.save(ad);
				return adPost;
	}
	
	@Transactional
	@Override
	public void deleteAdsPost(int subUserid, String adsId) {
			AdsPost adsPost = adsRepo.findById(adsId).orElse(null);
			 if (adsPost == null) {
		            throw new IllegalArgumentException("Ad not found.");   
		        }
	        if (!(adsPost.getSubUser().getSubUserId()==subUserid)) {
	            throw new IllegalArgumentException("Ad does not belong to the user.");
	        }	        
			adsRepo.delete(adsPost);
	}
	
	@Override
	public List<AdsPost> getAllSubUserAds(int subUserId) {
		List<AdsPost> adsList = adsRepo.findAllSubUserAds(subUserId);
		return adsList;
	}
	@Override
	public List<AdsPost> getAllCompleteAds() {
		List<AdsPost> completeAds = adsRepo.findAllAds("complete");
		return completeAds;
	}
	
// Administrator
	@Override
	public List<AdsPost> getAllPendingAds() {
		List<AdsPost> pendingAds = adsRepo.findAllAds("pending");		
		return pendingAds;
	}
	
	@Override
	public AdsPost updateStatus(AdsPost ad, String status) {
		AdsPost new_ads = adsRepo.findById(ad.getAds_post_id()).orElse(null);
		if(new_ads != null) {
			new_ads.setStatus(status);
		}
		AdsPost completeAd = adsRepo.save(new_ads);
		return completeAd;
	}

	@Transactional
	@Override
	public void deleteByAdmin(String adsId) {
		AdsPost adsPost = adsRepo.findById(adsId).orElseThrow(() -> new RuntimeException("Ad not found with id: " + adsId));
		int subUserId = adsPost.getSubUser().getSubUserId();
		 Packages pack = packageRepo.findByUserId(subUserId);
		    if (pack == null) {
		        throw new RuntimeException("Package not found for user ID: " + subUserId);
		    } else {
		    	int adsCount = pack.getAvailAds()+1;
		    	System.out.println("call UpdateAds method");
		    	System.out.println("adsCount value : " + adsCount);
			    packageRepo.updateAds(adsCount, subUserId);
			    System.out.println("Successfully updated avail_ads in database!");
		    }
		    
		adsRepo.delete(adsPost);
	}


}
