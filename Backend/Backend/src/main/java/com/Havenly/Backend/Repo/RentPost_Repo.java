package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.RentPost;

@Repository
@EnableJpaRepositories
public interface RentPost_Repo extends JpaRepository<RentPost, String>{
	
	

}
