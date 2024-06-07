package com.Havenly.Backend.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;


import com.Havenly.Backend.Entity.Reg_user;

import com.Havenly.Backend.Entity.Subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Subscription_DTO {
	private int subUserId;
	private String nrc;
	private String email;
	private String name;
    private String phone;
	private LocalDate subStartDate;
	private LocalDateTime subStartTime;
//	private Reg_user regUser
	private String packageType;
	private String packagePrice;
	private int availPosts;
	private int availAds;
//	private int availPosts;

	
	public Subscription convertToEntity(Subscription_DTO dto) {
		Subscription subUser = new Subscription();
		subUser.setSubUserId(dto.subUserId);
		subUser.setNrc(dto.getNrc());
		subUser.setSubStartDate(dto.getSubStartDate());
		subUser.setSubStartTime(dto.getSubStartTime());

		return subUser;
		
	}
	
	public Subscription_DTO convertToObject(Subscription subUser) {
		Subscription_DTO dto = new Subscription_DTO();
		dto.setSubUserId(subUser.getSubUserId());
//		dto.setRegUser(subUser.getReg_user());
		dto.setNrc(subUser.getNrc());
		dto.setSubStartDate(subUser.getSubStartDate());
		dto.setSubStartTime(subUser.getSubStartTime());
		
		dto.setEmail(subUser.getReg_user().getEmail());
		dto.setPackageType(subUser.getPackages().getPackType().getPackName());
		return dto;
		
	}

}
