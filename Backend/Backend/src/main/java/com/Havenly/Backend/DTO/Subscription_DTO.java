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
	private int postId;
	private int adsId;

	private int totalPosts;
	private int totalAds;
	
	public Subscription convertToEntity(Subscription_DTO dto) {
		Subscription subUser = new Subscription();
		subUser.setSubUserId(dto.getAdsId());
		subUser.setNrc(dto.getNrc());
		subUser.setName(dto.getName());
		subUser.setEmail(dto.getEmail());
		subUser.setTotalPosts(dto.getTotalPosts());
		subUser.setTotalAds(dto.getTotalAds());
		subUser.setPackageType(dto.getPackageType());
		subUser.setSubStartDate(dto.getSubStartDate());
		subUser.setSubStartTime(dto.getSubStartTime());

		
		return subUser;
		
	}
	
	public Subscription_DTO convertToObject(Subscription subUser) {
		Subscription_DTO dto = new Subscription_DTO();
		dto.setSubUserId(subUser.getSubUserId());
		dto.setName(subUser.getName());
		dto.setEmail(subUser.getEmail());
		dto.setNrc(subUser.getNrc());
		dto.setTotalPosts(subUser.getTotalPosts());
		dto.setTotalAds(subUser.getTotalAds());
		dto.setPackageType(subUser.getPackageType());
		dto.setSubStartDate(subUser.getSubStartDate());
		dto.setSubStartTime(subUser.getSubStartTime());

		return dto;
		
	}

}
