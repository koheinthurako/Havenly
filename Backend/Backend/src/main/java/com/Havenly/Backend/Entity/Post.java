package com.Havenly.Backend.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long post_id;
	private String title;
	private String description;
	private String house_type;
	private String property_type;
	private String area;
	private String price;
	private String[] photoUrls;
	
}
