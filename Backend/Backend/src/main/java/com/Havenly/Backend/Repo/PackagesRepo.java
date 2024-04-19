package com.Havenly.Backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Havenly.Backend.entity.Packages;

public interface PackagesRepo extends JpaRepository<Packages, Integer> {

}
