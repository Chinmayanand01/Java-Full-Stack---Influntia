package com.cts.influentia.subscriptionmanagement.entities;

//import java.util.List;


//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToMany;

@Entity
public class SubscriptionPlans {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(length=10)
	private int PlanId;
	@Column(length=100)
	private String Name;
	@Column(length=10)
	private int PricePerMonth;
	
//	@OneToMany(mappedBy="subscriptionPlans",cascade=CascadeType.ALL)
//	private List<UserSubscriptions> userSubscriptions;

	public SubscriptionPlans(int planId, String name, int pricePerMonth/*,List<UserSubscriptions> userSubscriptions*/) {
		PlanId = planId;
		Name = name;
		PricePerMonth = pricePerMonth;
		//this.userSubscriptions=userSubscriptions;
	}

	public SubscriptionPlans() {
		super();
	}

	public int getPlanId() {
		return PlanId;
	}

	public void setPlanId(int planId) {
		PlanId = planId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getPricePerMonth() {
		return PricePerMonth;
	}

	public void setPricePerMonth(int pricePerMonth) {
		PricePerMonth = pricePerMonth;
	}

//	public List<UserSubscriptions> getUserSubscriptions() {
//		return userSubscriptions;
//	}
//
//	public void setUserSubscriptions(List<UserSubscriptions> userSubscriptions) {
//		this.userSubscriptions = userSubscriptions;
//	}
//	
	
	
}
