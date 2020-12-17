package com.qa.api.gorest.Pojo;

public class BookingDates {
	private String checkin;
    private String checkout;
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public BookingDates(String checkin, String checkout) {
		super();
		this.checkin = checkin;
		this.checkout = checkout;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
}
