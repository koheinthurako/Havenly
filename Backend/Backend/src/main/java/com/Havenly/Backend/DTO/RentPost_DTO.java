package com.Havenly.Backend.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentPost_DTO {
	
	private LocalDate date;
	private LocalTime time;
	private String title;
	private String description;
	private String[] image;
	private String price;
	private String area;
	private String property_type;
	private String deposit;
	private String least_contract;
	private int post_id;
	private String post_type;
	private String status;
	

}
