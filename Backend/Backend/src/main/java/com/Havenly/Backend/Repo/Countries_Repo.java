package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.Countries;

@Repository
@EnableJpaRepositories
public interface Countries_Repo extends JpaRepository<Countries, Integer>{

}
