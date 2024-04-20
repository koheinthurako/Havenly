package com.Havenly.Backend.Service_Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.DTO.Subscription_DTO;
import com.Havenly.Backend.Entity.Subscription;
import com.Havenly.Backend.Repo.PackageTypesRepo;
import com.Havenly.Backend.Repo.PackagesRepo;
import com.Havenly.Backend.Repo.SubscribeRepo;
import com.Havenly.Backend.Service.SubscribeService;

@Configuration
public class SubscribeServiceImpl implements SubscribeService{

	@Autowired
	SubscribeRepo subscribeRepo;
	@Autowired
	PackagesRepo packRepo;
	
	Subscription_DTO subUser = new Subscription_DTO();
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
	public String cancel(Subscription_DTO dto) {
		Subscription sub = subscribeRepo.findByNrc(dto.getNrc());
		if(sub.equals(null)) {
			return "User is not subscribed.";
		}else {
					subscribeRepo.delete(sub);
					packRepo.delete(sub.getPackages());;
					return "No Longer Subscribed.";
		}
		
	}

	@Override
	public Subscription_DTO freeTrial(Subscription_DTO dto) {
		Subscription sub = subUser.convertToEntity(dto);
		sub.setTotalAds(2);
		sub.setTotalPosts(3);
		sub.setName(dto.getName());
		sub.setPackageType(dto.getPackageType());
		sub.setNrc(dto.getNrc());
		Subscription user = subscribeRepo.save(sub);
		Subscription_DTO user2 = subUser.convertToObject(user);
		return user2;
	
	}
	
	@Override
	public Subscription_DTO subNormal(Subscription_DTO dto) {
		Subscription sub = subUser.convertToEntity(dto);
		sub.setTotalAds(10);
		sub.setTotalPosts(15);
		sub.setName(dto.getName());
		sub.setPackageType(dto.getPackageType());
		sub.setNrc(dto.getNrc());
		Subscription user = subscribeRepo.save(sub);
		Subscription_DTO user2 = subUser.convertToObject(user);
		return user2;		
	}

	@Override
	public Subscription_DTO subPremium(Subscription_DTO dto) {
		Subscription sub = subUser.convertToEntity(dto);
		sub.setTotalAds(20);
		sub.setTotalPosts(30);
		sub.setName(dto.getName());
		sub.setPackageType(dto.getPackageType());
		sub.setNrc(dto.getNrc());
		Subscription user = subscribeRepo.save(sub);
		Subscription_DTO user2 = subUser.convertToObject(user);
		return user2;
	}
//
//	@Override
//	public Subscription_DTO subscribe(Subscription_DTO dto) {
//		Subscription sub = subUser.convertToEntity(dto);
//		sub.setTotalAds(2);
//		sub.setTotalPosts(3);
//		sub.setNrc(dto.getNrc());
//		Subscription user = subscribeRepo.save(sub);
//		Subscription_DTO user2 = subUser.convertToObject(user);
//		return user2;
//	}

}
