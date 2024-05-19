package com.Havenly.Backend.Service_Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.Havenly.Backend.DTO.Subscription_DTO;
import com.Havenly.Backend.Entity.PackageTypes;
import com.Havenly.Backend.Entity.Packages;
import com.Havenly.Backend.Entity.Reg_user;
import com.Havenly.Backend.Entity.Subscription;
import com.Havenly.Backend.Repo.PackageTypesRepo;
import com.Havenly.Backend.Repo.PackagesRepo;
import com.Havenly.Backend.Repo.Reg_user_Repo;
import com.Havenly.Backend.Repo.SubscribeRepo;
import com.Havenly.Backend.Service.SubscriptionService;

@Configuration
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	SubscribeRepo subscribeRepo;
	@Autowired
	PackagesRepo packRepo;
	@Autowired
	Reg_user_Repo regRepo;
	@Autowired
	PackageTypesRepo packTypesRepo;

//	@Override
//	public Subscription_DTO getById(Subscription_DTO dto) {
//		Subscription sub = subUser.convertToEntity(dto);
//		Subscription user = subscribeRepo.findById(sub.getSubUserId()).orElse(null);
//		Subscription_DTO user2 = subUser.convertToObject(user);
//		return user2;
//	}

	@Override
	public boolean cancel(Subscription_DTO dto) {
		Subscription sub = subscribeRepo.findByNrc(dto.getNrc());
		if (sub.equals(null)) {
			return false;
		} else {
			subscribeRepo.delete(sub);
			packRepo.delete(sub.getPackages());
			;
			return true;
		}

	}

	@Override
	public Subscription_DTO subscribe(Subscription_DTO dto) {
		Subscription_DTO subUser = new Subscription_DTO();
		Subscription sub = subUser.convertToEntity(dto);
		String email = dto.getEmail();
		String packName = dto.getPackageType();
		System.out.println("PackName : " + packName);
		Reg_user reg_user = regRepo.findByEmail(email);

//		if(regRepo.findByEmail(dto.getEmail())==null){
//		return null;
//		}else {		
		sub.setNrc(sub.getNrc());
		sub.setReg_user(reg_user);
		Packages packUser = new Packages();
		PackageTypes packTypes = packTypesRepo.findByPackName(packName);
		packUser.setSub1(sub);
		packUser.setPackType(packTypes);
		packUser.setAvailPosts(packTypes.getTotal_posts());
		packUser.setAvailAds(packTypes.getTotal_ads());
		packUser.setPackDate(LocalDate.now());
		packUser.setPackTime(LocalDateTime.now());

		Packages packUser2 = packRepo.save(packUser);

		sub.setPackages(packUser2);

		Subscription user = subscribeRepo.save(sub);
		Subscription_DTO user2 = subUser.convertToObject(user);

		return user2;
		// }

	}

}
