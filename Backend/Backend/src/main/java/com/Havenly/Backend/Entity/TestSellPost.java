package com.Havenly.Backend.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
public class TestSellPost {
	
	@Id
	private String sell_post_id;
	private LocalDate date;
	private LocalTime time;
	private String title;
	private String description;
	private String price;
	private String area;
	private String house_type;
	private String property_type;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String image;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location_id", referencedColumnName = "location_id")
	Locations locations;
	
	@JsonIgnore
	@OneToOne(mappedBy = "testsellpostss")
	Posts posts;

	
}
