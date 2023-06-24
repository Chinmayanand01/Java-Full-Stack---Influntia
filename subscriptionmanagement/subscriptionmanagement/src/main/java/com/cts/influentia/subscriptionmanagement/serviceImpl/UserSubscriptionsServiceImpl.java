package com.cts.influentia.subscriptionmanagement.serviceImpl;

import java.time.LocalDate;
//import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.influentia.subscriptionmanagement.Repository.UserSubscriptionsRepository;
import com.cts.influentia.subscriptionmanagement.dto.UserSubscriptionsDTO;
import com.cts.influentia.subscriptionmanagement.entities.UserSubscriptions;
import com.cts.influentia.subscriptionmanagement.exceptions.MultipleSubscriptionsAreNotAllowedException;
import com.cts.influentia.subscriptionmanagement.mapper.UserSubscriptionsMapper;
import com.cts.influentia.subscriptionmanagement.services.UserSubscriptionsService;

import jakarta.transaction.Transactional;

@Service
public class UserSubscriptionsServiceImpl implements UserSubscriptionsService {

	@Autowired
	private UserSubscriptionsRepository userSubscriptionsRepository;
	private int calculateAmountPayable(String subscriptionPlanType, int planDuration, int amountPaid) {
        double discount;
        if (planDuration == 1) {
            discount = 0.0; 
        } else if (planDuration == 3) {
            discount = subscriptionPlanType.equals("pro") ? 0.05 : 0.03;
        } else if (planDuration == 6) {
            discount = subscriptionPlanType.equals("pro") ? 0.1 : 0.05;
        } else {
            discount = subscriptionPlanType.equals("pro") ? 0.15 : 0.08;
        }
        return (int) Math.round((amountPaid*planDuration) * (1 - discount));
    }
	@Override
	public UserSubscriptions insertNewSubscription(UserSubscriptions userSubscription) throws MultipleSubscriptionsAreNotAllowedException{
		 // Check if the user already has a subscription
        if (userSubscriptionsRepository.existsByUserName(userSubscription.getUserName())) {
            throw new MultipleSubscriptionsAreNotAllowedException("Multiple Subscriptions are not Allowed");
        }
        
        // Calculate the end date and the amount payable
        LocalDate endDate = userSubscription.getSubscriptionStartDate().plusMonths(userSubscription.getPlanDuration());
        int amountPayable = calculateAmountPayable(userSubscription.getSubscriptionPlans().getName(), userSubscription.getPlanDuration(), userSubscription.getAmountPaid());
        userSubscription.setSubscriptionEndDate(endDate);
        userSubscription.setAmountPaid(amountPayable);
		return userSubscriptionsRepository.save(userSubscription);
	}
	
	public Optional<UserSubscriptions> findById(int subscriptionId) {
		return userSubscriptionsRepository.findById(subscriptionId);
	}
	public void deleteById(int subscriptionId) {
		userSubscriptionsRepository.deleteById(subscriptionId);
	}
	@Override
	@Transactional
	public UserSubscriptionsDTO updateSubscription(UserSubscriptionsDTO userSubscriptionDto) {
		UserSubscriptions userSubscription = UserSubscriptionsMapper.mapToJPA(userSubscriptionDto);
		 UserSubscriptions updatedUser=userSubscriptionsRepository.save(userSubscription);
		 UserSubscriptionsDTO updatedUserDto=UserSubscriptionsMapper.mapToDTO(updatedUser);
		 return updatedUserDto;
	}

	@Override
	public void removeSubscription(UserSubscriptionsDTO userSubscriptiondto) {
		UserSubscriptions userSubscription = UserSubscriptionsMapper.mapToJPA(userSubscriptiondto);
		userSubscriptionsRepository.save(userSubscription);
	} 
	
	@Override
	public UserSubscriptionsDTO getSubsciptionByUserName(String UserName){
		UserSubscriptions jpaUser=userSubscriptionsRepository.findByUserName(UserName);
		UserSubscriptionsDTO updatedUserDto=UserSubscriptionsMapper.mapToDTO(jpaUser);
		return updatedUserDto;
		
	}

}
