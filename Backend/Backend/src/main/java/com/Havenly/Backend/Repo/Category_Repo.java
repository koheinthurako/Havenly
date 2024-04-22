package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.Category;

@Repository
@EnableJpaRepositories
public interface Category_Repo extends JpaRepository<Category, Long>{
	
	

}
