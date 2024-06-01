package com.Havenly.Backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Havenly.Backend.Entity.SellPost;

public interface SellPost_Repo extends JpaRepository<SellPost, String>{
	
	
	@Query(value = "select p.post_id, p.post_type, p.status, sp.*, l.province, l.region, c.country from posts p, sellpost sp, locations l, countries c where l.country_id = c.country_id and sp.location_id = l.location_id and sp.sell_post_id = p.post_type and p.status='complete';", nativeQuery = true)
	public List<SellPost> getAllTestSellPosts();
	
	@Query(value = "select p.post_id, p.post_type, p.sub_user_id, p.status, sp.* from  sell_post sp, posts p where sp.sell_post_id = p.sell_post_id and p.sub_user_id = ? and p.status = 'complete';", nativeQuery = true)
	List<SellPost> getAllSubuserSellPosts(int subUserId);
	

}
