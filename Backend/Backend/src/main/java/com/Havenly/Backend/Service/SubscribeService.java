package com.Havenly.Backend.Service;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.DTO.Subscription_DTO;

@Service
public interface SubscribeService {

	String cancel(Subscription_DTO dto);
	//Subscription_DTO subscribe(Subscription_DTO dto);
	Subscription_DTO freeTrial(Subscription_DTO dto);
	Subscription_DTO subNormal(Subscription_DTO dto);
	Subscription_DTO subPremium(Subscription_DTO dto);

}
