package com.Havenly.Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Havenly.Backend.Entity.AdsPost;
import com.Havenly.Backend.Repo.AdsPost_Repo;
import com.Havenly.Backend.Repo.PackagesRepo;
import com.Havenly.Backend.Service_Impl.AdsPostService_Impl;

@RestController
@RequestMapping("/")
public class AdsPostController {
	
	@Autowired
	AdsPostService_Impl adsService;
	
	@Autowired
	AdsPost_Repo adsRepo;
	
	@Autowired
	PackagesRepo packageRepo;
	

	@PostMapping("/create/adspost")
	public ResponseEntity<AdsPost> createAdsPost(@RequestParam("image") MultipartFile img,
				@RequestParam("subUserId") int subUserId,
				@RequestParam("title") String title
			) {
		if (img == null) {
	        return ResponseEntity.notFound().build(); 
	    }else {
	    	System.out.println("Uploaded file: " + img.getOriginalFilename());
	    }
		return new ResponseEntity <AdsPost>(adsService.createAdsPost(img, subUserId, title),HttpStatus.OK);	
	}

    @DeleteMapping("/delete/adspost")
    public void deleteAdPost(@RequestParam int subUserId, @RequestParam String adPostId) {
        adsService.deleteAdsPost(subUserId, adPostId);
    }
    
    @GetMapping("/subuserads")
    public ResponseEntity<List<AdsPost>> getAllSubUserAds(@RequestParam("subUserId") int subUserId){
		return new ResponseEntity <List<AdsPost>>(adsService.getAllSubUserAds(subUserId),HttpStatus.OK);
    	
    }
}
