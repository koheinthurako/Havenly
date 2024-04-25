package com.Havenly.Backend.DTO;

import com.Havenly.Backend.Entity.All_Location;
import com.Havenly.Backend.Entity.Myanmar_Location;

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
public class Myanmar_Location_DTO {
	
	private int myanmar_location_id;
	private String province;
	private String amphoe;
	private String region;
	private String latitude;
	private String longitude;
	private All_Location all_locations;
	
	public Myanmar_Location convertToEntity(Myanmar_Location_DTO myanLocationDTO) {
		Myanmar_Location myanLocation = new Myanmar_Location();
		myanLocation.setAll_location(myanLocationDTO.all_locations);
		myanLocation.setMyanmar_location_id(myanLocationDTO.myanmar_location_id);
		myanLocation.setProvince(myanLocationDTO.getProvince());
		myanLocation.setAmphoe(myanLocationDTO.getAmphoe());
		myanLocation.setRegion(myanLocationDTO.getRegion());
		myanLocation.setLatitude(myanLocationDTO.getLatitude());
		myanLocation.setLongitude(myanLocationDTO.getLongitude());
		return myanLocation;
	}
	
	public Myanmar_Location_DTO convertToObject(Myanmar_Location myanmarLocations) {
		Myanmar_Location_DTO myanLocationDTO = new Myanmar_Location_DTO();
		myanLocationDTO.setAll_locations(myanmarLocations.getAll_location());;
		myanLocationDTO.setMyanmar_location_id(myanmarLocations.getMyanmar_location_id());
		myanLocationDTO.setProvince(myanmarLocations.getProvince());
		myanLocationDTO.setAmphoe(myanmarLocations.getAmphoe());
		myanLocationDTO.setRegion(myanmarLocations.getRegion());
		myanLocationDTO.setLatitude(myanmarLocations.getLatitude());
		myanLocationDTO.setLongitude(myanmarLocations.getLongitude());
		
		return myanLocationDTO;
	}

}
