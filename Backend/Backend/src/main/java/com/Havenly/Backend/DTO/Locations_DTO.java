package com.Havenly.Backend.DTO;

import com.Havenly.Backend.Entity.Countries;
import com.Havenly.Backend.Entity.Locations;

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
public class Locations_DTO {
	
	private int location_id;
	private String province;
	private String amphoe;
	private String region;
	private String latitude;
	private String longitude;
	private Countries countries;
	private String country_name;
	
	public Locations convertToEntity(Locations_DTO locationDTO) {
		Locations location = new Locations();
		location.setCountries(locationDTO.countries);
		location.setLocation_id(locationDTO.location_id);
		location.setProvince(locationDTO.province);
		location.setAmphoe(locationDTO.amphoe);
		location.setRegion(locationDTO.region);
		location.setLatitude(locationDTO.latitude);
		location.setLongitude(locationDTO.longitude);
		return location;
	}
	
	public Locations_DTO convertToObject(Locations location) {
		Locations_DTO locationDTO = new Locations_DTO();
		locationDTO.setCountries(location.getCountries());
//		locationDTO.setCountry_name(countries.getCountry_name());
		if (location.getCountries() != null) {
	        locationDTO.setCountry_name(location.getCountries().getCountry_name());
	    }
		locationDTO.setLocation_id(location.getLocation_id());
		locationDTO.setProvince(location.getProvince());
		locationDTO.setAmphoe(location.getAmphoe());
		locationDTO.setRegion(location.getRegion());
		locationDTO.setLatitude(location.getLatitude());
		locationDTO.setLongitude(location.getLongitude());
		return locationDTO;
	}

}
