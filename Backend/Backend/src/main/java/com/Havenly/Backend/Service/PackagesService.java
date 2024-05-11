package com.Havenly.Backend.Service;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.DTO.Packages_DD;
import com.Havenly.Backend.DTO.Packages_DTO;
import com.Havenly.Backend.Entity.Packages;

@Service
public interface PackagesService {

	Packages_DTO showPackage(Packages_DTO pack);
	boolean payment(String pay, int amount);
	boolean delete(Packages pack);
	Packages_DD buyPack(String nrc, String packType, int amount);
}