package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.All_Location;

@Repository
@EnableJpaRepositories
public interface All_Location_Repo extends JpaRepository<All_Location, Integer>{

}
