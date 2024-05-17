package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.Myanmar_Location;

@Repository
@EnableJpaRepositories
public interface Myanmar_Location_Repo extends JpaRepository<Myanmar_Location, Integer>{
	
	

}