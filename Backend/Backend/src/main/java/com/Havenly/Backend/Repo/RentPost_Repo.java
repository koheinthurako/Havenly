package com.Havenly.Backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.RentPost;

@Repository
@EnableJpaRepositories
public interface RentPost_Repo extends JpaRepository<RentPost, String>{
	
	@Query(value = "select p.post_id, p.post_type, p.status, rp.*, l.province, l.region, c.country from posts p, rentpost rp, locations l, countries c where l.country_id = c.country_id and rp.location_id = l.location_id and rp.rent_post_id = p.post_type and p.status='complete';", nativeQuery = true)
	public List<RentPost> getAllRentPosts();
	
}
