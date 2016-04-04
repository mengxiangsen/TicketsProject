package com.meng;

public class Vehicle {
 
	String vehicleNum;
	String startPlace;
	String endPlace;
	String startTime;
	String endTime;
	String type;
	String price;
	String date;
	

	public String getDate() {
		return date;
	}

	public Vehicle(String vehicleNum, String startPlace, String endPlace,
			String startTime, String endTime, String type, String price,
			String date) {
		super();
		this.vehicleNum = vehicleNum;
		this.startPlace = startPlace;
		this.endPlace = endPlace;
		this.startTime = startTime;
		this.endTime = endTime;
		this.type = type;
		this.price = price;
		this.date = date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public String getVehicleNum() {
		return vehicleNum;
	}
	public void setVehicleNum(String vehicleNum) {
		this.vehicleNum = vehicleNum;
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
