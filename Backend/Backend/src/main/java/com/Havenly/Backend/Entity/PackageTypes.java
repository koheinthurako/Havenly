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
	
	@Column(name = "package_types", length=30, nullable = false)
	private String packName;
	@Column(name = "price", nullable = false)
	private int price;
	@Column(name = "total_posts", nullable = false)
	private int total_posts;
	@Column(name = "total_ads", nullable = false)
	private int total_ads;
	
	@OneToOne(mappedBy = "packType")
	Packages packages;
}
