package com.cts.influentia.subscriptionmanagement.dto;
public class SubscriptionPlansDTO {
	private int PlanId;
	private String Name;
	private int PricePerMonth;
	public SubscriptionPlansDTO(int planId, String name, int pricePerMonth) {
		super();
		PlanId = planId;
		Name = name;
		PricePerMonth = pricePerMonth;
	}
	public SubscriptionPlansDTO() {
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
	
}
