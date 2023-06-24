package com.cts.influentia.subscriptionmanagement.dto;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//import com.cts.influentia.subscriptionmanagement.entities.SubscriptionCancellations;
import com.cts.influentia.subscriptionmanagement.entities.SubscriptionPlans;
import com.cts.influentia.subscriptionmanagement.entities.UserSubscriptions;

@SpringBootTest
public class SubscriptionCancellationsDTOTests {
	@Test
	public void testSubscriptionCancelletionDTO() {
		SubscriptionPlans subscriptionPlan=new SubscriptionPlans(1,"basic",10);
		UserSubscriptions userSubscription=new UserSubscriptions(22,"Mahi",LocalDate.now().plusDays(1),
				LocalDate.now().plusMonths(1),10,"Card","Cancelled",subscriptionPlan,1);
		
		SubscriptionCancellationsDTO subscriptionCancel = new SubscriptionCancellationsDTO();
		subscriptionCancel.setId(98);
		subscriptionCancel.setCancellationDate(LocalDate.now());
		subscriptionCancel.setCancellationReason("Due to Personel Reason");
		subscriptionCancel.setUserSubscriptions(userSubscription);
		
		//subscriptionCancelRepository.save(subscriptionCancel);
		
		Assertions.assertEquals(subscriptionCancel.getId(),98);
		Assertions.assertEquals(subscriptionCancel.getCancellationDate(),LocalDate.now());
		Assertions.assertEquals(subscriptionCancel.getCancellationReason(),"Due to Personel Reason");
		Assertions.assertEquals(userSubscription, subscriptionCancel.getUserSubscriptions());
		
		
	}
	
	@Test
	public void testSubscriptionCancelEntityConstructorCase() {
		SubscriptionPlans subscriptionPlan=new SubscriptionPlans(1,"basic",10);
		UserSubscriptions userSubscription=new UserSubscriptions(22,"Mahi",LocalDate.now().plusDays(1),
				LocalDate.now().plusMonths(1),10,"Card","Cancelled",subscriptionPlan,1);
		
		SubscriptionCancellationsDTO subscriptionCancel = new SubscriptionCancellationsDTO(98,LocalDate.now(),"Due to Personel Reason",userSubscription);
		
		Assertions.assertEquals(userSubscription, subscriptionCancel.getUserSubscriptions());
		
		
	}
}
