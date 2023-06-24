package com.cts.influentia.subscriptionmanagement.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.influentia.subscriptionmanagement.Repository.UserSubscriptionsRepository;
import com.cts.influentia.subscriptionmanagement.entities.SubscriptionPlans;
import com.cts.influentia.subscriptionmanagement.entities.UserSubscriptions;
import com.cts.influentia.subscriptionmanagement.mapper.UserSubscriptionsMapper;
import com.cts.influentia.subscriptionmanagement.dto.UserSubscriptionsDTO;
import com.cts.influentia.subscriptionmanagement.exceptions.MultipleSubscriptionsAreNotAllowedException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserSubscriptionsServiceImplTests {
	@Mock
    private UserSubscriptionsRepository userSubscriptionsRepository;

    @InjectMocks
    private UserSubscriptionsServiceImpl userSubscriptionsService;
    
    @Test
    void testInsertNewSubscriptionWhenUserHasExistingSubscription() {
        // Arrange
        String existingUserName = "testuser";
        UserSubscriptions existingSubscription = new UserSubscriptions();
        existingSubscription.setUserName(existingUserName);
        when(userSubscriptionsRepository.existsByUserName(existingUserName)).thenReturn(true);
        
        UserSubscriptions newSubscription = new UserSubscriptions();
        newSubscription.setUserName(existingUserName);
        
        // Act & Assert
        assertThrows(MultipleSubscriptionsAreNotAllowedException.class, () -> {
        	userSubscriptionsService.insertNewSubscription(newSubscription);
        });
    }
    
    @Test
    void testInsertNewSubscriptionWhenUserDoesNotHaveExistingSubscriptionForDuration12() throws MultipleSubscriptionsAreNotAllowedException{
    	SubscriptionPlans plan1=new SubscriptionPlans(); 
    	plan1.setPlanId(1);
    	plan1.setName("basic");
    	plan1.setPricePerMonth(10);
    	
    	SubscriptionPlans plan2=new SubscriptionPlans(); 
    	plan2.setPlanId(2);
    	plan2.setName("pro");
    	plan2.setPricePerMonth(25);
    	
    	UserSubscriptions newSubscription1 = new UserSubscriptions();
        newSubscription1.setUserName("Mahi");
        newSubscription1.setSubscriptionStartDate(LocalDate.now());
        newSubscription1.setPlanDuration(12);
        newSubscription1.setAmountPaid(25);
        newSubscription1.setSubscriptionPlans(plan2);
        
        UserSubscriptions newSubscription2 = new UserSubscriptions();
        newSubscription2.setUserName("Mahi2");
        newSubscription2.setSubscriptionStartDate(LocalDate.now());
        newSubscription2.setPlanDuration(12);
        newSubscription2.setAmountPaid(10);
        newSubscription2.setSubscriptionPlans(plan1);
        
        when(userSubscriptionsRepository.save(newSubscription1)).thenReturn(newSubscription1);
        when(userSubscriptionsRepository.save(newSubscription2)).thenReturn(newSubscription2);
        
        UserSubscriptions result1 = userSubscriptionsService.insertNewSubscription(newSubscription1);
        UserSubscriptions result2 = userSubscriptionsService.insertNewSubscription(newSubscription2);
        
        assertEquals(255,result1.getAmountPaid());
        assertEquals(110,result2.getAmountPaid());
    }
    @Test
    void testInsertNewSubscriptionWhenUserDoesNotHaveExistingSubscriptionForDuration6() throws MultipleSubscriptionsAreNotAllowedException{
    	SubscriptionPlans plan1=new SubscriptionPlans(); 
    	plan1.setPlanId(1);
    	plan1.setName("basic");
    	plan1.setPricePerMonth(10);
    	
    	SubscriptionPlans plan2=new SubscriptionPlans(); 
    	plan2.setPlanId(2);
    	plan2.setName("pro");
    	plan2.setPricePerMonth(25);
    	
    	UserSubscriptions newSubscription1 = new UserSubscriptions();
        newSubscription1.setUserName("Mahi");
        newSubscription1.setSubscriptionStartDate(LocalDate.now());
        newSubscription1.setPlanDuration(6);
        newSubscription1.setAmountPaid(25);
        newSubscription1.setSubscriptionPlans(plan2);
        
        UserSubscriptions newSubscription2 = new UserSubscriptions();
        newSubscription2.setUserName("Mahi2");
        newSubscription2.setSubscriptionStartDate(LocalDate.now());
        newSubscription2.setPlanDuration(6);
        newSubscription2.setAmountPaid(10);
        newSubscription2.setSubscriptionPlans(plan1);
        
        when(userSubscriptionsRepository.save(newSubscription1)).thenReturn(newSubscription1);
        when(userSubscriptionsRepository.save(newSubscription2)).thenReturn(newSubscription2);
        
        UserSubscriptions result1 = userSubscriptionsService.insertNewSubscription(newSubscription1);
        UserSubscriptions result2 = userSubscriptionsService.insertNewSubscription(newSubscription2);
        
        assertEquals(135,result1.getAmountPaid());
        assertEquals(57,result2.getAmountPaid());
    }
    
    @Test
    void testInsertNewSubscriptionWhenUserDoesNotHaveExistingSubscriptionForDuration3() throws MultipleSubscriptionsAreNotAllowedException{
    	SubscriptionPlans plan1=new SubscriptionPlans(); 
    	plan1.setPlanId(1);
    	plan1.setName("basic");
    	plan1.setPricePerMonth(10);
    	
    	SubscriptionPlans plan2=new SubscriptionPlans(); 
    	plan2.setPlanId(2);
    	plan2.setName("pro");
    	plan2.setPricePerMonth(25);
    	
    	UserSubscriptions newSubscription2 = new UserSubscriptions();
        newSubscription2.setUserName("Mahi");
        newSubscription2.setSubscriptionStartDate(LocalDate.now());
        newSubscription2.setPlanDuration(3);
        newSubscription2.setAmountPaid(25);
        newSubscription2.setSubscriptionPlans(plan2);
        
        UserSubscriptions newSubscription3 = new UserSubscriptions();
        newSubscription3.setUserName("Mahi2");
        newSubscription3.setSubscriptionStartDate(LocalDate.now());
        newSubscription3.setPlanDuration(3);
        newSubscription3.setAmountPaid(10);
        newSubscription3.setSubscriptionPlans(plan1);
        
        when(userSubscriptionsRepository.save(newSubscription2)).thenReturn(newSubscription2);
        when(userSubscriptionsRepository.save(newSubscription3)).thenReturn(newSubscription3);
        
        UserSubscriptions result2 = userSubscriptionsService.insertNewSubscription(newSubscription2);
        UserSubscriptions result3 = userSubscriptionsService.insertNewSubscription(newSubscription3);
        
        assertEquals(71,result2.getAmountPaid());
        assertEquals(29,result3.getAmountPaid());
    }
    
    @Test
    void testInsertNewSubscriptionWhenUserDoesNotHaveExistingSubscription() throws MultipleSubscriptionsAreNotAllowedException {
        // Arrange
    	SubscriptionPlans plan1=new SubscriptionPlans(); 
    	plan1.setPlanId(1);
    	plan1.setName("basic");
    	plan1.setPricePerMonth(10);
    	
        String newUserName = "newuser";
        UserSubscriptions newSubscription = new UserSubscriptions();
        newSubscription.setUserName(newUserName);
        newSubscription.setSubscriptionStartDate(LocalDate.now());
        newSubscription.setPlanDuration(1);
        newSubscription.setAmountPaid(10);
        newSubscription.setSubscriptionPlans(plan1);
        
        
        when(userSubscriptionsRepository.existsByUserName(newUserName)).thenReturn(false);
        when(userSubscriptionsRepository.save(newSubscription)).thenReturn(newSubscription);
        
        // Act
        UserSubscriptions result = userSubscriptionsService.insertNewSubscription(newSubscription);
        
        // Assert
        assertEquals(10, result.getAmountPaid());
        assertEquals(LocalDate.now().plusMonths(1), result.getSubscriptionEndDate());
    }
    
    @Test
    public void testRenewSubscription() {
    	SubscriptionPlans plan1=new SubscriptionPlans(); 
    	plan1.setPlanId(1);
    	plan1.setName("basic");
    	plan1.setPricePerMonth(10);
    	
    	UserSubscriptionsDTO userSubscriptionDTO =new UserSubscriptionsDTO();
    	userSubscriptionDTO.setSubscriptionId(105);
        userSubscriptionDTO.setUserName("JeffBezos");
        userSubscriptionDTO.setSubscriptionStartDate(LocalDate.now().plusDays(1));
        userSubscriptionDTO.setSubscriptionEndDate(LocalDate.now().plusDays(1).plusMonths(6));
        userSubscriptionDTO.setAmountPaid(10);
        userSubscriptionDTO.setPaymentMode("NetBanking");
        userSubscriptionDTO.setSubscriptionStatus("Renewed");
        userSubscriptionDTO.setSubscriptionPlans(plan1);
        userSubscriptionDTO.setPlanDuration(1);
        
        
        UserSubscriptions updatedUser=UserSubscriptionsMapper.mapToJPA(userSubscriptionDTO);
     // given
        when(userSubscriptionsRepository.save(any(UserSubscriptions.class))).thenReturn(updatedUser);
        //when(UserSubscriptionsMapper.mapToDTO(any(UserSubscriptions.class))).thenReturn(userSubscriptionDTO);

        // when
        UserSubscriptionsDTO result = userSubscriptionsService.updateSubscription(userSubscriptionDTO);
        
        assertEquals(updatedUser.getSubscriptionId(),result.getSubscriptionId());
    }
    
    @Test
    void testFindByIdWhenSubscriptionExists() {
        // Arrange
        int subscriptionId = 1;
        UserSubscriptions expectedSubscription = new UserSubscriptions();
        expectedSubscription.setSubscriptionId(subscriptionId);
        when(userSubscriptionsRepository.findById(subscriptionId)).thenReturn(Optional.of(expectedSubscription));

        // Act
        Optional<UserSubscriptions> result = userSubscriptionsService.findById(subscriptionId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedSubscription, result.get());
    }

    @Test
    void testFindByIdWhenSubscriptionDoesNotExist() {
        // Arrange
        int subscriptionId = 1;
        when(userSubscriptionsRepository.findById(subscriptionId)).thenReturn(Optional.empty());

        // Act
        Optional<UserSubscriptions> result = userSubscriptionsService.findById(subscriptionId);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void testRemoveSubscription() {
        // Create a UserSubscriptionsDTO object to represent the subscription to be removed
    	SubscriptionPlans plan1=new SubscriptionPlans(); 
    	plan1.setPlanId(1);
    	plan1.setName("basic");
    	plan1.setPricePerMonth(10);
    	
        UserSubscriptionsDTO userSubscriptionDTO = new UserSubscriptionsDTO();
        userSubscriptionDTO.setSubscriptionId(105);
        userSubscriptionDTO.setUserName("Narayana");
        userSubscriptionDTO.setSubscriptionStartDate(LocalDate.now().plusDays(1));
        userSubscriptionDTO.setSubscriptionEndDate(LocalDate.now().plusDays(1).plusMonths(6));
        userSubscriptionDTO.setAmountPaid(10);
        userSubscriptionDTO.setPaymentMode("NetBanking");
        userSubscriptionDTO.setSubscriptionStatus("New");
        userSubscriptionDTO.setSubscriptionPlans(plan1);
        userSubscriptionDTO.setPlanDuration(1);

        // Map the DTO to a JPA entity
        UserSubscriptions userSubscription = UserSubscriptionsMapper.mapToJPA(userSubscriptionDTO);

        // Mock the repository to return the entity when save() is called
        when(userSubscriptionsRepository.save(any(UserSubscriptions.class))).thenReturn(userSubscription);

        // Call the service method to remove the subscription
        userSubscriptionsService.removeSubscription(userSubscriptionDTO);

        // Verify that the repository's save() method was called with the correct argument
        // verify(userSubscriptionsRepository).save(userSubscription);
    }
    
    @Test
    public void testGetSubsciptionByUserName(){
    	
    	SubscriptionPlans plan1=new SubscriptionPlans(); 
    	plan1.setPlanId(1);
    	plan1.setName("basic");
    	plan1.setPricePerMonth(10);
    	
        String userName = "Sundhar";
        UserSubscriptions jpaUser = new UserSubscriptions();
        jpaUser.setSubscriptionId(302);
        jpaUser.setUserName(userName);
        jpaUser.setSubscriptionStartDate(LocalDate.now().plusDays(1));
        jpaUser.setSubscriptionEndDate(LocalDate.now().plusDays(1).plusMonths(6));
        jpaUser.setAmountPaid(57);
        jpaUser.setPaymentMode("NetBanking");
        jpaUser.setSubscriptionStatus("Renewed");
        jpaUser.setSubscriptionPlans(plan1);
        jpaUser.setPlanDuration(6);
        

       when(userSubscriptionsRepository.findByUserName(userName)).thenReturn(jpaUser);

        UserSubscriptionsDTO expectedDto = new UserSubscriptionsDTO();
        expectedDto.setSubscriptionId(302);
        expectedDto.setUserName("Sundhar");
        expectedDto.setSubscriptionStartDate(LocalDate.now().plusDays(1));
        expectedDto.setSubscriptionEndDate(LocalDate.now().plusDays(1).plusMonths(6));
        expectedDto.setAmountPaid(57);
        expectedDto.setPaymentMode("NetBanking");
        expectedDto.setSubscriptionStatus("Renewed");
        expectedDto.setSubscriptionPlans(plan1);
        expectedDto.setPlanDuration(6);

       // UserSubscriptionsDTO expectedout=userSubscriptionsService.getSubsciptionByUserName(userName);
        UserSubscriptionsDTO actualDto = userSubscriptionsService.getSubsciptionByUserName(userName);
        verify(userSubscriptionsRepository, times(1)).findByUserName(userName);
        assertEquals(expectedDto.getUserName(), actualDto.getUserName());
        assertThat(actualDto).isNotNull();
    }
}
