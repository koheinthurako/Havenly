package com.Havenly.Backend.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import com.Havenly.Backend.Entity.Reg_user;
import com.Havenly.Backend.Entity.Subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reg_user_DD {
	
	private int register_id;
	private String name;
	private String phone;
	private String email;
	private String password;
	private LocalDate date;
	private LocalTime time;
	private int packageTypeId;
	private String packageName;
	private String nrc;
	private String [] profileImg;
	
	public Reg_user covertToEntity(Reg_user_DD dto) {
		Reg_user user=new Reg_user();
		user.setRegister_id(dto.getRegister_id());
		user.setName(dto.getName());
		user.setPhone(dto.getPhone());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setDate(dto.getDate());
		user.setTime(dto.getTime());
		user.setProfileImg(dto.getProfileImg());	
		return user;
	}
	
	public Reg_user_DD covertToObject(Reg_user user) {
		
		Reg_user_DD dto=new Reg_user_DD();
		Subscription sub = user.getSub();
		
		dto.setRegister_id(user.getRegister_id());
		dto.setName(user.getName());
		dto.setPhone(user.getPhone());
		dto.setEmail(user.getEmail());
		dto.setDate(user.getDate());
		dto.setTime(user.getTime());
		dto.setProfileImg(user.getProfileImg());
		
		if(sub!=null) {
			dto.setNrc(sub.getNrc().toString());
			dto.setPackageTypeId(sub.getPackages().getPackType().getPackageTypeId());	
			}
			dto.setPackageName(sub.getPackages().getPackType().getPackName());
			return dto;
		
	}
}