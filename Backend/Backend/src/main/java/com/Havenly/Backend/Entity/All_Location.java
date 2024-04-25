package com.Havenly.Backend.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class All_Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int all_location_id;
	private String country;
	
	@JsonIgnore
	@OneToMany(mappedBy = "all_location", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Thai_Location> thai_location_id;
	
	@JsonIgnore
	@OneToMany(mappedBy = "all_location", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Myanmar_Location> myanmar_location_id;
	
	
	
	
	

}
