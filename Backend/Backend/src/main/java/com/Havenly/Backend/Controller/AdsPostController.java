package com.Havenly.Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Havenly.Backend.Entity.AdsPost;
import com.Havenly.Backend.Repo.AdsPost_Repo;
import com.Havenly.Backend.Repo.PackagesRepo;
import com.Havenly.Backend.Service_Impl.AdsPostService_Impl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class AdsPostController {
	
	@Autowired
	AdsPostService_Impl adsService;
	
	@Autowired
	AdsPost_Repo adsRepo;
	
	@Autowired
	PackagesRepo packageRepo;
	

	@PostMapping("/ads/create")
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

    @DeleteMapping("/ads/delete")
    public ResponseEntity<String> deleteAdPost(@RequestParam("subUserId") int subUserId, @RequestParam("ads_post_id") int adPostId) {
        try {
            adsService.deleteAdsPost(subUserId, adPostId);
            return ResponseEntity.ok("ad deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting ad: " + e.getMessage());
        }
    }
    
    @GetMapping("/ads/all/{subUserId}")
    public ResponseEntity<List<AdsPost>> getAllSubUserAds(@PathVariable int subUserId){
		return new ResponseEntity <List<AdsPost>>(adsService.getAllSubUserAds(subUserId),HttpStatus.OK);
    	
    }
    
    @GetMapping("/ads/all/complete")
    public ResponseEntity<List<AdsPost>> getAllCompleteAds(){
		return new ResponseEntity <List<AdsPost>>(adsService.getAllCompleteAds(),HttpStatus.OK);
    	
    }
    
   //Administrator  
    @GetMapping("/ads/all/pending")
    public ResponseEntity<List<AdsPost>> getAllPendingAds(){
		return new ResponseEntity <List<AdsPost>>(adsService.getAllPendingAds(),HttpStatus.OK);
    	
    }
    @PutMapping("/admin/approve")
    public ResponseEntity<AdsPost> approveAds(@Valid @RequestBody AdsPost ad){
		return new ResponseEntity<AdsPost>(adsService.updateStatus(ad, "complete"),HttpStatus.OK);	
    }
    
    @DeleteMapping("/admin/decline")
    public ResponseEntity<String> declineAds(@RequestParam("AdsId") int adId) {
        try {
            adsService.deleteByAdmin(adId);
            return ResponseEntity.ok("ad declined successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error declining ad: " + e.getMessage());
        }
    }
}
