package com.Havenly.Backend.Service;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.DTO.Packages_DTO;
import com.Havenly.Backend.Entity.Packages;

@Service
public interface PackagesService {

	Packages findById(int pid);
	Packages_DTO showPackage(Packages_DTO pack);
	boolean payment(String pay);
	boolean delete(int packId);
	Packages_DTO buyPack(String nrc, String packType);
}