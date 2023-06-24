package com.cts.influentia.subscriptionmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.influentia.subscriptionmanagement.entities.UserSubscriptions;
import com.cts.influentia.subscriptionmanagement.exceptions.MultipleSubscriptionsAreNotAllowedException;
import com.cts.influentia.subscriptionmanagement.mapper.UserSubscriptionsMapper;
import com.cts.influentia.subscriptionmanagement.serviceImpl.UserSubscriptionsServiceImpl;

import jakarta.validation.Valid;

import com.cts.influentia.subscriptionmanagement.serviceImpl.SubscriptionPlansServiceImpl;
import com.cts.influentia.subscriptionmanagement.dto.SubscriptionPlansDTO;
import com.cts.influentia.subscriptionmanagement.dto.UserSubscriptionsDTO;
//import com.cts.influentia.subscriptionmanagement.entities.SubscriptionPlans;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class SubscriptionmanagementController {
	
	@Autowired
	private UserSubscriptionsServiceImpl userSubscriptionsServiceImpl;
	
	@Autowired
	private SubscriptionPlansServiceImpl subscriptionPlansServiceImpl;
	
	@GetMapping("/api/subscriptions/plan")
	public ResponseEntity<List<SubscriptionPlansDTO>> getAllSubscriptionPlans() {
        List<SubscriptionPlansDTO> subscriptionPlansDTO = subscriptionPlansServiceImpl.getListOfSubscriptionPlans();
        return ResponseEntity.ok().body(subscriptionPlansDTO); 
    }
	
	@PostMapping("/api/subscriptions/purchase")
	public ResponseEntity<UserSubscriptions> saveInsert(@Valid @RequestBody UserSubscriptions userSubscriptions) throws MultipleSubscriptionsAreNotAllowedException{
		UserSubscriptions users= userSubscriptionsServiceImpl.insertNewSubscription(userSubscriptions);
		return new ResponseEntity<>(users,HttpStatus.CREATED);
	}
	
	@PutMapping("/api/subscriptions/{subscriptionId}/renew")
	 public ResponseEntity<UserSubscriptionsDTO> renewSubscription(@PathVariable int subscriptionId) {
        Optional<UserSubscriptions> validSubscription = userSubscriptionsServiceImpl.findById(subscriptionId);

        if (validSubscription.isPresent()) {
            UserSubscriptions subscription = validSubscription.get();
            subscription.setSubscriptionStatus("Renewed");
            UserSubscriptionsDTO subscriptionDTO=UserSubscriptionsMapper.mapToDTO(subscription);
            // Update the subscription end date here
            UserSubscriptionsDTO saveduser=userSubscriptionsServiceImpl.updateSubscription(subscriptionDTO);
            return new ResponseEntity<>(saveduser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	@PutMapping("/api/subscriptions/{subscriptionId}/renewf")
	 public ResponseEntity<UserSubscriptions> renewForm(@PathVariable int subscriptionId,@RequestBody UserSubscriptions userSubscription ) throws MultipleSubscriptionsAreNotAllowedException{
       Optional<UserSubscriptions> validSubscription = userSubscriptionsServiceImpl.findById(subscriptionId);

       if (validSubscription.isPresent()) {
           //UserSubscriptions subscription = validSubscription.get();
           userSubscriptionsServiceImpl.deleteById(subscriptionId);
//           UserSubscriptionsDTO subscriptionDTO=UserSubscriptionsMapper.mapToDTO(userSubscription);
//           // Update the subscription end date here
           UserSubscriptions saveduser=userSubscriptionsServiceImpl.insertNewSubscription(userSubscription);
           return new ResponseEntity<>(saveduser,HttpStatus.OK);
       } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
   }
	
	@PutMapping("/api/subscriptions/{subscriptionId}/cancel")
	public ResponseEntity<UserSubscriptionsDTO> cancelSubscription(@PathVariable int subscriptionId) {
        Optional<UserSubscriptions> optionalSubscription = userSubscriptionsServiceImpl.findById(subscriptionId);

        if (optionalSubscription.isPresent()) {
            UserSubscriptions subscription = optionalSubscription.get();
            subscription.setSubscriptionStatus("Cancelled");
            UserSubscriptionsDTO subscriptionDTO=UserSubscriptionsMapper.mapToDTO(subscription);
            // Update the subscription end date here
            userSubscriptionsServiceImpl.removeSubscription(subscriptionDTO);
            return new ResponseEntity<>(subscriptionDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@PutMapping("/api/subscriptions/{subscriptionId}/cancelf")
	 public ResponseEntity<UserSubscriptions> cancelForm(@PathVariable int subscriptionId,@RequestBody UserSubscriptions userSubscription ) throws MultipleSubscriptionsAreNotAllowedException{
      Optional<UserSubscriptions> validSubscription = userSubscriptionsServiceImpl.findById(subscriptionId);

      if (validSubscription.isPresent()) {
          //UserSubscriptions subscription = validSubscription.get();
          userSubscriptionsServiceImpl.deleteById(subscriptionId);
//          UserSubscriptionsDTO subscriptionDTO=UserSubscriptionsMapper.mapToDTO(userSubscription);
//          // Update the subscription end date here
          UserSubscriptions saveduser=userSubscriptionsServiceImpl.insertNewSubscription(userSubscription);
          return new ResponseEntity<>(saveduser,HttpStatus.OK);
      } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  }
	@GetMapping("/api/subscriptions/{UserName}")
	public ResponseEntity<UserSubscriptionsDTO> getSubscriptionByUsername(@PathVariable String UserName){
	    UserSubscriptionsDTO subscription = userSubscriptionsServiceImpl.getSubsciptionByUserName(UserName);
	    return ResponseEntity.ok(subscription);
	}
}
