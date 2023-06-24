package com.cts.influentia.subscriptionmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.influentia.subscriptionmanagement.entities.SubscriptionPlans;

@Repository
public interface SubscriptionPlansRepository extends JpaRepository<SubscriptionPlans,Integer> {

}
