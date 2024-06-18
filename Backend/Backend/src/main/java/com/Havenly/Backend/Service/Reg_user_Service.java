package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.Havenly.Backend.Entity.Reg_user;
import com.Havenly.Backend.DTO.Reg_user_DD;
import com.Havenly.Backend.DTO.Reg_user_DTO;


@Service
public interface Reg_user_Service {
	public List<Reg_user_DTO> findAll();
	public Reg_user_DD getById(int id);
	public Reg_user_DTO register(Reg_user_DTO dto);
	public Reg_user_DD Login(String gmail, String password);
	public Reg_user_DD update(String name,String phone,String gmail, MultipartFile img);
	public Reg_user_DTO pwdUpdate(String username,String password,String new_password);
//	public String sendEmail(Reg_user user);
//	public String generateResetToken(Reg_user user);
//	public boolean hasExpired(LocalDateTime expiryDateTime);
	public String forgotPassword(String email);
	public String setPassword(String email, String newPassword);
	String deleteByEmail(String email);
	Reg_user getDataBySubId(int id);
	public boolean isEmailExist(String email);

}
