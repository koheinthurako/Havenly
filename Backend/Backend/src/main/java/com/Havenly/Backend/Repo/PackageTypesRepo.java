package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Havenly.Backend.Entity.PackageTypes;

public interface PackageTypesRepo extends JpaRepository<PackageTypes, Integer> {

	public PackageTypes findByName(String name);
	
	@Query(value = "select packName from PackageTypes where packTypeId=?1",nativeQuery= true)
	public String getPackType(Integer packId);
	
	@Query(value = "select total_ads from PackageTypes where packName=?1",nativeQuery= true)
	public int getTotalAds(String packName);
	
	@Query(value = "select total_posts from PackageTypes where packName=?1",nativeQuery= true)
	public int getTotalPosts(String packName);
}
