package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.Thai_Location;

@Repository
@EnableJpaRepositories
public interface Thai_Location_Repo extends JpaRepository<Thai_Location, Integer>{

}
