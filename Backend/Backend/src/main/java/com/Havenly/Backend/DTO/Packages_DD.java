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
//	private LocalDate expireDate;	
	private String payment;
	private LocalDate packDate;
	private LocalDateTime packTime;

	private int amount;
//	private boolean userIsPaid;
	private String email;
	private String packageType;
	
	public Packages convertToEntity(Packages_DD dto) {
		Packages user=new Packages();
		user.setPackageId(dto.getPackageId());
		user.setPayment(dto.getPayment());
		user.setPackDate(dto.getPackDate());
		user.setPackTime(dto.getPackTime());
		return user;
	}
	
	public Packages_DD convertToObject(Packages user) {
		
		Packages_DD dto=new Packages_DD();
		dto.setPackageId(user.getPackageId());
		dto.setPayment(user.getPayment());
		dto.setPackDate(user.getPackDate());
		dto.setPackTime(user.getPackTime());

		dto.setPackageType(user.getSub1().getPackageType().toString());	
		return dto;
	}
}

