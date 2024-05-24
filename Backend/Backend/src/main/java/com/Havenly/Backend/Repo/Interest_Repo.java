package com.Havenly.Backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.Interest;

@Repository
@EnableJpaRepositories
public interface Interest_Repo extends JpaRepository<Interest, Integer>{

	@Query(value = "select distinct i.id,r.register_id,r.name,r.phone,r.email,p.post_id,t.image from interest i,reg_user r,posts p,test_sell_post t where i.register_id=r.register_id and i.post_id=p.post_id;"
		,
    nativeQuery = true)
public List<Interest> getAllInterestForNoti();
}
