package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.DTO.Countries_DTO;

@Service
public interface Countries_Service {
	
	public List<Countries_DTO> getAllCountries();
	public List<Countries_DTO> saveCountry(List<Countries_DTO> countryDTO);

}
