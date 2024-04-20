package com.Havenly.Backend.Service;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.Entity.PackageTypes;
import com.Havenly.Backend.Entity.Packages;

@Service
public interface PackageService {

	Packages findById(int pid);
	boolean payment(String pay);
	void delete(int packId);
	PackageTypes defaultValue();
	/*
	 * public int freeTrial(Packages pack); public int normal(Packages pack); public
	 * int premium(Packages pack);
	 */
}
