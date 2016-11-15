package com.restws.java.main.dto;

public class Employee {

	private String id;
	private String name;
	private String city;
	private String phoneNumber;
	
	public Employee(){}

	public Employee(String id, String name, String city, String phoneNum) {
		this.id=id;
		this.name=name;
		this.city=city;
		this.phoneNumber=phoneNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
