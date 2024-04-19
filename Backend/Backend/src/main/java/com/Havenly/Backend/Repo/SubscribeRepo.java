package com.Havenly.Backend.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import com.Havenly.Backend.entity.Subscribe;

public interface SubscribeRepo extends JpaRepository<Subscribe, Integer> {
	
	public Subscribe findByGmail(String gmail);
	
	public Subscribe findByName(String name);
	
	
}
