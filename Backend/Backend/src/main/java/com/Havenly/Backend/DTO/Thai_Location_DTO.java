package com.Havenly.Backend.DTO;

import com.Havenly.Backend.Entity.All_Location;
import com.Havenly.Backend.Entity.Thai_Location;

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
public class Thai_Location_DTO {
	
	private int thai_location_id;
	private String province;
	private String amphoe;
	private String region;
	private String latitude;
	private String longitude;
	private All_Location all_locations;
	
	public Thai_Location convertToEntity(Thai_Location_DTO thaiLocationDTO) {
		Thai_Location thaiLocation = new Thai_Location();
		thaiLocation.setAll_location(thaiLocationDTO.all_locations);
		thaiLocation.setThai_location_id(thaiLocationDTO.thai_location_id);
		thaiLocation.setProvince(thaiLocationDTO.getProvince());
		thaiLocation.setAmphoe(thaiLocationDTO.getAmphoe());
		thaiLocation.setRegion(thaiLocationDTO.getRegion());
		thaiLocation.setLatitude(thaiLocationDTO.getLatitude());
		thaiLocation.setLongitude(thaiLocationDTO.getLongitude());
		return thaiLocation;
	}
	
	public Thai_Location_DTO convertToObject(Thai_Location thaiLocations) {
		Thai_Location_DTO thaiLocationsDTO = new Thai_Location_DTO();
		thaiLocationsDTO.setAll_locations(thaiLocations.getAll_location());;
		thaiLocationsDTO.setThai_location_id(thaiLocations.getThai_location_id());
		thaiLocationsDTO.setProvince(thaiLocations.getProvince());
		thaiLocationsDTO.setAmphoe(thaiLocations.getAmphoe());
		thaiLocationsDTO.setRegion(thaiLocations.getRegion());
		thaiLocationsDTO.setLatitude(thaiLocations.getLatitude());
		thaiLocationsDTO.setLongitude(thaiLocations.getLongitude());
		return thaiLocationsDTO;
	}

}
