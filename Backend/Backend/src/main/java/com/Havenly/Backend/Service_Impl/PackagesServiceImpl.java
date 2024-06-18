package com.Havenly.Backend.Service_Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


import com.Havenly.Backend.Service.PackagesService;
import com.Havenly.Backend.DTO.Packages_DD;
import com.Havenly.Backend.Entity.PackageTypes;
import com.Havenly.Backend.Entity.Packages;
import com.Havenly.Backend.Entity.Reg_user;
import com.Havenly.Backend.Entity.Subscription;
import com.Havenly.Backend.Repo.PackageTypesRepo;
import com.Havenly.Backend.Repo.PackagesRepo;
import com.Havenly.Backend.Repo.Reg_user_Repo;
import com.Havenly.Backend.Repo.SubscribeRepo;

@Configuration
public class PackagesServiceImpl implements PackagesService{

	@Autowired
	PackageTypesRepo packTypesRepo;
	@Autowired
	PackagesRepo packRepo;
	@Autowired
	SubscribeRepo subRepo;
	@Autowired
	Reg_user_Repo regRepo;

	Packages_DD pack_dd = new Packages_DD();

	@Override
	public boolean delete(Packages_DD pack) {
		Packages delPack = pack_dd.convertToEntity(pack);
		int delId = delPack.getPackageId();
		if(packRepo.existsById(delId)){
			packRepo.deleteById(delId);
		//Package profile deleted
		return true;
		}
		return false;
		 
	}

	@Override
	public boolean payment(String packType, String amount) {
		String total = packTypesRepo.getPriceByPackName(packType);
		
		if(amount.equals(total)) {
			return true;
		}
		return false;
	}


	@Override
	public Packages_DD buyPack(String email, String packType, String amount) {
		Reg_user reg = regRepo.findByEmail(email);
		Subscription subUser = subRepo.findByNrc(reg.getSub().getNrc());
		PackageTypes packTypes = packTypesRepo.findByPackName(packType);
		Packages packUser = subUser.getPackages();
		
		if(packUser==null) {
			return null;
		}
		if(!(this.payment(packType, amount))) {
			return null;
		}		

		packUser.setPackType(packTypes);
		packUser.setPackDate(LocalDate.now());
		packUser.setPackTime(LocalDateTime.now());	
		packUser.setPayment("OK");
		packUser.setAvailPosts(packTypes.getTotal_posts());
		packUser.setAvailAds(packTypes.getTotal_ads());
		
		Packages packUser2 = packRepo.save(packUser);
			
		subUser.setPackages(packUser2);
		subUser.setNrc(subUser.getNrc());
		subRepo.save(subUser);
		
		Packages_DD packUser3 = pack_dd.convertToObject(packUser2);
		return packUser3;
		
	}

	@Override
	public Packages_DD showPackage(Packages_DD dto) {
		Packages packUser = pack_dd.convertToEntity(dto);
		
		Packages_DD packUser2 = pack_dd.convertToObject(packUser);
		return packUser2;
	}


	

}