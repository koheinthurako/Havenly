package com.Havenly.Backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.RentPost;

import jakarta.transaction.Transactional;

@Repository
@EnableJpaRepositories
public interface RentPost_Repo extends JpaRepository<RentPost, String>{
	
	@Query(value = "select p.post_id, p.post_type, p.status, rp.*, l.province, l.region, c.country from posts p, rentpost rp, locations l, countries c where l.country_id = c.country_id and rp.location_id = l.location_id and rp.rent_post_id = p.post_type and p.status='complete';", nativeQuery = true)
	public List<RentPost> getAllRentPosts();
	
	@Query(value = "select p.post_id, p.post_type, p.sub_user_id, p.status, rp.* from  rentpost rp, posts p where rp.rent_post_id = p.rent_post_id and p.sub_user_id = ? and p.status = 'complete';", nativeQuery = true)
	List<RentPost> getAllSubuserRentPosts(int subUserId);
	
//	@Modifying
//    @Transactional
//    @Query(value = "UPDATE packages SET avail_posts = ?1 WHERE sub_user_id = ?2", nativeQuery = true)
//	public void updatePost();
	
//	@Query(value = "SELECT new com.Havenly.Backend.DTO.RentPost_DTO(rp.date, rp.time, rp.title, rp.description, rp.image, rp.price, rp.area, rp.property_type, rp.deposit, rp.least_contract, p.post_id, p.post_type, p.status ) " +
//			"FROM RentPost rp " +
//			"LEFT JOIN rp.posts p " +
//			"WHERE p.sub_user_id = :subUserId"
//			)
//	List<RentPost_DTO> getAllSubuserRentPosts(@Param("subUserId") int subUserId);
	
	 @Transactional
	    @Modifying
	    @Query(value = "DELETE FROM rentpost WHERE rent_post_id = ?1", nativeQuery = true)
	    void deleteFromRentpost(String id);
	
}