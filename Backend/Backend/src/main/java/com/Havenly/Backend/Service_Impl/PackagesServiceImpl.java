package com.Havenly.Backend.Service_Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

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

	Packages pack = new Packages(); 
	Packages_DTO pack_dto = new Packages_DTO();

	@Override
	public boolean delete(int pid) {
		pack = this.findById(pid);
		if(pack.equals(null)) {
			System.out.println("No such package.");
			return false;
		}else {
			packRepo.delete(pack);
		System.out.println("Package profile deleted.");
		return true;
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
	public Packages_DTO buyPack(String nrc, Packages_DTO dto) {
		Subscription subUser = subRepo.findByNrc(nrc);
		Packages packUser = pack_dto.convertToEntity(dto);
		PackageTypes packTypes = new PackageTypes();
		if(dto.getPackType().matches("Free Trial")) {
			subUser.setTotalAds(packTypesRepo.getTotalAds(1));
			subUser.setTotalPosts(packTypesRepo.getTotalPosts(1));
			}
		if(dto.getPackType().matches("Normal")) {
				subUser.setTotalAds(packTypesRepo.getTotalAds(2));
				subUser.setTotalPosts(packTypesRepo.getTotalPosts(2));
			}
		if(dto.getPackType().matches("Premium")) {
				subUser.setTotalAds(packTypesRepo.getTotalAds(3));
				subUser.setTotalPosts(packTypesRepo.getTotalPosts(3));
			}
		packTypes.setPackName(packUser.getPackType().toString());
		packUser.setPackType(packTypes);
		subUser.setPackages(packUser);
		subUser.setEmail(subUser.getEmail());
		subUser.setNrc(subUser.getNrc());
		packUser.getSub1();
		packUser.setSub1(subUser);
		subRepo.save(subUser);
		Packages packUser2 = packRepo.save(packUser);
		Packages_DTO packUser3 = pack_dto.convertToObject(packUser2);
		return packUser3;
	}

	@Override
	public Packages_DTO showPackage(Packages_DTO dto) {
		Packages packUser = pack_dto.convertToEntity(dto);
		Packages packUser1 = this.findById(packUser.getPackageId());
		Packages_DTO packUser2 = pack_dto.convertToObject(packUser1);
		return packUser2;
	}


	

}
