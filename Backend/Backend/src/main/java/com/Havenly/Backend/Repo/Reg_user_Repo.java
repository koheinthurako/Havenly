package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.Reg_user;

import jakarta.transaction.Transactional;

@Repository
@EnableJpaRepositories
public interface Reg_user_Repo extends JpaRepository<Reg_user, Integer> {

	public Reg_user findByEmail(String gmail);
	public Reg_user findById(int id);
	
	@Transactional
	public String deleteByEmail(String email);

//	@Query("SELECT new com.Havenly.Backend.DTO.Reg_user_DD(reg.name, reg.phone, reg.email, reg.password, reg.date, reg.time, sub.nrc, pack.packType.packName, pack.packType.price, pack.packType.total_posts ) " +
//	           "FROM reg_user reg " +
//	           "LEFT JOIN reg.sub sub " +
//	           "LEFT JOIN sub.packages pack")
//	Reg_user_DD getSubUserInfo();

}
