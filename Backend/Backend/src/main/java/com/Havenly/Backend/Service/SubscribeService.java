package com.Havenly.Backend.Service;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.DTO.Subscription_DTO;

@Service
public interface SubscribeService {


	
	Subscription_DTO getById(Subscription_DTO dto);

	String cancel(int subId);
	Subscription_DTO subscribe(String nrc, int packId);
	Subscription_DTO freeTrial(String nrc, int reg_id, int pid);
	Subscription_DTO subNormal(String nrc, int reg_id,int pid);
	Subscription_DTO subPremium(String nrc, int reg_id, int pid);

}
