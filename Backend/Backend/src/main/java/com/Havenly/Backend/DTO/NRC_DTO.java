package com.Havenly.Backend.DTO;

import java.time.LocalDateTime;

import com.Havenly.Backend.Entity.NRC;

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
public class NRC_DTO {
	private int id;
	private String name_en;
	private String name_mm;
	private int nrc_code;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
	public NRC convertToEntity (NRC_DTO dto) {
		NRC nrc = new NRC();
		nrc.setId(dto.id);
		nrc.setName_en(dto.getName_en());
		nrc.setName_mm(dto.getName_mm());
		nrc.setNrc_code(dto.getNrc_code());
		nrc.setCreated_at(dto.getCreated_at());
		nrc.setUpdated_at(dto.getUpdated_at());
		return nrc;	
	}
	
	public NRC_DTO convertToObject (NRC nrc) {
			NRC_DTO dto = new NRC_DTO();
		dto.setId(nrc.getId());
		dto.setName_en(nrc.getName_en());
		dto.setName_mm(nrc.getName_mm());
		dto.setNrc_code(nrc.getNrc_code());
		dto.setCreated_at(nrc.getCreated_at());
		dto.setUpdated_at(nrc.getUpdated_at());
			return dto;
		}
}
