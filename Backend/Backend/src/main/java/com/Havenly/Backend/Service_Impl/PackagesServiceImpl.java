package com.Havenly.Backend.Service_Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.DTO.Packages_DD;
import com.Havenly.Backend.DTO.Packages_DTO;
import com.Havenly.Backend.Entity.PackageTypes;
import com.Havenly.Backend.Entity.Packages;
import com.Havenly.Backend.Entity.Subscription;
import com.Havenly.Backend.Repo.PackageTypesRepo;
import com.Havenly.Backend.Repo.PackagesRepo;
import com.Havenly.Backend.Repo.SubscribeRepo;
import com.Havenly.Backend.Service.PackagesService;

@Configuration
public class PackagesServiceImpl implements PackagesService{

	@Autowired
	PackageTypesRepo packTypesRepo;
	@Autowired
	PackagesRepo packRepo;
	@Autowired
	SubscribeRepo subRepo;

	Packages_DD pack_dd = new Packages_DD();
	Packages_DTO pack_dto = new Packages_DTO();

	@Override
	public boolean delete(Packages pack) {
		
		if(pack.equals(null)) {
			//No such package
			return false;
		}else {
			packRepo.deleteById(pack.getPackageId());
		//Package profile deleted
		return true;
		}
		 
	}

	@Override
	public boolean payment(String packType, int amount) {
		int total = packTypesRepo.getPriceByPackName(packType);
		
		if(amount == total) {
			return true;
		}
		return false;
	}


	@Override
	public Packages_DD buyPack(String email, String packType, int amount) {
		Subscription subUser = subRepo.findByEmail(email);
		PackageTypes packTypes = packTypesRepo.findByPackName(packType);
		Packages packUser = subUser.getPackages();
		if(packUser==null) {
			return null;
		}
		if(!(this.payment(packType, amount))) {
			return null;
		}		
//		packUser.setSub1(subUser);
		packUser.setPackType(packTypes);
		packUser.setPackDate(LocalDate.now());
		packUser.setPackTime(LocalDateTime.now());	
		packUser.setPayment("OK");
		
		Packages packUser2 = packRepo.save(packUser);
			
		subUser.setPackages(packUser2);
		subUser.setEmail(subUser.getEmail());
		subUser.setNrc(subUser.getNrc());
		subUser.setPackageType(packType);
		subRepo.save(subUser);
		
		Packages_DD packUser3 = pack_dd.convertToObject(packUser2);
		return packUser3;
		
	}

	@Override
	public Packages_DTO showPackage(Packages_DTO dto) {
		Packages packUser = pack_dto.convertToEntity(dto);
		
		Packages_DTO packUser2 = pack_dto.convertToObject(packUser);
		return packUser2;
	}


	

}
