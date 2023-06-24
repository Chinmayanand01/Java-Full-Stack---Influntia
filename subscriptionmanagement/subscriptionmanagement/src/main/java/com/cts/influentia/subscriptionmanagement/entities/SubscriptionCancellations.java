package com.cts.influentia.subscriptionmanagement.entities;

import java.time.LocalDate;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;


@Entity
public class SubscriptionCancellations {
	
	@Id
	@Column(length=10)
	private int Id;
	
	private LocalDate CancellationDate;
	@Column(length=100)
	private String CancellationReason;
	
//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="FKSubId",referencedColumnName="SubscriptionId")
	
	@OneToOne
	@JoinColumn(name="SubscriptionId")
	private UserSubscriptions userSubscription;
	
	public SubscriptionCancellations(int id, int subscription_Id, LocalDate cancellationDate, String cancellationReason,UserSubscriptions userSubscription) {
		super();
		Id = id;
		CancellationDate = cancellationDate;
		CancellationReason = cancellationReason;
		this.userSubscription=userSubscription;
	}
	
	public SubscriptionCancellations() {
		super();
	}

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public LocalDate getCancellationDate() {
		return CancellationDate;
	}
	public void setCancellationDate(LocalDate cancellationDate) {
		CancellationDate = cancellationDate;
	}
	public String getCancellationReason() {
		return CancellationReason;
	}
	public void setCancellationReason(String cancellationReason) {
		CancellationReason = cancellationReason;
	}

	public UserSubscriptions getUserSubscriptions() {
		return userSubscription;
	}

	public void setUserSubscriptions(UserSubscriptions userSubscription) {
		this.userSubscription = userSubscription;
	}
	
	
}
