package com.Havenly.Backend.DTO;


import java.time.LocalDate;
import java.time.LocalDateTime;
import com.Havenly.Backend.Entity.Packages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Packages_DD {
	
	private int packageId;
	private String payment;
	private LocalDate packDate;
	private LocalDateTime packTime;

	private String amount;
	private String email;
	private String packageType;
	private int availPosts;
	private int availAds;
	
	public Packages convertToEntity(Packages_DD dto) {
		Packages user=new Packages();
		user.setPackageId(dto.getPackageId());
		user.setPayment(dto.getPayment());
		user.setPackDate(dto.getPackDate());
		user.setPackTime(dto.getPackTime());
		user.setAvailPosts(dto.getAvailPosts());
		user.setAvailAds(dto.getAvailAds());
		
		return user;
	}
	
	public Packages_DD convertToObject(Packages user) {
		
		Packages_DD dto=new Packages_DD();
		dto.setPackageId(user.getPackageId());
		dto.setPayment(user.getPayment());
		dto.setPackDate(user.getPackDate());
		dto.setPackTime(user.getPackTime());
		dto.setAvailPosts(user.getAvailPosts());
		dto.setAvailAds(user.getAvailAds());

		dto.setPackageType(user.getPackType().getPackName());	
		return dto;
	}
}

