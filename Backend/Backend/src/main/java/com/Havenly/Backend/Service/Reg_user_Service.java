package com.Havenly.Backend.Service;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.DTO.Reg_user_DD;
import com.Havenly.Backend.DTO.Reg_user_DTO;


@Service
public interface Reg_user_Service {
	public Reg_user_DTO register(Reg_user_DTO dto);
	public Reg_user_DD Login(String gmail, String password);

}
