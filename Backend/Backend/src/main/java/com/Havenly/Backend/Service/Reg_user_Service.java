package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.Havenly.Backend.DTO.Reg_user_DTO;
import com.Havenly.Backend.Entity.Reg_user;

@Service
public interface Reg_user_Service {
	public List<Reg_user_DTO> findAll();
	public Reg_user_DTO register(Reg_user_DTO dto);
	public Reg_user Login(String gmail, String password);

}
