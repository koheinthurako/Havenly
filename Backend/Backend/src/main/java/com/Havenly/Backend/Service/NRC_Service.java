package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.DTO.NRC_DTO;
@Service
public interface NRC_Service {

	public List<NRC_DTO> getAll();
	public List<NRC_DTO> saveAll(List<NRC_DTO> nrc);
}
