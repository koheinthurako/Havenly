package com.Havenly.Backend.Service;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.DTO.Subscription_DTO;

@Service
public interface SubscriptionService {

	boolean cancel(Subscription_DTO dto);
	Subscription_DTO subscribe(Subscription_DTO dto);
	

}
