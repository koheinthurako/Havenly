package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Havenly.Backend.Entity.PackageTypes;

public interface PackageTypesRepo extends JpaRepository<PackageTypes, Integer> {
	
	
	public PackageTypes findByPackName(String packName);
	
}
