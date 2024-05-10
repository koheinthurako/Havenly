package com.Havenly.Backend.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.Havenly.Backend.Entity.Packages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Packages_DTO {
	private int packageId;
//	private String packageName;
	private String payment;	
	private LocalDateTime packTime;
	private LocalDate packDate;
	
	public Packages convertToEntity(Packages_DTO dto) {
		Packages pack = new Packages();
		pack.setPackageId(dto.packageId);
		pack.setPayment(dto.getPayment());
		pack.setPackDate(dto.getPackDate());
		pack.setPackTime(dto.getPackTime());
	//	pack.setPackageName(dto.getPackageName());
		return pack;
}
	
	public Packages_DTO convertToObject(Packages pack) {
		Packages_DTO dto = new Packages_DTO();
		dto.setPackageId(pack.getPackageId());
		dto.setPayment(pack.getPayment());
		dto.setPackDate(pack.getPackDate());
		dto.setPackTime(pack.getPackTime());
	//	dto.setPackageName(pack.getPackageName());
		return dto;
}
}

