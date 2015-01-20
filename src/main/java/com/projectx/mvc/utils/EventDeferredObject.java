package com.projectx.mvc.utils;

import org.springframework.web.context.request.async.DeferredResult;

public class EventDeferredObject extends DeferredResult<String> implements Runnable {

	private Long customerId;
	
	private String email;
	

	@Override
	public void run() {
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Execution completed");

	}
	
	
	public EventDeferredObject() {

	}

	public EventDeferredObject(Long customerId, String email) {
		this.customerId = customerId;
		this.email = email;
	}

	


	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	@Override
	public String toString() {
		return "EventDeferredObject [customerId=" + customerId + ", email="
				+ email + "]";
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventDeferredObject other = (EventDeferredObject) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}



}
