package com.Havenly.Backend.Service_Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.Service.NRC_Service;
import com.Havenly.Backend.DTO.NRC_DTO;
import com.Havenly.Backend.Entity.NRC;
import com.Havenly.Backend.Repo.NRC_Repo;
@Configuration
public class NRC_Service_Impl implements NRC_Service{

	@Autowired
	NRC_Repo nrcRepo;
	
	NRC_DTO dto = new NRC_DTO();
	
	@Override
	public List<NRC_DTO> getAll() {
		List<NRC> nrc = nrcRepo.findAll();
		List<NRC_DTO> nrcDTO = new ArrayList<NRC_DTO>();
		nrcDTO.clear();
		for(NRC n : nrc) {
			nrcDTO.add(dto.convertToObject(n));
		}
		return nrcDTO;
	}

	@Override
	public List<NRC_DTO> saveAll(List<NRC_DTO> nrc_dto) {
		List<NRC> nrc = new ArrayList<NRC>();
		for(NRC_DTO nrcdto : nrc_dto) {
			nrc.add(dto.convertToEntity(nrcdto));
		}
		List<NRC> nrcList = nrcRepo.saveAll(nrc);
		List<NRC_DTO> nrc_dto_list = new ArrayList<NRC_DTO>();
		for(NRC n : nrcList) {
			nrc_dto_list.add(dto.convertToObject(n));
		}
		return nrc_dto_list;
	}

}
