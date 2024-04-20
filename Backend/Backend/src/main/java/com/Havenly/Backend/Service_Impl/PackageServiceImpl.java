package com.Havenly.Backend.Service_Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.Entity.PackageTypes;
import com.Havenly.Backend.Entity.Packages;
import com.Havenly.Backend.Repo.PackageTypesRepo;
import com.Havenly.Backend.Repo.PackagesRepo;
import com.Havenly.Backend.Service.PackageService;

@Configuration
public class PackageServiceImpl implements PackageService{

	@Autowired
	PackageTypesRepo packTypesRepo;
	@Autowired
	PackagesRepo packRepo;
	
	Packages pack = new Packages(); 
	

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
		pack.setPayment(pay);
		return true;
	}

	@Override
	public Packages findById(int pid) {
		return packRepo.findById(pid).orElse(null);
	}

	@Override
	public PackageTypes defaultValue() {
		// TODO Auto-generated method stub
		return packTypesRepo.insertValues();
	}

	

}
