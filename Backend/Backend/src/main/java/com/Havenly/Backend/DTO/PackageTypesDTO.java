package com.Havenly.Backend.DTO;

import com.Havenly.Backend.Entity.PackageTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageTypesDTO {

private int packageTypeId;
	private String packName;
	private String price;
	private int total_posts;
	private int total_ads;
	
	public PackageTypes convertToEntity(PackageTypesDTO packDTO) {
		PackageTypes pack = new PackageTypes();
		pack.setPackageTypeId(packDTO.getPackageTypeId());
		pack.setPackName(packDTO.getPackName());
		pack.setPrice(packDTO.getPrice());
		pack.setTotal_ads(packDTO.getTotal_ads());
		pack.setTotal_posts(packDTO.getTotal_posts());
		return pack;
		
	}
	public PackageTypesDTO convertToObject(PackageTypes pack) {
		PackageTypesDTO packDTO = new PackageTypesDTO();
		packDTO.setPackageTypeId(pack.getPackageTypeId());
		packDTO.setPackName(pack.getPackName());
		packDTO.setPrice(pack.getPrice());
		packDTO.setTotal_ads(pack.getTotal_ads());
		packDTO.setTotal_posts(pack.getTotal_posts());
		return packDTO;
		
	}
}
