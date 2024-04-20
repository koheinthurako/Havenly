package com.Havenly.Backend.Service;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.DTO.Subscription_DTO;

@Service
public interface SubscribeService {

	String cancel(int subId);
	Subscription_DTO subscribe(String nrc, int packId);
	Subscription_DTO freeTrial(Subscription_DTO dto, String pName);
	Subscription_DTO subNormal(Subscription_DTO dto, String pName);
	Subscription_DTO subPremium(Subscription_DTO dto, String pName);

}
