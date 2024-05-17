package com.Havenly.Backend.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.Havenly.Backend.Entity.Subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subscription_DTO {
	private int subUserId;
	private String name;
	private String nrc;
	private String email;
	private LocalDate subStartDate;
	private LocalDateTime subStartTime;

	private String packageType;

	
	public Subscription convertToEntity(Subscription_DTO dto) {
		Subscription subUser = new Subscription();
		subUser.setSubUserId(dto.subUserId);
		subUser.setNrc(dto.getNrc());
		subUser.setEmail(dto.getEmail());
		subUser.setPackageType(dto.getPackageType());
		subUser.setSubStartDate(dto.getSubStartDate());
		subUser.setSubStartTime(dto.getSubStartTime());

		
		return subUser;
		
	}
	
	public Subscription_DTO convertToObject(Subscription subUser) {
		Subscription_DTO dto = new Subscription_DTO();
		dto.setSubUserId(subUser.getSubUserId());
		dto.setEmail(subUser.getEmail());
		dto.setNrc(subUser.getNrc());
		dto.setPackageType(subUser.getPackageType());
		dto.setSubStartDate(subUser.getSubStartDate());
		dto.setSubStartTime(subUser.getSubStartTime());

		return dto;
		
	}

}
