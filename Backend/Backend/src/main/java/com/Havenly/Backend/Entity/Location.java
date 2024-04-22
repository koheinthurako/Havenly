package com.Havenly.Backend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int location_id;
//	private String province;
//	private String amphoe;
//	private String district;
//	private String zipcode;
	
	

}
