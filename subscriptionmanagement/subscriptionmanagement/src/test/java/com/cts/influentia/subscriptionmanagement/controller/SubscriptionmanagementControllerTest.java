package com.cts.influentia.subscriptionmanagement.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
//import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cts.influentia.subscriptionmanagement.serviceImpl.SubscriptionPlansServiceImpl;
import com.cts.influentia.subscriptionmanagement.serviceImpl.UserSubscriptionsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cts.influentia.subscriptionmanagement.dto.SubscriptionPlansDTO;
import com.cts.influentia.subscriptionmanagement.dto.UserSubscriptionsDTO;
import com.cts.influentia.subscriptionmanagement.entities.SubscriptionPlans;
import com.cts.influentia.subscriptionmanagement.entities.UserSubscriptions;
//import com.cts.influentia.subscriptionmanagement.mapper.UserSubscriptionsMapper;
import com.cts.influentia.subscriptionmanagement.mapper.UserSubscriptionsMapper;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class SubscriptionmanagementControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private SubscriptionPlansServiceImpl subscriptionPlansServiceImpl;
	
	@MockBean
	private UserSubscriptionsServiceImpl userSubscriptionsServiceImpl;
	
	@Test
	public void testGetAllSubscriptionPlans() throws Exception{
		// create a list of subscription plans to be returned by the service
		List<SubscriptionPlansDTO> subscriptionPlans = new ArrayList<>();
		subscriptionPlans.add(new SubscriptionPlansDTO(1, "basic", 10));
		subscriptionPlans.add(new SubscriptionPlansDTO(2, "pro",25));
		
		BDDMockito.given(subscriptionPlansServiceImpl.getListOfSubscriptionPlans()).willReturn(subscriptionPlans);
		
		ResultActions response=mockMvc.perform(MockMvcRequestBuilders.get("/api/subscriptions/plan"));
		
		response.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(subscriptionPlans.size())));
	}
	
	@Test
	public void testInsertNewSubscription() throws Exception{
		SubscriptionPlans subscriptionPlan=new SubscriptionPlans(1,"basic",10);
		
		UserSubscriptions userSubscription=new UserSubscriptions(101,"Mahi",LocalDate.now().plusDays(1),
				LocalDate.now().plusMonths(1),10,"Card","New",subscriptionPlan,1);
		
		BDDMockito.given(userSubscriptionsServiceImpl.insertNewSubscription(ArgumentMatchers.any(UserSubscriptions.class)))
		.willAnswer((invocation)->invocation.getArgument(0));
		
		ResultActions response =mockMvc.perform(MockMvcRequestBuilders.post("/api/subscriptions/purchase")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userSubscription)));
		
		response.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void testRenewSubscription() throws Exception{
		int subscriptionId=22;
		
		
        SubscriptionPlans subscriptionPlan=new SubscriptionPlans(1,"basic",10);
		
		UserSubscriptions userSubscription=new UserSubscriptions(22,"Mahi",LocalDate.now().plusDays(1),
				LocalDate.now().plusMonths(1),10,"Card","Renewed",subscriptionPlan,1);
		
//		BDDMockito.given(userSubscriptionsServiceImpl.insertNewSubscription(ArgumentMatchers.any(UserSubscriptions.class)))
//			.willAnswer((invocation)->invocation.getArgument(0));
		
		Optional<UserSubscriptions> validSubscription = userSubscriptionsServiceImpl.findById(subscriptionId);
			
			BDDMockito.given(userSubscriptionsServiceImpl.findById(subscriptionId)).willReturn(validSubscription);
		
			UserSubscriptionsDTO userSubscriptionDto =UserSubscriptionsMapper.mapToDTO(userSubscription);
			
			BDDMockito.given(userSubscriptionsServiceImpl.updateSubscription(ArgumentMatchers.any(UserSubscriptionsDTO.class)))
			.willReturn(userSubscriptionDto);
			
			ResultActions response =mockMvc.perform(MockMvcRequestBuilders.put("/api/subscriptions/{subscriptionId}/renew",subscriptionId,userSubscriptionDto)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(userSubscriptionDto)));
			
			response.andDo(MockMvcResultHandlers.print());
		
	}
	
	@Test
	public void testRenewForm() throws Exception{
		int subscriptionId=22;
		
		
        SubscriptionPlans subscriptionPlan=new SubscriptionPlans(1,"basic",10);
		
		UserSubscriptions userSubscription=new UserSubscriptions(22,"Mahi",LocalDate.now().plusDays(1),
				LocalDate.now().plusMonths(1),10,"Card","Renewed",subscriptionPlan,1);
		
		Optional<UserSubscriptions> validSubscription = userSubscriptionsServiceImpl.findById(subscriptionId);
			
			BDDMockito.given(userSubscriptionsServiceImpl.findById(subscriptionId)).willReturn(validSubscription);
			userSubscriptionsServiceImpl.deleteById(subscriptionId);
			//UserSubscriptionsDTO userSubscriptionDto =UserSubscriptionsMapper.mapToDTO(userSubscription);
			
			BDDMockito.given(userSubscriptionsServiceImpl.insertNewSubscription(ArgumentMatchers.any(UserSubscriptions.class)))
			.willReturn(userSubscription);
			
			ResultActions response =mockMvc.perform(MockMvcRequestBuilders.put("/api/subscriptions/{subscriptionId}/renewf",subscriptionId,userSubscription)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(userSubscription)));
			
			response.andDo(MockMvcResultHandlers.print());
		
	}
	@Test
	public void testcancelForm() throws Exception{
		int subscriptionId=22;
		
		
        SubscriptionPlans subscriptionPlan=new SubscriptionPlans(1,"basic",10);
		
		UserSubscriptions userSubscription=new UserSubscriptions(22,"Mahi",LocalDate.now().plusDays(1),
				LocalDate.now().plusMonths(1),10,"Card","Cancelled",subscriptionPlan,1);
		
		Optional<UserSubscriptions> validSubscription = userSubscriptionsServiceImpl.findById(subscriptionId);
			
			BDDMockito.given(userSubscriptionsServiceImpl.findById(subscriptionId)).willReturn(validSubscription);
		
			//UserSubscriptionsDTO userSubscriptionDto =UserSubscriptionsMapper.mapToDTO(userSubscription);
			
			BDDMockito.given(userSubscriptionsServiceImpl.insertNewSubscription(ArgumentMatchers.any(UserSubscriptions.class)))
			.willReturn(userSubscription);
			
			ResultActions response =mockMvc.perform(MockMvcRequestBuilders.put("/api/subscriptions/{subscriptionId}/cancelf",subscriptionId,userSubscription)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(userSubscription)));
			
			response.andDo(MockMvcResultHandlers.print());
		
	}
	/*@Test
	public void testCancelSubscription() throws Exception{
		int subscriptionId=22;
		
		
        SubscriptionPlans subscriptionPlan=new SubscriptionPlans(1,"basic",10);
		
		UserSubscriptions userSubscription=new UserSubscriptions(22,"Mahi",LocalDate.now().plusDays(1),
				LocalDate.now().plusMonths(1),10,"Card","Renewed",subscriptionPlan,1);
		
//		BDDMockito.given(userSubscriptionsServiceImpl.insertNewSubscription(ArgumentMatchers.any(UserSubscriptions.class)))
//			.willAnswer((invocation)->invocation.getArgument(0));
		
		Optional<UserSubscriptions> validSubscription = userSubscriptionsServiceImpl.findById(subscriptionId);
			
			BDDMockito.given(userSubscriptionsServiceImpl.findById(subscriptionId)).willReturn(validSubscription);
		
			UserSubscriptionsDTO userSubscriptionDto =UserSubscriptionsMapper.mapToDTO(userSubscription);
			
			BDDMockito.given(userSubscriptionsServiceImpl.removeSubscription(ArgumentMatchers.any(UserSubscriptionsDTO.class)))
			.willReturn(userSubscriptionDto);
			
			ResultActions response =mockMvc.perform(MockMvcRequestBuilders.put("/api/subscriptions/{subscriptionId}/renew",subscriptionId,userSubscriptionDto)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(userSubscriptionDto)));
			
			response.andDo(MockMvcResultHandlers.print());
		
	}*/
	
	@Test
	public void testGetSubscriptionByUsername() throws Exception{
		String UserName="Dhoni";
		
        SubscriptionPlans subscriptionPlan=new SubscriptionPlans(1,"basic",10);
		
		UserSubscriptionsDTO userSubscription=new UserSubscriptionsDTO(22,"Dhoni",LocalDate.now().plusDays(1),
				LocalDate.now().plusMonths(1),10,"Card","Renewed",subscriptionPlan,1);
		
		BDDMockito.given(userSubscriptionsServiceImpl.getSubsciptionByUserName(UserName)).willReturn(userSubscription);
		
		ResultActions response =mockMvc.perform(MockMvcRequestBuilders.get("/api/subscriptions/{UserName}",UserName));
		
		response.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.subscriptionId", CoreMatchers.is(userSubscription.getSubscriptionId())));
		
	}
	
}
