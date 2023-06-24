package com.cts.influentia.subscriptionmanagement.serviceImpl;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.influentia.subscriptionmanagement.Repository.SubscriptionPlansRepository;
import com.cts.influentia.subscriptionmanagement.dto.SubscriptionPlansDTO;
import com.cts.influentia.subscriptionmanagement.entities.SubscriptionPlans;
import com.cts.influentia.subscriptionmanagement.services.SubscriptionPlansService;

@Service
public class SubscriptionPlansServiceImpl implements SubscriptionPlansService{
	
	@Autowired
	private SubscriptionPlansRepository subscriptionPlanRepository;
	
	public List<SubscriptionPlansDTO> getListOfSubscriptionPlans(){
		List<SubscriptionPlans> lists= subscriptionPlanRepository.findAll();
		List<SubscriptionPlansDTO> listsDTO=new ArrayList<>();
		for(SubscriptionPlans list:lists) {
			SubscriptionPlansDTO listDTO =new SubscriptionPlansDTO(
					list.getPlanId(),
					list.getName(),
					list.getPricePerMonth()
					); 
			listsDTO.add(listDTO);
		}
		return listsDTO;
	}
}
