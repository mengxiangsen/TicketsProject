package com.meng;

public class User {
   
	String phone;
	String password;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String phone, String password) {
		super();
		this.phone = phone;
		this.password = password;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
