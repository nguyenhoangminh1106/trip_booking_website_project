package com.travel.web.controllers.admin.review;

import java.sql.Date;

public class Review {
	private int id;
	private String reviewer;
	private int post_id;
	private int rating;
	private Date reviewDate;
	private String message;
	

	private boolean status;
	
	public Review(int id, String reviewer, int post_id, int rating, Date reviewDate, String message, boolean status) {
		super();
		this.id = id;
		this.reviewer = reviewer;
		this.post_id = post_id;
		this.rating = rating;
		this.reviewDate = reviewDate;
		this.message = message;
		this.status = status;
	}
	public Review(String reviewer, int post_id, int rating, Date reviewDate, String message, boolean status) {
		super();
		this.reviewer = reviewer;
		this.post_id = post_id;
		this.rating = rating;
		this.reviewDate = reviewDate;
		this.message = message;
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
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
