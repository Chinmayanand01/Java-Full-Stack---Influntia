package com.cts.influentia.subscriptionmanagement.dto;

import java.time.LocalDate;

import com.cts.influentia.subscriptionmanagement.entities.UserSubscriptions;

public class SubscriptionCancellationsDTO {
	private int Id;
	private LocalDate CancellationDate;
	private String CancellationReason;
	private UserSubscriptions userSubscription;
	
	public SubscriptionCancellationsDTO() {
		super();
	}

	public SubscriptionCancellationsDTO(int id, LocalDate cancellationDate, String cancellationReason,
			UserSubscriptions userSubscription) {
		super();
		Id = id;
		CancellationDate = cancellationDate;
		CancellationReason = cancellationReason;
		this.userSubscription = userSubscription;
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
