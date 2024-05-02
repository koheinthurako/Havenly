package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.DTO.PackageTypesDTO;
@Service
public interface PackageTypesService {

	public List<PackageTypesDTO> getAll();
	public PackageTypesDTO save(PackageTypesDTO packDTO);
}
