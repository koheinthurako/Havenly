package com.Havenly.Backend.Service_Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.DTO.PackageTypesDTO;
import com.Havenly.Backend.Entity.PackageTypes;
import com.Havenly.Backend.Repo.PackageTypesRepo;
import com.Havenly.Backend.Service.PackageTypesService;
@Configuration
public class PackageTypesServiceImpl implements PackageTypesService{

	@Autowired
	PackageTypesRepo packTypesRepo;
	
	PackageTypesDTO dto = new PackageTypesDTO();
	
	@Override
	public List<PackageTypesDTO> getAll() {
		List<PackageTypes>	packTypes = packTypesRepo.findAll();
		List<PackageTypesDTO> packDTO = new ArrayList<PackageTypesDTO>();
		packDTO.clear();
		for(PackageTypes p : packTypes) {
			packDTO.add(dto.convertToObject(p));
		}
		return packDTO;
	}

	@Override
	public PackageTypesDTO save(PackageTypesDTO packDTO) {
		PackageTypes packTypes = dto.convertToEntity(packDTO);
		PackageTypes newPackTypes = packTypesRepo.save(packTypes);
		PackageTypesDTO packTypesDTO = dto.convertToObject(newPackTypes);
		return packTypesDTO;
	}


}
