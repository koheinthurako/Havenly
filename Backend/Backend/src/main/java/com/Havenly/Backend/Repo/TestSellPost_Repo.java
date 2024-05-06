package com.Havenly.Backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Havenly.Backend.Entity.TestSellPost;

public interface TestSellPost_Repo extends JpaRepository<TestSellPost, String>{
	
//	@Query(value = "select sp.*, l.province, l.region, c.country from sellpost sp, locations l, countries c where sp.location_id = l.location_id and l.country_id = c.country_id;", nativeQuery = true)
//	public List<TestSellPost> getAllTestPosts();
	
	@Query(value = "select * from test_sell_post;", nativeQuery = true)
	public List<TestSellPost> getAllTestPosts();

}
