package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.Reg_user;

import jakarta.transaction.Transactional;

@Repository
@EnableJpaRepositories
public interface Reg_user_Repo extends JpaRepository<Reg_user, Integer>{
	public Reg_user findByEmail(String gmail);
	
	@Transactional
	public String deleteByEmail(String eamil);
}

	