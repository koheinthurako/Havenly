package com.Havenly.Backend.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "sellpost")
public class SellPost {

	@Id
	private String sell_post_id;
	private LocalDate date;
	private LocalTime time;
	private String title;
	private String description;
	private String[] photos;
	private String price;
	private String area;
	private String house_type;
	private String property_type;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sellpost_location", referencedColumnName = "location_id")
	Locations locations;
	
}
