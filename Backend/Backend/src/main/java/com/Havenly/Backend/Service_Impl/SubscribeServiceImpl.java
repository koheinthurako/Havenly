package com.Havenly.Backend.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.entity.PackageTypes;
import com.Havenly.Backend.entity.Packages;
import com.Havenly.Backend.entity.Subscribe;
import com.Havenly.Backend.entity.UserStatus;
import com.Havenly.Backend.repo.PackageTypesRepo;
import com.Havenly.Backend.repo.SubscribeRepo;

@Configuration
public class SubscribeServiceImpl implements SubscribeService{

	@Autowired
	SubscribeRepo subscribeRepo;
	PackageTypesRepo packRepo;
	Subscribe subUser;
	Packages pack;
	PackageTypes packType;
	
	@Override
	public Collection<Subscribe> findAll() {
		// TODO Auto-generated method stub
		return subscribeRepo.findAll();
	}

	@Override
	public Subscribe getById(int id) {
		return subscribeRepo.findById(id).orElse(null);
	}


	@Override
	public String cancel(int id) {
		Subscribe sub = this.getById(id);
		sub.setSubEndDate(LocalDate.now());
		sub.setSubEndTime(LocalDateTime.now());
		sub.setStatus(UserStatus.registered);
		if(sub.equals(null)) {
			return "User is not subscribed.";
		}else {
					subscribeRepo.delete(sub);
					packRepo.deleteById(sub.getPackageId());
					return "No Longer Subscribed.";
		}
		
	}

	@Override
	public Subscribe freeTrial() {
		pack.setPackageTypeId(1);
		subUser.setStatus(UserStatus.freetrial);
		subUser.setTotalAds(packRepo.getTotalAds("freeTrial"));
		subUser.setTotalAds(packRepo.getTotalPosts("freeTrial"));
		return subscribeRepo.save(subUser);
	
	}
	
	@Override
	public Subscribe subNormal() {
		pack.setPackageTypeId(2);
		subUser.setStatus(UserStatus.subscribed);
		subUser.setTotalAds(packRepo.getTotalAds("Normal"));
		subUser.setTotalAds(packRepo.getTotalPosts("Normal"));
		return subscribeRepo.save(subUser);
		
	}

	@Override
	public Subscribe subPremium() {
		pack.setPackageTypeId(3);
		subUser.setStatus(UserStatus.premium);
		subUser.setTotalAds(packRepo.getTotalAds("Premium"));
		subUser.setTotalAds(packRepo.getTotalPosts("Premium"));
		return subscribeRepo.save(subUser);
	}

	@Override
	public Subscribe subscribe(String email, int packId) {
		PackageService ps = null;
		Subscribe user = subscribeRepo.findByGmail(email);
		if (user.equals(null) && ps.payment(null)) {
		user.setSubStartDate(LocalDate.now());
		user.setSubStartTime(LocalDateTime.now());
			if(packId==1) {
				freeTrial();
			}else if(packId==2) {
				subNormal();
			}else {
				subPremium();
			}
		}else {
			pack.setPackageTypeId(packId);
			user.setTotalPosts(user.getTotalPosts()+packType.getTotal_posts());
			user.setTotalAds(user.getTotalAds()+packType.getTotal_ads());
		}
		return subscribeRepo.save(user);
	}

}
