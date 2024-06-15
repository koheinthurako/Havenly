package com.Havenly.Backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Query(value = "SELECT * FROM posts as p WHERE p.ads_post_id = ?1", nativeQuery = true)
	public Posts getPostsByAdsPostId(String ads_post_id);

	@Query(value = "SELECT p.*, " +
            "COALESCE(sp.title, rp.title) AS title, " +
            "COALESCE(sp.description, rp.description) AS description, " +
            "COALESCE(sp.area, rp.area) AS area, " +
            "COALESCE(sp.price, rp.price) AS price, " +
            "COALESCE(sp.date, rp.date) AS date, " +
            "COALESCE(sp.time, rp.time) AS time, " +
            "COALESCE(sp.image, rp.image) AS image, " +
            "COALESCE(sp.property_type, rp.property_type) AS property_type, " +
            "COALESCE(sp.location_id, rp.location_id) AS location_id, " +
            "COALESCE(l1.province, l2.province) AS province, " +
            "COALESCE(l1.region, l2.region) AS region, " +
            "COALESCE(c1.country, c2.country) AS country, " +
            "rp.deposit, rp.least_contract " +
        "FROM posts p " +
        "LEFT JOIN subscription sub ON p.sub_user_id = sub.sub_user_id " +
        "LEFT JOIN rentpost rp ON p.rent_post_id = rp.rent_post_id " +
        "LEFT JOIN sell_post sp ON p.sell_post_id = sp.sell_post_id " +
        "LEFT JOIN locations l1 ON sp.location_id = l1.location_id " +
        "LEFT JOIN locations l2 ON rp.location_id = l2.location_id " +
        "LEFT JOIN countries c1 ON l1.country_id = c1.country_id " +
        "LEFT JOIN countries c2 ON l2.country_id = c2.country_id " +
        "WHERE (l1.location_id = ?1 OR l2.location_id = ?1) AND p.status = 'complete'", nativeQuery = true)
	public List<Posts> getAllPostsByLocation(@Param("locationId") int locationId);

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

	List<Posts> findBySubUserSubUserId(int subUserId);
	
	 @Transactional
	    @Modifying
	    @Query(value = "DELETE FROM posts WHERE sub_user_id = ?", nativeQuery = true)
	    void deleteFromposts(int id);
	    
	    @Query(value = "SELECT sell_post_id FROM posts WHERE sub_user_id = ? and post_type='Sell Post'", nativeQuery = true)
	    String getSellId(int id);
	    
	    @Query(value = "SELECT rent_post_id FROM posts WHERE sub_user_id = ? and post_type='Rent Post'", nativeQuery = true)
	    String getRentId(int id);
	    
	    @Query(value = "SELECT post_id FROM posts WHERE sub_user_id = ? and post_type='Sell Post' and status='complete' ", nativeQuery = true)
	    Integer getSPostId(int id);
	    
	    @Query(value = "SELECT post_id FROM posts WHERE sub_user_id = ? and post_type='Rent Post' and status='complete' ", nativeQuery = true)
	    Integer getRPostId(int id);
}
