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
	
	private LocalDate subStartDate;
	private LocalDate subEndDate;


	private LocalDateTime subStartTime;
	private LocalDateTime subEndTime;

	private int postId;
	private int adsId;
	private int banId;
	private int totalPosts;
	private int totalAds;
	
	public Subscription convertToEntity(Subscription_DTO dto) {
		Subscription subUser = new Subscription();
		subUser.setSubUserId(dto.getAdsId());
		subUser.setNrc(dto.getNrc());
		subUser.setTotalPosts(dto.getTotalPosts());
		subUser.setTotalAds(dto.getTotalAds());
		
		subUser.setSubStartDate(dto.getSubStartDate());
		subUser.setSubStartTime(dto.getSubStartTime());
		subUser.setSubEndDate(dto.getSubEndDate());
		subUser.setSubEndTime(dto.getSubEndTime());
		
		return subUser;
		
	}
	
	public Subscription_DTO convertToObject(Subscription subUser) {
		Subscription_DTO dto = new Subscription_DTO();
		dto.setSubUserId(subUser.getSubUserId());
		dto.setNrc(subUser.getNrc());
		dto.setTotalPosts(subUser.getTotalPosts());
		dto.setTotalAds(subUser.getTotalAds());
		
		dto.setSubStartDate(subUser.getSubStartDate());
		dto.setSubStartTime(subUser.getSubStartTime());
		dto.setSubEndDate(subUser.getSubEndDate());
		dto.setSubEndTime(subUser.getSubEndTime());
		return dto;
		
	}

}
