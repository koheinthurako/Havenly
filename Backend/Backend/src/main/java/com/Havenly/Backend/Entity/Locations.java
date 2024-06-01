package com.Havenly.Backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Locations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int location_id;
	private String province;
	private String amphoe;
	private String region;
	private String latitude;
	private String longitude;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "country_id", referencedColumnName = "country_id")
	Countries countries;
	
	@JsonIgnore
	@OneToOne(mappedBy = "locations")
	SellPost sellpost;
	
	@JsonIgnore
	@OneToOne(mappedBy = "locations")
	RentPost rentpost;
	
}