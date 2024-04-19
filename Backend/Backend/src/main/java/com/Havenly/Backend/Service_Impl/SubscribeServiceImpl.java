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
import com.Havenly.Backend.Repo.SubscribeRepo;
import com.Havenly.Backend.Service.SubscribeService;

@Configuration
public class SubscribeServiceImpl implements SubscribeService{

	@Autowired
	SubscribeRepo subscribeRepo;
	PackagesRepo packRepo;
	Subscription_DTO subUser;
	Packages pack;
	PackageTypes packType;
	


//	@Override
//	public Subscription_DTO getById(Subscription_DTO dto) {
//		Subscription sub = subUser.convertToEntity(dto);
//		Subscription user = subscribeRepo.findById(sub.getSubUserId()).orElse(null);
//		Subscription_DTO user2 = subUser.convertToObject(user);
//		return user2;
//	}


	@Override
	public String cancel(int sid) {
		Subscription sub = subscribeRepo.findById(sid).orElse(null);
		sub.setSubEndDate(LocalDate.now());
		sub.setSubEndTime(LocalDateTime.now());
		if(sub.equals(null)) {
			return "User is not subscribed.";
		}else {
					subscribeRepo.delete(sub);
					packRepo.delete(sub.getPackages());;
					return "No Longer Subscribed.";
		}
		
	}

	@Override
	public Subscription_DTO freeTrial(String nrc, int reg_id, int pid) {
		Subscription user = subscribeRepo.findByNrc(nrc);
		subUser.setTotalAds(packType.getTotal_ads());
		subUser.setTotalAds(packType.getTotal_posts());
		return subscribeRepo.save(subUser);
	
	}
	
	@Override
	public Subscription_DTO subNormal(String nrc, int reg_id, int pid) {
		subUser.setTotalAds(packType.getTotal_ads());
		subUser.setTotalAds(packType.getTotal_posts());
		return subscribeRepo.save(subUser);
		
	}

	@Override
	public Subscription_DTO subPremium(String nrc, int reg_id, int pid) {
		subUser.setTotalAds(packType.getTotal_posts());
		subUser.setTotalAds(packType.getTotal_posts());
		return subscribeRepo.save(subUser);
	}

	@Override
	public Subscription_DTO subscribe(String nrc, int packId) {
		Subscription_DTO user = subscribeRepo.findByNrc(nrc);
		if (user.equals(null)) {
		user.setSubStartDate(LocalDate.now());
		user.setSubStartTime(LocalDateTime.now());
		user.setNrc(nrc);
			if(packId==1) {			}else if(packId==2) {
			}else {
		}
		}else {
			user.setTotalPosts(user.getTotalPosts()+packType.getTotal_posts());
			user.setTotalAds(user.getTotalAds()+packType.getTotal_ads());
		}
		return subscribeRepo.save(user);
	}

}
