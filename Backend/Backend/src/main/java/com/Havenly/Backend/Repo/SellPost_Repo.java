package com.Havenly.Backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.SellPost;

import jakarta.transaction.Transactional;

@Repository
@EnableJpaRepositories
public interface SellPost_Repo extends JpaRepository<SellPost, String>{
	
	@Query(value = "select sp.*, l.province, l.region, c.country from sellpost sp, locations l, countries c where sp.location_id = l.location_id and l.country_id = c.country_id;", nativeQuery = true)
	public List<SellPost> getAllPosts();
	
//	@Query(value = "select count(sell_post_id) from sellpost")
//	long getSellPostCount();
	
//	@Query("SELECT COUNT(sp) FROM SellPost sp")
//    long getSellPostCount();
	
//	@Modifying
//    @Query(value = "UPDATE SellPost SET sell_post_id = CONCAT('s', COALESCE(CAST((SELECT MAX(SUBSTRING(sell_post_id, 2)) FROM SellPost) AS UNSIGNED) + 1, 0)) WHERE sell_post_id = (SELECT MAX(sell_post_id) FROM SellPost)", nativeQuery = true)
//	@Transactional
//    void incrementSellPostId();
//
//    default String getNextSellPostId() {
//        incrementSellPostId();
//        String maxId = findAll().stream()
//                .map(SellPost::getSell_post_id)
//                .max(String::compareTo)
//                .orElse("s0");
//        return maxId;
//    }
	
	
}
