package com.Havenly.Backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Havenly.Backend.Entity.TestSellPost;

public interface TestSellPost_Repo extends JpaRepository<TestSellPost, String>{
	
//	@Query(value = "select sp.*, l.province, l.region, c.country from sellpost sp, locations l, countries c where sp.location_id = l.location_id and l.country_id = c.country_id;", nativeQuery = true)
//	public List<TestSellPost> getAllTestPosts();
	

//	@Query(value = "SELECT p.post_id, p.post_type, p.status, sp.*, l.province, l.region, c.country" +
//            "FROM posts p " +
//            "INNER JOIN test_sell_post sp ON p.post_type = sp.sell_post_id " +
//            "INNER JOIN locations l ON sp.location_id = l.location_id " +
//            "INNER JOIN countries c ON l.country_id = c.country_id " +
//            "WHERE p.status = 'complete'", nativeQuery = true)
//	public List<TestSellPost> getAllTestSellPosts();
	
	@Query(value = "select p.post_id, p.post_type, p.status, tp.*, l.province, l.region, c.country from posts p, test_sell_post tp, locations l, countries c where l.country_id = c.country_id and tp.location_id = l.location_id and tp.sell_post_id = p.post_type and p.status='complete';", nativeQuery = true)
	public List<TestSellPost> getAllTestSellPosts();
	

}
