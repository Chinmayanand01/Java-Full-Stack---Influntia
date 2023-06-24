package com.cts.influentia.subscriptionmanagement.mapper;

import com.cts.influentia.subscriptionmanagement.dto.UserSubscriptionsDTO;
import com.cts.influentia.subscriptionmanagement.entities.UserSubscriptions;

public class UserSubscriptionsMapper {
	
	//Covert User JPA Entity into DTO
	public static UserSubscriptionsDTO mapToDTO(UserSubscriptions userSubscription) {
		UserSubscriptionsDTO usersubsciptionsDto=new UserSubscriptionsDTO(
				userSubscription.getSubscriptionId(),
				userSubscription.getUserName(),
				userSubscription.getSubscriptionStartDate(),
				userSubscription.getSubscriptionEndDate(),
				userSubscription.getAmountPaid(),
				userSubscription.getPaymentMode(),
				userSubscription.getSubscriptionStatus(),
				userSubscription.getSubscriptionPlans(),
				userSubscription.getPlanDuration()
				);
		return usersubsciptionsDto;
	}
	
	//Convert DTO to JPA Entity
	public static UserSubscriptions mapToJPA(UserSubscriptionsDTO userSubscriptionDto) {
		UserSubscriptions usersubsciptions=new UserSubscriptions(
				userSubscriptionDto.getSubscriptionId(),
				userSubscriptionDto.getUserName(),
				userSubscriptionDto.getSubscriptionStartDate(),
				userSubscriptionDto.getSubscriptionEndDate(),
				userSubscriptionDto.getAmountPaid(),
				userSubscriptionDto.getPaymentMode(),
				userSubscriptionDto.getSubscriptionStatus(),
				userSubscriptionDto.getSubscriptionPlans(),
				userSubscriptionDto.getPlanDuration()
				);
		return usersubsciptions;
	}
	
}
