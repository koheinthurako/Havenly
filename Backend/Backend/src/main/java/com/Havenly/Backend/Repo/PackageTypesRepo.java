package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Havenly.Backend.Entity.PackageTypes;

public interface PackageTypesRepo extends JpaRepository<PackageTypes, Integer> {
	
	@Query(value = "insert into package_types(package_type_id,price,total_ads,total_posts,package_types) values (1,0,2,3,\"Free Trial\"),(2,25,10,15,\"Normal\"),(3,50,15,30,\"Premium\")",nativeQuery= true)
	public PackageTypes insertValues();
	
	@Query(value = "select package_name from package_types where packTypeId=?1",nativeQuery= true)
	public String getPackType(Integer packId);
	
	@Query(value = "select total_ads from package_types where package_name=?1",nativeQuery= true)
	public int getTotalAds(String packName);
	
	@Query(value = "select total_posts from package_types where package_name=?1",nativeQuery= true)
	public int getTotalPosts(String packName);
}
