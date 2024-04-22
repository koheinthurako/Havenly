package com.Havenly.Backend.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Myanmar_Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int myanmar_location_id;
	private String province;
	private String amphoe;
	private String region;
	private int latitude;
	private int longitude;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "all_location_id", referencedColumnName = "all_location_id")
	All_Location all_locations;

}
