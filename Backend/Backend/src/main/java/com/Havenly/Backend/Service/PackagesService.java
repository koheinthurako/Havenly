package com.Havenly.Backend.Service;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.DTO.Packages_DD;

@Service
public interface PackagesService {

	boolean payment(String pay, String amount);
	boolean delete(Packages_DD pack);
	Packages_DD buyPack(String nrc, String packType, String amount);
}