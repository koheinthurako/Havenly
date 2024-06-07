package com.Havenly.Backend.Service_Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.Service.PackageTypesService;
import com.Havenly.Backend.DTO.PackageTypesDTO;
import com.Havenly.Backend.Entity.PackageTypes;
import com.Havenly.Backend.Repo.PackageTypesRepo;
@Configuration
public class PackageTypesServiceImpl implements PackageTypesService{

	@Autowired
	PackageTypesRepo packTypesRepo;
	
	PackageTypesDTO packageTypeDTO = new PackageTypesDTO();
	
	@Override
	public List<PackageTypesDTO> getAll() {
		List<PackageTypes>	packTypes = packTypesRepo.findAll();
		List<PackageTypesDTO> packDTO = new ArrayList<PackageTypesDTO>();
		packDTO.clear();
		for(PackageTypes p : packTypes) {
			packDTO.add(packageTypeDTO.convertToObject(p));
		}
		return packDTO;
	}

	@Override
	public List<PackageTypesDTO> saveAll(List<PackageTypesDTO> packDTO) {
//		PackageTypes packTypes = dto.convertToEntity(packDTO);
//		PackageTypes newPackTypes = packTypesRepo.save(packTypes);
//		PackageTypesDTO packTypesDTO = dto.convertToObject(newPackTypes);
		List<PackageTypes> packageTypes = new ArrayList<PackageTypes>();
		for(PackageTypesDTO dto : packDTO) {
			packageTypes.add(packageTypeDTO.convertToEntity(dto));
		}
		List<PackageTypes> packageTypeLists = packTypesRepo.saveAll(packageTypes);
		List<PackageTypesDTO> packDTOLists = new ArrayList<PackageTypesDTO>();
		for(PackageTypes pack: packageTypeLists) {
			packDTOLists.add(packageTypeDTO.convertToObject(pack));
		}
		
		return packDTOLists;
	}


}
