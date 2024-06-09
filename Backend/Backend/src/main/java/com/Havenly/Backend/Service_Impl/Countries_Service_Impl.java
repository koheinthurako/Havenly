package com.Havenly.Backend.Service_Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.Havenly.Backend.Service.Countries_Service;
import com.Havenly.Backend.DTO.Countries_DTO;
import com.Havenly.Backend.Entity.Countries;
import com.Havenly.Backend.Repo.Countries_Repo;

@Configuration
public class Countries_Service_Impl implements Countries_Service{
	
	@Autowired
	Countries_Repo countryRepo;
	
	Countries_DTO countriesDTO = new Countries_DTO();

	@Override
	public List<Countries_DTO> getAllCountries() {
		List<Countries> countries = countryRepo.findAll();
		List<Countries_DTO> countryDTO = new ArrayList<Countries_DTO>();
		countryDTO.clear();
		for(Countries country : countries) {
			countryDTO.add(countriesDTO.convertToObject(country));
		}
		return countryDTO;
	}

	@Override
	public List<Countries_DTO> saveCountry(List<Countries_DTO> countryDTO) {
		List<Countries> countries = new ArrayList<Countries>();
		for(Countries_DTO dto : countryDTO) {
			countries.add(countriesDTO.convertToEntity(dto));
		}
		List<Countries> countryLists = countryRepo.saveAll(countries);
		List<Countries_DTO> countryDTOLists = new ArrayList<Countries_DTO>();
		for(Countries country : countryLists) {
			countryDTOLists.add(countriesDTO.convertToObject(country));
		}
		return countryDTOLists;
	}
	
	

}
