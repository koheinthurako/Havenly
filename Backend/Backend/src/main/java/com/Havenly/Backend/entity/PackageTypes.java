package com.Havenly.Backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PackageTypes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int packageTypeId;
	
	@Column
	private String packName;
	@Column
	private int price;
	@Column
	private int total_posts;
	@Column
	private int total_ads;
}
