package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.Havenly.Backend.Entity.Packages;

import jakarta.transaction.Transactional;

public interface PackagesRepo extends JpaRepository<Packages, Integer> {
	
	
	@Query(value = "SELECT * FROM packages WHERE sub_user_id = ?1", nativeQuery = true)
	public Packages findByUserId(int subUserId);
	
	@Modifying
    @Transactional
	@Query(value = "UPDATE packages SET avail_posts = ?1 WHERE sub_user_id = ?2", nativeQuery = true)
	public void updatePost(int postCount, int subUserId);
	
	@Transactional
    @Modifying
	 @Query(value = "Delete from packages  WHERE sub_user_id = ?", nativeQuery = true)
	    void deleteFrompackages(int id);
	
}
