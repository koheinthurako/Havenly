package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Havenly.Backend.Entity.PackageTypes;

public interface PackageTypesRepo extends JpaRepository<PackageTypes, Integer> {
	
	
	public PackageTypes findByPackName(String packName);
	
	@Query(value = "SELECT price FROM package_types WHERE package_name = ?1", nativeQuery = true)
	public int getPriceByPackName(String packName);
	
}
