package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Havenly.Backend.Entity.PackageTypes;

public interface PackageTypesRepo extends JpaRepository<PackageTypes, Integer> {
	
	
	public PackageTypes findByPackName(String packName);
	
	@Query(value = "select package_name from package_types where package_type_id=?1",nativeQuery= true)
	public String getPackType(int package_type_id);
	
	@Query(value = "select total_ads from package_types where package_type_id=?1",nativeQuery= true)
	public int getTotalAds(int package_type_id);
	
	@Query(value = "select total_posts from package_types where package_type_id=?1",nativeQuery= true)
	public int getTotalPosts(int package_type_id);
}
