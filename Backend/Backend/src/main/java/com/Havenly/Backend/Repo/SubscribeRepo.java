package com.Havenly.Backend.Repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Havenly.Backend.DTO.Subscription_DTO;
import com.Havenly.Backend.Entity.Subscription;

public interface SubscribeRepo extends JpaRepository<Subscription, Integer> {
	
	public Subscription findByGmail(String gmail);
	
	public Subscription findByName(String name);
	
	public Subscription findByNrc(String nrc);
}
