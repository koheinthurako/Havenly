package com.Havenly.Backend.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.entity.PackageTypes;
import com.Havenly.Backend.entity.Packages;
import com.Havenly.Backend.repo.PackageTypesRepo;
import com.Havenly.Backend.repo.PackagesRepo;

@Configuration
public class PackageServiceImpl implements PackageService{

	@Autowired
	PackageTypesRepo packTypesRepo;
	
	PackagesRepo packRepo;
	
	Packages pack; 
	@Override
	public Collection<PackageTypes> findAll() {
		// TODO Auto-generated method stub
		return packTypesRepo.findAll();
	}

	@Override
	public PackageTypes getById(int packageId) {
		// TODO Auto-generated method stub
		return packTypesRepo.findById(packageId).orElse(null);
	}
	
	@Override
	public PackageTypes getByName(String packName) {
		// TODO Auto-generated method stub
		return packTypesRepo.findByName(packName);
	}

	@Override
	public void delete(int pid) {
		pack = this.findById(pid);
		if(pack.equals(null)) {
			System.out.println("No such package.");
		}else {
			packRepo.delete(pack);
		System.out.println("Package profile deleted.");
		}
		 
	}

	@Override
	public boolean payment(String pay) {
		pack.setPaymentMethod(pay);
		return true;
	}

	@Override
	public Packages findById(int pid) {
		return packRepo.findById(pid).orElse(null);
	}

	

}
