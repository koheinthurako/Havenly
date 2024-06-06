package com.Havenly.Backend.Repo;

import java.util.List;

import com.Havenly.Backend.Entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Havenly.Backend.Entity.Interest;
import org.springframework.transaction.annotation.Transactional;

@Repository
@EnableJpaRepositories
public interface Interest_Repo extends JpaRepository<Interest, Integer>{

//	@Transactional
//	@Modifying
//	@Query(value = "SELECT DISTINCT i.id, r.register_id, r.name, r.phone, r.email, p.post_id, t.image "
//			+ "                   FROM interest i"
//			+ "                   JOIN posts p ON i.post_id = p.post_id "
//			+ "                   JOIN reg_user r ON r.register_id = i.register_id "
//			+ "                   JOIN test_sell_post t ON p.sell_post_id = t.sell_post_id"
//			+ "                   WHERE p.sub_user_id=?",
//    nativeQuery = true)
//	public List<Interest> getAllInterestForNoti(int id);


	@Query(value = "SELECT DISTINCT i.id ,i.description, i.interest_date, i.interest_time, r.register_id, r.name, r.phone, r.email, p.post_id, COALESCE(t.image, rt.image) As image\n" +
			"FROM interest i\n" +
			"JOIN posts p ON i.post_id = p.post_id\n" +
			"JOIN reg_user r ON r.register_id = i.register_id\n" +
			"LEFT JOIN sell_post t ON p.sell_post_id = t.sell_post_id\n" +
			"LEFT JOIN rentpost rt ON p.rent_post_id = rt.rent_post_id\n" +
			"WHERE p.sub_user_id= ?",nativeQuery = true)
	public List<Interest> getAllInterestForNoti(int id);


	// to check duplicate or not
//	@Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
//			"FROM Interest i WHERE i.post_id = :postId AND i.register_id = :userId",
//			nativeQuery = true)
//	boolean existsByPostIdAndUserId(@Param("postId") int postId, @Param("userId") int userId);

	// check not to duplicate interest with Register id and Post id
	@Query(value = "SELECT COUNT(*) FROM Interest i WHERE i.post_id = :postId AND i.register_id = :userId", nativeQuery = true)
	Long countByPostIdAndUserId(@Param("userId") int userId, @Param("postId") int postId);

	// delete by post id and login user mail
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Interest WHERE post_id = :postId AND register_id = :user_id", nativeQuery = true)
	void deleteByPostIdAndEmail(int postId, int user_id);


	// find data by Post id in interest
	@Query(value = "select * from interest where post_id = ?;", nativeQuery = true)
	List<Interest> findDataByPostId(int postId);

}
