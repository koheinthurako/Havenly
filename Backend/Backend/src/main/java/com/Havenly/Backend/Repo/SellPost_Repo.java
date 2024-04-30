package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.SellPost;

@Repository
@EnableJpaRepositories
public interface SellPost_Repo extends JpaRepository<SellPost, String>{
	
	
	
}
