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


	@Query(value = "SELECT p.post_id, p.post_type, p.status, p.sub_user_id, tp.sell_post_id, rp.rent_post_id, COALESCE (tp.title, rp.title) AS title, COALESCE(tp.description, rp.description) AS description,COALESCE(tp.area, rp.area) AS area, COALESCE(tp.price, rp.price) AS price, COALESCE(tp.date, rp.date) AS date, COALESCE(tp.time, rp.time) AS time, COALESCE(tp.image, rp.image) AS image, COALESCE(tp.property_type, rp.property_type) AS property_type, COALESCE(tp.location_id, rp.location_id) AS location_id, rp.deposit, rp.least_contract, l.province, l.region, c.country " +
			"FROM posts as p " +
			"LEFT JOIN subscription as sub ON p.sub_user_id = sub.sub_user_id " +
			"LEFT JOIN rentpost as rp ON p.post_type = rp.rent_post_id " +
			"LEFT JOIN test_sell_post tp ON p.post_type = tp.sell_post_id " +
			"LEFT JOIN locations l ON tp.location_id = l.location_id " +
			"LEFT JOIN countries c ON l.country_id = c.country_id " +
			"WHERE p.post_type = ?", nativeQuery = true)
	public Posts getPostById(String id);

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
			"LEFT JOIN rentpost rp ON p.post_type = rp.rent_post_id " +
			"LEFT JOIN test_sell_post tp ON p.post_type = tp.sell_post_id " +
			"LEFT JOIN locations l ON tp.location_id = l.location_id " +
			"LEFT JOIN countries c ON l.country_id = c.country_id " +
			"INNER JOIN interest i ON p.post_id = i.post_id " +
			"INNER JOIN reg_user r ON i.register_id = r.register_id " +
			"WHERE r.email = :email",
			nativeQuery = true)
	List<Posts> findPostByUserLoginMailFromInterest(@Param("email") String email);


	// firstly don't work, leave it and then test again it's work ;)
//	@Query(value ="SELECT \n" +
//			"    p.post_id,\n" +
//			"    p.post_type,\n" +
//			"    p.status,\n" +
//			"    p.sub_user_id,\n" +
//			"    tp.sell_post_id,\n" +
//			"    rp.rent_post_id,\n" +
//			"    COALESCE(tp.title, rp.title) AS title,\n" +
//			"    COALESCE(tp.description, rp.description) AS description,\n" +
//			"    COALESCE(tp.image, rp.image) AS image,\n" +
//			"    COALESCE(tp.property_type, rp.property_type) AS property_type,\n" +
//			"    l.province AS province,\n" +
//			"    l.region AS region,\n" +
//			"    c.country AS country,\n" +
//			"    i.register_id AS register_id,\n" +
//			"    (SELECT description FROM interest WHERE post_id = p.post_id AND register_id = i.register_id LIMIT 1) AS user_desc,\n" +
//			"    ru.name AS user_name\n" +
//			"FROM \n" +
//			"    posts p\n" +
//			"LEFT JOIN \n" +
//			"    subscription sub ON p.sub_user_id = sub.sub_user_id\n" +
//			"LEFT JOIN \n" +
//			"    rentpost rp ON p.post_type = rp.rent_post_id\n" +
//			"LEFT JOIN \n" +
//			"    test_sell_post tp ON p.post_type = tp.sell_post_id\n" +
//			"LEFT JOIN \n" +
//			"    locations l ON tp.location_id = l.location_id\n" +
//			"LEFT JOIN \n" +
//			"    countries c ON l.country_id = c.country_id\n" +
//			"LEFT JOIN \n" +
//			"    (\n" +
//			"        SELECT \n" +
//			"            post_id,\n" +
//			"            register_id\n" +
//			"        FROM \n" +
//			"            interest\n" +
//			"    ) i ON p.post_id = i.post_id\n" +
//			"LEFT JOIN \n" +
//			"    reg_user r ON sub.reg_user_id = r.register_id\n" +
//			"LEFT JOIN \n" +
//			"    reg_user ru ON i.register_id = ru.register_id\n" +
//			"WHERE \n" +
//			"    p.sub_user_id = (\n" +
//			"        SELECT \n" +
//			"            sub_user_id \n" +
//			"        FROM \n" +
//			"            subscription \n" +
//			"        WHERE \n" +
//			"            reg_user_id = (\n" +
//			"                SELECT \n" +
//			"                    register_id \n" +
//			"                FROM \n" +
//			"                    reg_user \n" +
//			"                WHERE \n" +
//			"                    email = :email\n" +
//			"            )\n" +
//			"        AND EXISTS (\n" +
//			"            SELECT 1\n" +
//			"            FROM interest\n" +
//			"            WHERE post_id = p.post_id\n" +
//			"        )\n" +
//			"    );", nativeQuery = true)
//	List<Posts> getAllNotificationsByEmail(@Param("email") String email);

}
