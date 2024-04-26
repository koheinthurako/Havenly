package com.Havenly.Backend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestPhoto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long photo_id;
	private String[] photoUrls;
	
}
