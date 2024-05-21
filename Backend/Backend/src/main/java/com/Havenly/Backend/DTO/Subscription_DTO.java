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
	private String nrc;
	private String email;
	private LocalDate subStartDate;
	private LocalDateTime subStartTime;

	private String packageType;

	
	public Subscription convertToEntity(Subscription_DTO dto) {
		Subscription subUser = new Subscription();
		subUser.setSubUserId(dto.getSubUserId());
		subUser.setNrc(dto.getNrc());
		subUser.setSubStartDate(dto.getSubStartDate());
		subUser.setSubStartTime(dto.getSubStartTime());

		return subUser;
		
	}
	
	public Subscription_DTO convertToObject(Subscription subUser) {
		Subscription_DTO dto = new Subscription_DTO();
		dto.setSubUserId(subUser.getSubUserId());
		dto.setNrc(subUser.getNrc());
		dto.setSubStartDate(subUser.getSubStartDate());
		dto.setSubStartTime(subUser.getSubStartTime());
		
		dto.setEmail(subUser.getReg_user().getEmail());
		dto.setPackageType(subUser.getPackages().getPackType().getPackName());
		return dto;
		
	}

}
