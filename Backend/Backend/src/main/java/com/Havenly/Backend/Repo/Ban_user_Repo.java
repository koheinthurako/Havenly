package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.Ban_user;

@Repository
@EnableJpaRepositories
public interface Ban_user_Repo extends JpaRepository<Ban_user, Integer>{

	public Ban_user findByEmail(String email);

}
