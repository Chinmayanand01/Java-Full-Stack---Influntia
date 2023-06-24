package com.cts.influentia.subscriptionmanagement.services;

import java.util.List;

import com.cts.influentia.subscriptionmanagement.dto.SubscriptionPlansDTO;
//import com.cts.influentia.subscriptionmanagement.entities.SubscriptionPlans;

//import com.cts.influentia.subscriptionmanagement.entities.UserSubscriptions;
public interface SubscriptionPlansService {
	public List<SubscriptionPlansDTO> getListOfSubscriptionPlans();
}
