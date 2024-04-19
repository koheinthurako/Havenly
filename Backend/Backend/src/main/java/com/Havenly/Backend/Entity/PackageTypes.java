package com.Havenly.Backend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
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
	
	@OneToOne(mappedBy = "packageTypeId")
	Packages packages;
}
