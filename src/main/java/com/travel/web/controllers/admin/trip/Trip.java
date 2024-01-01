package com.travel.web.controllers.admin.trip;

import java.sql.Date;

public class Trip {
	private int id;
	private String tripName;
	private double price;
	private String img_url;
	private String desc_url;
	private String address;
	private Date startDate;
	private Date endDate;
	private boolean status;
	
	
	
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



	public Trip(int id, String tripName, double price, String img_url, String desc_url, String address,
			Date startDate, Date endDate, boolean status) {
		super();
		this.id = id;
		this.tripName = tripName;
		this.price = price;
		this.img_url = img_url;
		this.desc_url = desc_url;
		this.address = address;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	
	
	public Trip(String tripName, double price, String img_url, String desc_url, String address, Date startDate,
			Date endDate, boolean status) {
		super();
		this.tripName = tripName;
		this.price = price;
		this.img_url = img_url;
		this.desc_url = desc_url;
		this.address = address;
		this.startDate = startDate;
		this.endDate = endDate;
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



	public String getImg_url() {
		return img_url;
	}



	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}



	public String getDesc_url() {
		return desc_url;
	}



	public void setDesc_url(String desc_url) {
		this.desc_url = desc_url;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}	
}
