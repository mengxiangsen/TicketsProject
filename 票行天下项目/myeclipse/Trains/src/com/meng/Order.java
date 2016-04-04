package com.meng;

public class Order {

	int orderNum;
	String name;
	String phone;
	String identityCard;
	String startPlace;
	String endPlace;
	String date;
	String vehicleNum;
	String price;
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getStartPlace() {
		return startPlace;
	}
	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}
	public String getEndPlace() {
		return endPlace;
	}
	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getVehicleNum() {
		return vehicleNum;
	}
	public void setVehicleNum(String vehicleNum) {
		this.vehicleNum = vehicleNum;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int orderNum, String name, String phone, String identityCard,
			String startPlace, String endPlace, String date, String vehicleNum,
			String price) {
		super();
		this.orderNum = orderNum;
		this.name = name;
		this.phone = phone;
		this.identityCard = identityCard;
		this.startPlace = startPlace;
		this.endPlace = endPlace;
		this.date = date;
		this.vehicleNum = vehicleNum;
		this.price = price;
	}
	
}
