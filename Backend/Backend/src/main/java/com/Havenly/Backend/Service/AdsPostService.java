package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Havenly.Backend.Entity.AdsPost;
@Service
public interface AdsPostService {
	
	public AdsPost createAdsPost(MultipartFile file, int subUserId, String title);
	public void deleteAdsPost(int subUserid, int ads_post_id);
	public void deleteByAdmin(int ads_post_id);
	public List<AdsPost> getAllSubUserAds(int subUserId);
	public List<AdsPost> getAllCompleteAds();
	public List<AdsPost> getAllPendingAds();
	public AdsPost updateStatus(AdsPost ad,  String status);
}
