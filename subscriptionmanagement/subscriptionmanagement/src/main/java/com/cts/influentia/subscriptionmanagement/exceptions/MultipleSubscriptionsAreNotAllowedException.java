package com.cts.influentia.subscriptionmanagement.exceptions;

public class MultipleSubscriptionsAreNotAllowedException extends Exception{
	private static final long serialVersionUID = 1L;
	public MultipleSubscriptionsAreNotAllowedException(String message) {
		super(message);
	}
}
