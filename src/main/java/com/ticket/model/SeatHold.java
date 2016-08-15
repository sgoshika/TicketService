package com.ticket.model;

import java.util.Calendar;
import java.util.List;

/**
 * 
 * SeatHold Class
 *
 */
public class SeatHold {

	private String customerEmail;
	private String seatHoldId;
	private List<Level> levels;
	private Calendar createdTime;
	private boolean confirmed; 
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerEmail == null) ? 0 : customerEmail.hashCode());
		result = prime * result
				+ ((seatHoldId == null) ? 0 : seatHoldId.hashCode());
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
		SeatHold other = (SeatHold) obj;
		if (customerEmail == null) {
			if (other.customerEmail != null)
				return false;
		} else if (!customerEmail.equals(other.customerEmail))
			return false;
		if (seatHoldId == null) {
			if (other.seatHoldId != null)
				return false;
		} else if (!seatHoldId.equals(other.seatHoldId))
			return false;
		return true;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getSeatHoldId() {
		return seatHoldId;
	}

	public void setSeatHoldId(String seatHoldId) {
		this.seatHoldId = seatHoldId;
	}

	public List<Level> getLevels() {
		return levels;
	}

	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

}