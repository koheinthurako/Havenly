package com.Havenly.Backend.DTO;

import com.Havenly.Backend.Entity.Countries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Countries_DTO {
	
	private int country_id;
	private String country_name;
	
	public Countries convertToEntity(Countries_DTO countryDTO) {
		Countries countries = new Countries();
		countries.setCountry_id(countryDTO.country_id);
		countries.setCountry_name(countryDTO.country_name);
		return countries;
	}
	
	public Countries_DTO convertToObject(Countries countryObj) {
		Countries_DTO countryDTO = new Countries_DTO();
		countryDTO.setCountry_id(countryObj.getCountry_id());
		countryDTO.setCountry_name(countryObj.getCountry_name());
		return countryDTO;
	}

}
