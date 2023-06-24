package com.cts.influentia.subscriptionmanagement.serviceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.influentia.subscriptionmanagement.Repository.SubscriptionPlansRepository;
import com.cts.influentia.subscriptionmanagement.dto.SubscriptionPlansDTO;
import com.cts.influentia.subscriptionmanagement.entities.SubscriptionPlans;
import com.cts.influentia.subscriptionmanagement.entities.UserSubscriptions;
import com.cts.influentia.subscriptionmanagement.mapper.UserSubscriptionsMapper;
import com.cts.influentia.subscriptionmanagement.dto.UserSubscriptionsDTO;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SubscriptionPlansServiceImplTests {
	@Mock
    private SubscriptionPlansRepository subscriptionPlanRepository;

    @InjectMocks
    private SubscriptionPlansServiceImpl subscriptionPlanService;
    
    @Test
    public void testGetListOfSubscriptionPlans() {
    	SubscriptionPlans plan1=new SubscriptionPlans(); 
    	plan1.setPlanId(1);
    	plan1.setName("basic");
    	plan1.setPricePerMonth(10);
    	SubscriptionPlans plan2=new SubscriptionPlans();
    	plan1.setPlanId(2);
    	plan1.setName("pro");
    	plan1.setPricePerMonth(10);
    	List<UserSubscriptions> users=new ArrayList<>();
    	UserSubscriptions user1=new UserSubscriptions(154,"Rocky",LocalDate.now(),LocalDate.now().plusMonths(1),10,"Card","New",plan1,1);
    	UserSubscriptions user2=new UserSubscriptions(155,"RockDon",LocalDate.now(),LocalDate.now().plusMonths(1),25,"Card","New",plan2,1);
        users.add(user1);
        users.add(user2);
    	List<SubscriptionPlans> plans = new ArrayList<>();
        plans.add(new SubscriptionPlans(1, "basic", 10));
        plans.add(new SubscriptionPlans(2, "pro", 25));

        when(subscriptionPlanRepository.findAll()).thenReturn(plans);

        List<SubscriptionPlansDTO> planDTOs = subscriptionPlanService.getListOfSubscriptionPlans();

        assertEquals(2, planDTOs.size());
        assertEquals(1, planDTOs.get(0).getPlanId());
        assertEquals("basic", planDTOs.get(0).getName());
        assertEquals(10, planDTOs.get(0).getPricePerMonth());
        assertEquals(2, planDTOs.get(1).getPlanId());
        assertEquals("pro", planDTOs.get(1).getName());
        assertEquals(25, planDTOs.get(1).getPricePerMonth()); 
        
        SubscriptionPlansDTO plandto=new SubscriptionPlansDTO();
        plandto.setPlanId(1);
        plandto.setName("basic");
        plandto.setPricePerMonth(10);
        
        UserSubscriptionsDTO userSubscriptionDto=new UserSubscriptionsDTO(155,"RockDone",LocalDate.now(),LocalDate.now().plusMonths(1),25,"Card","New",plan2,1);
        UserSubscriptions jpaUserSubscription=UserSubscriptionsMapper.mapToJPA(userSubscriptionDto);
        userSubscriptionDto =UserSubscriptionsMapper.mapToDTO(jpaUserSubscription);
        
        UserSubscriptionsDTO userSubscriptionDto2=new UserSubscriptionsDTO();
        userSubscriptionDto2.setSubscriptionId(156);
        userSubscriptionDto2.setUserName("Kohli");
        userSubscriptionDto2.setSubscriptionStartDate(LocalDate.now());
        userSubscriptionDto2.setSubscriptionEndDate(LocalDate.now().plusMonths(1));
        userSubscriptionDto2.setAmountPaid(10);
        userSubscriptionDto2.setPaymentMode("Card");
        userSubscriptionDto2.setSubscriptionStatus("New");
        userSubscriptionDto2.setSubscriptionPlans(plan1);
        userSubscriptionDto2.setPlanDuration(1); 
        
        UserSubscriptions user3=new UserSubscriptions();
        user3.setSubscriptionId(65);
        user3.setUserName("Dhoni");
        user3.setSubscriptionStartDate(LocalDate.now());
        user3.setSubscriptionEndDate(LocalDate.now().plusMonths(1));
        user3.setAmountPaid(25);
        user3.setPaymentMode("Netbanking");
        user3.setSubscriptionStatus("New");
        user3.setSubscriptionPlans(plan2);
        user3.setPlanDuration(12);
        
        
    }
}

