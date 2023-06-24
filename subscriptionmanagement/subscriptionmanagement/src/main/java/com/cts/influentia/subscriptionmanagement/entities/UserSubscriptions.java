package com.cts.influentia.subscriptionmanagement.entities;

import java.time.LocalDate;

//import jakarta.persistence.CascadeType;
//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Pattern;

@Entity
public class UserSubscriptions {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(length=10)
	private int SubscriptionId;
	
	@Column(length=10,unique=true)
	private String UserName;

	@Future
	private LocalDate SubscriptionStartDate;
	
	@Future
	private LocalDate SubscriptionEndDate;
	
	@Column(length=10)
	private int AmountPaid;
	
	@Column(length=50)
	@Pattern(regexp="^(Card|NetBanking)$",message="Allowed paymentModes are Card and NetBanking")
	private String PaymentMode;
	@Column(length=12)
	@Pattern(regexp="^(New|Renewed|Cancelled)$",message="The status other this are not allowed")
	private String SubscriptionStatus;
	
	@ManyToOne
	@JoinColumn(name="PlanId")
	private SubscriptionPlans subscriptionPlans;
	
	@Column(length=2)
	private int planDuration;

	public UserSubscriptions(int subscriptionId, String userName, LocalDate subscriptionStartDate,
			LocalDate subscriptionEndDate, int amountPaid, String paymentMode, String subscriptionStatus,SubscriptionPlans subscriptionPlans,int planDuration) {
		super();
		SubscriptionId = subscriptionId;
		UserName = userName;
		SubscriptionStartDate = subscriptionStartDate;
		SubscriptionEndDate = subscriptionEndDate;
		AmountPaid = amountPaid;
		PaymentMode = paymentMode;
		SubscriptionStatus = subscriptionStatus;
		this.subscriptionPlans=subscriptionPlans;
		this.planDuration=planDuration;
	}
	
	
	public UserSubscriptions() {
		super();
	}


	public int getSubscriptionId() {
		return SubscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		SubscriptionId = subscriptionId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public LocalDate getSubscriptionStartDate() {
		return SubscriptionStartDate;
	}

	public void setSubscriptionStartDate(LocalDate subscriptionStartDate) {
		SubscriptionStartDate = subscriptionStartDate;
	}

	public LocalDate getSubscriptionEndDate() {
		return SubscriptionEndDate;
	}

	public void setSubscriptionEndDate(LocalDate subscriptionEndDate) {
		SubscriptionEndDate = subscriptionEndDate;
	}

	public int getAmountPaid() {
		return AmountPaid;
	}

	public void setAmountPaid(int amountPaid) {
		AmountPaid = amountPaid;
	}

	public String getPaymentMode() {
		return PaymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		PaymentMode = paymentMode;
	}

	public String getSubscriptionStatus() {
		return SubscriptionStatus;
	}

	public void setSubscriptionStatus(String subscriptionStatus) {
		SubscriptionStatus = subscriptionStatus;
	}


	public SubscriptionPlans getSubscriptionPlans() {
		return subscriptionPlans;
	}


	public void setSubscriptionPlans(SubscriptionPlans subscriptionPlans) {
		this.subscriptionPlans = subscriptionPlans;
	}


	public int getPlanDuration() {
		return planDuration;
	}


	public void setPlanDuration(int planDuration) {
		this.planDuration = planDuration;
	}
	

	
	
}
