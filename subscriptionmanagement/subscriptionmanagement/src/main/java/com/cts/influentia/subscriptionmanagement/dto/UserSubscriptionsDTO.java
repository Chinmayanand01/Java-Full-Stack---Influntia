package com.cts.influentia.subscriptionmanagement.dto;

import java.time.LocalDate;

import com.cts.influentia.subscriptionmanagement.entities.SubscriptionPlans;

public class UserSubscriptionsDTO {
	private int SubscriptionId;
	private String UserName;
	private LocalDate SubscriptionStartDate;
	private LocalDate SubscriptionEndDate;
	private int AmountPaid;
	private String PaymentMode;
	private String SubscriptionStatus;
	private SubscriptionPlans subscriptionPlans;
	private int planDuration;
	
	public UserSubscriptionsDTO() {
		super();
	}

	public UserSubscriptionsDTO(int subscriptionId, String userName, LocalDate subscriptionStartDate,
			LocalDate subscriptionEndDate, int amountPaid, String paymentMode, String subscriptionStatus,
			SubscriptionPlans subscriptionPlans,int planDuration) {
		super();
		SubscriptionId = subscriptionId;
		UserName = userName;
		SubscriptionStartDate = subscriptionStartDate;
		SubscriptionEndDate = subscriptionEndDate;
		AmountPaid = amountPaid;
		PaymentMode = paymentMode;
		SubscriptionStatus = subscriptionStatus;
		this.subscriptionPlans = subscriptionPlans;
		this.planDuration=planDuration;
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
