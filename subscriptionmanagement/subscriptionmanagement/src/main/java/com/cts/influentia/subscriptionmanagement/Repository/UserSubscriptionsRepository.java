package com.cts.influentia.subscriptionmanagement.Repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import com.cts.influentia.subscriptionmanagement.dto.UserSubscriptionsDTO;
import com.cts.influentia.subscriptionmanagement.entities.UserSubscriptions;

@Repository
public interface UserSubscriptionsRepository extends JpaRepository<UserSubscriptions,Integer> {
	//List<UserSubscriptions> findByUserName(String UserName);
	@Query("SELECT u FROM UserSubscriptions u WHERE u.UserName = :userName")
    UserSubscriptions findByUserName(@Param("userName") String userName);
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserSubscriptions u WHERE u.UserName = :userName")
    boolean existsByUserName(@Param("userName") String userName);
}
