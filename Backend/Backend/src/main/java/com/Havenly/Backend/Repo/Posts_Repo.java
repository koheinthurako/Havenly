package com.Havenly.Backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.Posts;


@Repository
@EnableJpaRepositories
public interface Posts_Repo extends JpaRepository<Posts, Integer>{
	
	
	
	@Query(value = "SELECT p.post_id, p.post_type, p.status, tp.sell_post_id, rp.rent_post_id, COALESCE (tp.title, rp.title) AS title, COALESCE(tp.description, rp.description) AS description,COALESCE(tp.area, rp.area) AS area, COALESCE(tp.price, rp.price) AS price, COALESCE(tp.date, rp.date) AS date, COALESCE(tp.time, rp.time) AS time, COALESCE(tp.image, rp.image) AS image, COALESCE(tp.property_type, rp.property_type) AS property_type, COALESCE(tp.location_id, rp.location_id) AS location_id, rp.deposit, rp.least_contract, l.province, l.region, c.country " +
            "FROM posts as p " +
			"LEFT JOIN rentpost as rp ON p.post_type = rp.rent_post_id " +
            "LEFT JOIN test_sell_post tp ON p.post_type = tp.sell_post_id " +
            "LEFT JOIN locations l ON tp.location_id = l.location_id " +
            "LEFT JOIN countries c ON l.country_id = c.country_id " +
            "WHERE p.status = 'pending'", nativeQuery = true)
	public List<Posts> getAllPendingPosts();
	
	
	@Query(value = "SELECT p.post_id, p.post_type, p.status, tp.sell_post_id, rp.rent_post_id, COALESCE (tp.title, rp.title) AS title, COALESCE(tp.description, rp.description) AS description,COALESCE(tp.area, rp.area) AS area, COALESCE(tp.price, rp.price) AS price, COALESCE(tp.date, rp.date) AS date, COALESCE(tp.time, rp.time) AS time, COALESCE(tp.image, rp.image) AS image, COALESCE(tp.property_type, rp.property_type) AS property_type, COALESCE(tp.location_id, rp.location_id) AS location_id, rp.deposit, rp.least_contract, l.province, l.region, c.country " +
            "FROM posts as p " +
			"LEFT JOIN rentpost as rp ON p.post_type = rp.rent_post_id " +
            "LEFT JOIN test_sell_post tp ON p.post_type = tp.sell_post_id " +
            "LEFT JOIN locations l ON tp.location_id = l.location_id " +
            "LEFT JOIN countries c ON l.country_id = c.country_id " +
            "WHERE p.status = 'complete'", nativeQuery = true)
	public List<Posts> getAllCompletePosts();

}