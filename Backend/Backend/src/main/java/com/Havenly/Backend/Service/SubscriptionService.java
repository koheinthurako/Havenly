package com.Havenly.Backend.Service;

import com.Havenly.Backend.Entity.Subscription;
import org.springframework.stereotype.Service;

import com.Havenly.Backend.DTO.Subscription_DTO;

import java.util.List;

@Service
public interface SubscriptionService {

	boolean cancel(Subscription_DTO dto);
	Subscription_DTO subscribe(Subscription_DTO dto);
	List<Subscription> getAllSubUserInfo();

}
