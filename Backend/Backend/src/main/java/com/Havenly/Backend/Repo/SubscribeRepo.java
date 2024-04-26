package com.Havenly.Backend.Repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Havenly.Backend.Entity.Subscription;

public interface SubscribeRepo extends JpaRepository<Subscription, Integer> {
	
	public Subscription findByNrc(String nrc);
	public Subscription findByEmail(String email);
}
