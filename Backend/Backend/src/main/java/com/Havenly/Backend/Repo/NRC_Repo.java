package com.Havenly.Backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Havenly.Backend.DTO.NRC_DTO;
import com.Havenly.Backend.Entity.NRC;

public interface NRC_Repo extends JpaRepository<NRC, Integer>{
	
}
