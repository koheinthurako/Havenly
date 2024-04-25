package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.TestPhoto;

@Repository
@EnableJpaRepositories
public interface TestPhotoRepo extends JpaRepository<TestPhoto, Long>{

}
