package com.Havenly.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Havenly.Backend.DTO.Subscription_DTO;
import com.Havenly.Backend.Entity.Subscription;

public interface SubscribeRepo extends JpaRepository<Subscription, Integer> {

	public Subscription findByNrc(String nrc);

//	@Query(value = "SELECT regUser.name, regUser.email, sub.reg_user_id, sub.nrc "
//			+ "FROM subscription as sub" + "LEFT JOIN reg_user as regUser ON sub.reg_user_id = regUser.register_id;"
//			, nativeQuery = true)
//	public List<Subscription_DTO> getSubUserInfo();

	@Query("SELECT new com.Havenly.Backend.DTO.Subscription_DTO(sub.subUserId, sub.nrc, reg.email, reg.name, reg.phone, sub.subStartDate, sub.subStartTime, pack.packType.packName, pack.packType.price, pack.availPosts ) " +
	           "FROM Subscription sub " +
	           "LEFT JOIN sub.reg_user reg " +
	           "LEFT JOIN sub.packages pack " +
	           "WHERE reg.register_id = :registerId")
//    List<Subscription_DTO> getSubUserInfo();
	Subscription_DTO getSubUserInfo(@Param("registerId") int registerId);

}
