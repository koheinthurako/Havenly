package com.Havenly.Backend.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import com.Havenly.Backend.Entity.Reg_user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reg_user_DTO {
	
private int register_id;
	
   @NotBlank
	private String name;
   
   @NotBlank
	private String phone;
   
   @NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	private LocalDate date;
	private LocalTime time;
	
	private String profileImg;
	
	public Reg_user covertToEntity(Reg_user_DTO dto) {
		Reg_user user=new Reg_user();
		user.setRegister_id(dto.getRegister_id());
		user.setName(dto.getName());
		user.setPhone(dto.getPhone());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		
		user.setDate(dto.getDate());
		user.setTime(dto.getTime());
		return user;
	}
	public Reg_user_DTO covertToObject(Reg_user user) {
		
		Reg_user_DTO dto=new Reg_user_DTO();
		
		dto.setRegister_id(user.getRegister_id());
		dto.setName(user.getName());
		dto.setPhone(user.getPhone());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setDate(user.getDate());
		dto.setTime(user.getTime());
		 if (user.getProfileImg() != null) {
             dto.setProfileImg(user.getProfileImg());
         }
		return dto;
		
	}

}
