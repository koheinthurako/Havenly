package com.Havenly.Backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.DTO.RentPost_DTO;
import com.Havenly.Backend.Entity.Posts;
import com.Havenly.Backend.Entity.RentPost;

@Repository
@EnableJpaRepositories
public interface RentPost_Repo extends JpaRepository<RentPost, String>{
	
	@Query(value = "select p.post_id, p.post_type, p.status, rp.*, l.province, l.region, c.country from posts p, rentpost rp, locations l, countries c where l.country_id = c.country_id and rp.location_id = l.location_id and rp.rent_post_id = p.post_type and p.status='complete';", nativeQuery = true)
	public List<RentPost> getAllRentPosts();
	
	@Query(value = "select p.post_id, p.post_type, p.sub_user_id, p.status, rp.* from  rentpost rp, posts p where rp.rent_post_id = p.rent_post_id and p.sub_user_id = ? and p.status = 'complete';", nativeQuery = true)
	List<RentPost> getAllSubuserRentPosts(int subUserId);
	
//	@Query(value = "SELECT new com.Havenly.Backend.DTO.RentPost_DTO(rp.date, rp.time, rp.title, rp.description, rp.image, rp.price, rp.area, rp.property_type, rp.deposit, rp.least_contract, p.post_id, p.post_type, p.status ) " +
//			"FROM RentPost rp " +
//			"LEFT JOIN rp.posts p " +
//			"WHERE p.sub_user_id = :subUserId"
//			)
//	List<RentPost_DTO> getAllSubuserRentPosts(@Param("subUserId") int subUserId);
	
}
