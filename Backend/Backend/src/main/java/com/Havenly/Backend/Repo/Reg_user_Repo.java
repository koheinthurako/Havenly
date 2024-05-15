package com.Havenly.Backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.DTO.Reg_user_DD;
import com.Havenly.Backend.Entity.Reg_user;

import jakarta.transaction.Transactional;

@Repository
@EnableJpaRepositories
public interface Reg_user_Repo extends JpaRepository<Reg_user, Integer>{
	
	public Reg_user findByEmail(String gmail);
	
	@Transactional
	public String deleteByEmail(String eamil);
	
	@Query(value = "select r.name, r.email, s.nrc, p.avail_posts, p.avail_ads, p.payment, pt.package_name from packages p, subscription s, package_types pt, reg_user r where p.sub_user_id = s.sub_user_id and p.package_type_id = pt.package_type_id and r.register_id = s.reg_user_id;", nativeQuery = true)
	public Reg_user_DD getSubUserInfo();
	
}

	