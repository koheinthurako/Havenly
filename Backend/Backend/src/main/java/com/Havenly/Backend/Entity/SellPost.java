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
public class SellPost {
	
	@Id
	private String sell_post_id;
	private LocalDate date;
	private LocalTime time;
	private String title;
	
	@Column(name = "description", length = 10000)
	private String description;
	
	private String price;
	private String area;
	private String property_type;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String[] image;
	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "location_id", referencedColumnName = "location_id")
//	Locations locations;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location_id", referencedColumnName = "location_id")
	private Locations locations;
	
	@JsonIgnore
	@OneToOne(mappedBy = "sellpost", cascade = CascadeType.ALL, orphanRemoval = true)
	Posts posts;

	
}
