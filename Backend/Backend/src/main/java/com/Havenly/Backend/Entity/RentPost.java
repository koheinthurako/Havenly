package com.Havenly.Backend.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "rentpost")
public class RentPost {
	
	@Id
	private String rent_post_id;
	private LocalDate date;
	private LocalTime time;
	private String title;
	
	@Column(name = "description", length = 10000)
	private String description;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String[] image;
	
	private String price;
	private String area;
	private String property_type;
	private String deposit;
	private String least_contract;
	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "location_id", referencedColumnName = "location_id")
//	Locations locations;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location_id", referencedColumnName = "location_id")
	private Locations locations;
	
	@JsonIgnore
	@OneToOne(mappedBy = "rentpost", cascade = CascadeType.ALL, orphanRemoval = true)
	Posts posts;

}