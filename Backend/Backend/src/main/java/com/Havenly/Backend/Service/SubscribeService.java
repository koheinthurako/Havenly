package com.Havenly.Backend.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.entity.Subscribe;

@Service
public interface SubscribeService {

	Collection <Subscribe> findAll();
	
	Subscribe getById(int subId);

	String cancel(int subId);
	Subscribe subscribe(String email, int packId);
	Subscribe freeTrial();
	Subscribe subNormal();
	Subscribe subPremium();

}
