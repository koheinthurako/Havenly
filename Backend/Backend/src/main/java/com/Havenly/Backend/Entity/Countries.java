package com.Havenly.Backend.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Countries {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int country_id;

	@Column(name = "country", nullable = false)
	private String country_name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "countries", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Locations> location_id;
	
}
