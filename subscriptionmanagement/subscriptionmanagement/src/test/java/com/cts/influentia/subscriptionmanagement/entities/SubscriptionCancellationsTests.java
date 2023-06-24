package com.cts.influentia.subscriptionmanagement.entities;

import java.time.LocalDate;
//import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import com.cts.influentia.subscriptionmanagement.Repository.SubscriptionCancelRepository;

@SpringBootTest
public class SubscriptionCancellationsTests {
	
//	@Autowired
//	private SubscriptionCancelRepository subscriptionCancelRepository;
	
	@Test
	public void testSubscriptionCancelEntity() {
		SubscriptionPlans subscriptionPlan=new SubscriptionPlans(1,"basic",10);
		UserSubscriptions userSubscription=new UserSubscriptions(22,"Mahi",LocalDate.now().plusDays(1),
				LocalDate.now().plusMonths(1),10,"Card","Cancelled",subscriptionPlan,1);
		
		SubscriptionCancellations subscriptionCancel = new SubscriptionCancellations();
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
		
		SubscriptionCancellations subscriptionCancel = new SubscriptionCancellations(98,22,LocalDate.now(),"Due to Personel Reason",userSubscription);
		
		Assertions.assertEquals(userSubscription, subscriptionCancel.getUserSubscriptions());
		
		
	}

}
