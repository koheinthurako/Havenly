package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.PasswordResetToken;


@Repository
@EnableJpaRepositories
public interface TokenRepository extends JpaRepository<PasswordResetToken, Integer>{

	public PasswordResetToken findByToken(String token);
}
