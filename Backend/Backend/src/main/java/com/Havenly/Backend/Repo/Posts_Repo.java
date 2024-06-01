package com.Havenly.Backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.Posts;


@Repository
@EnableJpaRepositories
public interface Posts_Repo extends JpaRepository<Posts, Integer>{
	
	@Query(value = "SELECT p.post_id, p.post_type, p.status, p.sub_user_id, p.sell_post_id, p.rent_post_id, COALESCE (sp.title, rp.title) AS title, COALESCE(sp.description, sp.description) AS description,COALESCE(sp.area, rp.area) AS area, COALESCE(sp.price, rp.price) AS price, COALESCE(sp.date, rp.date) AS date, COALESCE(sp.time, rp.time) AS time, COALESCE(sp.image, rp.image) AS image, COALESCE(sp.property_type, rp.property_type) AS property_type, COALESCE(sp.location_id, rp.location_id) AS location_id, rp.deposit, rp.least_contract, l.province, l.region, c.country " +
            "FROM posts as p " +
			"LEFT JOIN subscription as sub ON p.sub_user_id = sub.sub_user_id " +
			"LEFT JOIN rentpost as rp ON p.rent_post_id = rp.rent_post_id " +
            "LEFT JOIN sell_post sp ON p.post_type = sp.sell_post_id " +
            "LEFT JOIN locations l ON sp.location_id = l.location_id " +
            "LEFT JOIN countries c ON l.country_id = c.country_id " +
            "WHERE p.sub_user_id = ?1", nativeQuery = true)
	public List<Posts>  getAllSubuserPosts(int subUserId);
	
	@Query(value = "SELECT p.post_id, p.post_type, p.status, p.sub_user_id, p.sell_post_id, p.rent_post_id, COALESCE (sp.title, rp.title) AS title, COALESCE(sp.description, rp.description) AS description,COALESCE(sp.area, rp.area) AS area, COALESCE(sp.price, rp.price) AS price, COALESCE(sp.date, rp.date) AS date, COALESCE(sp.time, rp.time) AS time, COALESCE(sp.image, rp.image) AS image, COALESCE(sp.property_type, rp.property_type) AS property_type, COALESCE(sp.location_id, rp.location_id) AS location_id, rp.deposit, rp.least_contract, l.province, l.region, c.country " +
            "FROM posts as p " +
			"LEFT JOIN subscription as sub ON p.sub_user_id = sub.sub_user_id " +
			"LEFT JOIN rentpost as rp ON p.post_type = rp.rent_post_id " +
            "LEFT JOIN sell_post sp ON p.sell_post_id = sp.sell_post_id " +
            "LEFT JOIN locations l ON sp.location_id = l.location_id " +
            "LEFT JOIN countries c ON l.country_id = c.country_id " +
            "WHERE p.status = 'pending'", nativeQuery = true)
	public List<Posts> getAllPendingPosts();
	
	
	@Query(value = "SELECT p.post_id, p.post_type, p.status, p.sub_user_id, p.sell_post_id, p.rent_post_id, COALESCE (sp.title, rp.title) AS title, COALESCE(sp.description, rp.description) AS description,COALESCE(sp.area, rp.area) AS area, COALESCE(sp.price, rp.price) AS price, COALESCE(sp.date, rp.date) AS date, COALESCE(sp.time, rp.time) AS time, COALESCE(sp.image, rp.image) AS image, COALESCE(sp.property_type, rp.property_type) AS property_type, COALESCE(sp.location_id, rp.location_id) AS location_id, rp.deposit, rp.least_contract, l.province, l.region, c.country " +
            "FROM posts as p " +
            "LEFT JOIN subscription as sub ON p.sub_user_id = sub.sub_user_id " +
			"LEFT JOIN rentpost as rp ON p.post_type = rp.rent_post_id " +
            "LEFT JOIN sell_post sp ON p.post_type = sp.sell_post_id " +
            "LEFT JOIN locations l ON sp.location_id = l.location_id " +
            "LEFT JOIN countries c ON l.country_id = c.country_id " +
            "WHERE p.status = 'complete'", nativeQuery = true)
	public List<Posts> getAllCompletePosts();

