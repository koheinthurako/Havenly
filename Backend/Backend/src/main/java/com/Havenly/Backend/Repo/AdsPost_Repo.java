package com.Havenly.Backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.Havenly.Backend.Entity.AdsPost;

import jakarta.transaction.Transactional;

public interface AdsPost_Repo extends JpaRepository<AdsPost,String>{

	@Query(value = "SELECT * FROM ads_post WHERE sub_user_id = ?1", nativeQuery = true)
	public List<AdsPost> findAllSubUserAds(int subUserId);

	@Query(value = "SELECT * FROM ads_post WHERE status = ?1", nativeQuery = true)
	public List<AdsPost> findAllAds(String status);
	
	@Transactional
    @Modifying
	 @Query(value = "Delete from ads_post  WHERE sub_user_id = ?", nativeQuery = true)
	    void deleteFromAds(int id);
	

}
