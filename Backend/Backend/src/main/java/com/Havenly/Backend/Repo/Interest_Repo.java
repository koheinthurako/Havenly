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

	@Query(value = " SELECT DISTINCT i.id, r.register_id, r.name, r.phone, r.email, p.post_id, t.image "
			+ "                   FROM interest i"
			+ "                   JOIN posts p ON i.post_id = p.post_id "
			+ "                   JOIN reg_user r ON r.register_id = i.register_id "
			+ "                   JOIN test_sell_post t ON p.sell_post_id = t.sell_post_id"
			+ "                   WHERE p.sub_user_id=?"
			
		,
    nativeQuery = true)
public List<Interest> getAllInterestForNoti(Integer id);
}
