package com.travel.web.controllers.admin.booking;

import java.sql.Date;

public class Booking {
	private int id;
	private String tripName;
	private double price;
	private int noOfAdults;
	private int noOfChildren;
	private String booker;
	private Date bookingDate;
	private boolean status;
	public Booking(int id, String tripName, double price, int noOfAdults, int noOfChildren, String booker,
			Date bookingDate, boolean status) {
		super();
		this.id = id;
		this.tripName = tripName;
		this.price = price;
		this.noOfAdults = noOfAdults;
		this.noOfChildren = noOfChildren;
		this.booker = booker;
		this.bookingDate = bookingDate;
		this.status = status;
	}
	public Booking(String tripName, double price, int noOfAdults, int noOfChildren, String booker, Date bookingDate,
			boolean status) {
		super();
		this.tripName = tripName;
		this.price = price;
		this.noOfAdults = noOfAdults;
		this.noOfChildren = noOfChildren;
		this.booker = booker;
		this.bookingDate = bookingDate;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTripName() {
		return tripName;
	}
	public void setTripName(String tripName) {
		this.tripName = tripName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNoOfAdults() {
		return noOfAdults;
	}
	public void setNoOfAdults(int noOfAdults) {
		this.noOfAdults = noOfAdults;
	}
	public int getNoOfChildren() {
		return noOfChildren;
	}
	public void setNoOfChildren(int noOfChildren) {
		this.noOfChildren = noOfChildren;
	}
	public String getBooker() {
		return booker;
	}
	public void setBooker(String booker) {
		this.booker = booker;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getStatus(boolean status) {
		if (status) {
			return "Active";
		}
		else {
			return "Inactive";
		}
	}
}
