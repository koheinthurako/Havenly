package com.Havenly.Backend.Service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.Entity.PackageTypes;
import com.Havenly.Backend.Entity.Packages;

@Service
public interface PackageService {
	Collection <PackageTypes> findAll();
	PackageTypes getById(int packageId);
	PackageTypes getByName(String packName);
	Packages findById(int pid);
	boolean payment(String pay);
	void delete(int packId);
	/*
	 * public int freeTrial(Packages pack); public int normal(Packages pack); public
	 * int premium(Packages pack);
	 */
}
