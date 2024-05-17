package com.Havenly.Backend.DTO;

import com.Havenly.Backend.Entity.All_Location;

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
public class All_Location_DTO {
	
	private int all_location_id;
	private String country;
	
	public All_Location convertToEntity(All_Location_DTO allLocationsDTO) {
		All_Location allLocations = new All_Location();
		allLocations.setAll_location_id(allLocationsDTO.all_location_id);
		allLocations.setCountry(allLocationsDTO.getCountry());
		
		return allLocations;
	}
	
	public All_Location_DTO convertToObject(All_Location allLocations) {
		All_Location_DTO allLocationsDTO = new All_Location_DTO();
		allLocationsDTO.setAll_location_id(allLocations.getAll_location_id());
		allLocationsDTO.setCountry(allLocations.getCountry());
		return allLocationsDTO;
	}

}