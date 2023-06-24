package com.cts.influentia.subscriptionmanagement.services;


import com.cts.influentia.subscriptionmanagement.dto.UserSubscriptionsDTO;
import com.cts.influentia.subscriptionmanagement.entities.UserSubscriptions;
import com.cts.influentia.subscriptionmanagement.exceptions.MultipleSubscriptionsAreNotAllowedException;

public interface UserSubscriptionsService {
	
	public UserSubscriptions insertNewSubscription(UserSubscriptions userSubscription)throws MultipleSubscriptionsAreNotAllowedException;
	public UserSubscriptionsDTO updateSubscription(UserSubscriptionsDTO userSubscriptionDto);
	public void removeSubscription(UserSubscriptionsDTO userSubscriptionDto);
	public UserSubscriptionsDTO getSubsciptionByUserName(String UserName);
	
}