	// Retrieve post from interest and then Retrieve post data by id
	@Query(value = "SELECT p.post_id, p.post_type, p.status, p.sub_user_id, tp.sell_post_id, rp.rent_post_id, " +
			"COALESCE(tp.title, rp.title) AS title, " +
			"COALESCE(tp.description, rp.description) AS description, " +
			"COALESCE(tp.area, rp.area) AS area, " +
			"COALESCE(tp.price, rp.price) AS price, " +
			"COALESCE(tp.date, rp.date) AS date, " +
			"COALESCE(tp.time, rp.time) AS time, " +
			"COALESCE(tp.image, rp.image) AS image, " +
			"COALESCE(tp.property_type, rp.property_type) AS property_type, " +
			"COALESCE(tp.location_id, rp.location_id) AS location_id, " +
			"rp.deposit, rp.least_contract, l.province, l.region, c.country " +
			"FROM posts p " +
			"LEFT JOIN subscription sub ON p.sub_user_id = sub.sub_user_id " +
			"LEFT JOIN rentpost rp ON p.rent_post_id = rp.rent_post_id " +
			"LEFT JOIN sell_post tp ON p.sell_post_id = tp.sell_post_id " +
			"LEFT JOIN locations l ON tp.location_id = l.location_id " +
			"LEFT JOIN countries c ON l.country_id = c.country_id " +
			"INNER JOIN interest i ON p.post_id = i.post_id " +
			"INNER JOIN reg_user r ON i.register_id = r.register_id " +
			"WHERE r.register_id = :r_id",
			nativeQuery = true)
	List<Posts> getInterestedPostsByRegId(@Param("r_id") int r_id);

//	@Query(value = "SELECT " +
//			"    p.post_id AS postId, " +
//			"    p.post_type AS postType, " +
//			"    p.status AS status, " +
//			"    p.sub_user_id AS subUserId, " +
//			"    tp.sell_post_id AS sellPostId, " +
//			"    rp.rent_post_id AS rentPostId, " +
//			"    COALESCE(tp.title, rp.title) AS title, " +
//			"    COALESCE(tp.description, rp.description) AS description, " +
//			"    COALESCE(tp.area, rp.area) AS area, " +
//			"    COALESCE(tp.price, rp.price) AS price, " +
//			"    COALESCE(tp.date, rp.date) AS date, " +
//			"    COALESCE(tp.time, rp.time) AS time, " +
//			"    COALESCE(tp.image, rp.image) AS image, " +
//			"    COALESCE(tp.property_type, rp.property_type) AS propertyType, " +
//			"    COALESCE(tp.location_id, rp.location_id) AS locationId, " +
//			"    rp.deposit AS deposit, " +
//			"    rp.lease_contract AS leaseContract, " +
//			"    l.province AS province, " +
//			"    l.region AS region, " +
//			"    c.country AS country " +
//			"FROM " +
//			"    posts p " +
//			"LEFT JOIN " +
//			"    subscription sub ON p.sub_user_id = sub.sub_user_id " +
//			"LEFT JOIN " +
//			"    rentpost rp ON p.rent_post_id = rp.rent_post_id " +
//			"LEFT JOIN " +
//			"    sell_post tp ON p.sell_post_id = tp.sell_post_id " +
//			"LEFT JOIN " +
//			"    locations l ON tp.location_id = l.location_id " +
//			"LEFT JOIN " +
//			"    countries c ON l.country_id = c.country_id " +
//			"INNER JOIN " +
//			"    interest i ON p.post_id = i.post_id " +
//			"INNER JOIN " +
//			"    reg_user r ON i.register_id = r.register_id " +
//			"WHERE " +
//			"    r.register_id = :r_id " +
//			"    AND p.status = 'complete'", nativeQuery = true)
//	List<Posts> getInterestedPostsByRegId(@Param("r_id") int r_id);


}
