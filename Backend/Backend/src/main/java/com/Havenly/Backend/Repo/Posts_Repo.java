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
	
	
	
	@Query(value = "SELECT p.post_id AS post_id, p.post_type AS post_type, p.status AS status, sp.sell_post_id AS sell_post_id, sp.date AS date, sp.time AS time, sp.title AS title, sp.description AS description, sp.price AS price, sp.area AS area, sp.property_type AS property_type, sp.image AS image, l.province AS province, l.region AS region, c.country AS country " +
            "FROM posts p " +
            "INNER JOIN test_sell_post sp ON p.post_type = sp.sell_post_id " +
            "INNER JOIN locations l ON sp.location_id = l.location_id " +
            "INNER JOIN countries c ON l.country_id = c.country_id " +
            "WHERE p.status = 'pending'", nativeQuery = true)
	public List<Posts> getAllPosts();
	

}
