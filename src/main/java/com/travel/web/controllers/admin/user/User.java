package com.travel.web.controllers.admin.user;

public class User {
	private int id;
	private String userName;
	private String password;
	private String fullName;
	private String email;
	private String phoneNumber;
	private String address;
	private boolean status;
	private int role_id;
	
	public User(int id, String userName, String password, String fullName, String email, String phoneNumber,
			String address, boolean status, int role_id) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.status = status;
		this.role_id = role_id;
	}
	
	public User(String userName, String password, String fullName, String email, String phoneNumber, String address, boolean status,
			int role_id) {
		super();
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.status = status;
		this.role_id = role_id;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isStatus() {
		return status;
	}
	
	public String getStatus(boolean status) {
		if (status) {
			return "Active";
		}
		else {
			return "Inactive";
		}
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getRole_id() {
		return role_id;
	}
	
	public String getRole(int role_id) {
		if (role_id == 0) {
			return "User";
		}
		else if (role_id == 1) {
			return "Admin";
		}
		return null;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	
	
	
}
