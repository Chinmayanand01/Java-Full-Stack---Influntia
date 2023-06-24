package com.cts.influentia.subscriptionmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.influentia.subscriptionmanagement.entities.SubscriptionCancellations;

public interface SubscriptionCancelRepository extends JpaRepository<SubscriptionCancellations,Integer> {

}
