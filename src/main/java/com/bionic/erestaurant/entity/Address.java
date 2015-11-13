package com.bionic.erestaurant.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int address_id;
	

	@ManyToOne
	@JoinColumn(name="user_id")
	private Users user;	

	
	private String address1;
	
	public Address(String address1, String city, String state, String country, String zip) {
		this.address1 = address1;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
	}

	private String city;
	private String state;
	private String country;
	private String zip;
	
	public Address(){}

	public int getAddressId() {
		return address_id;
	}

	public void setAddressId(int address_id) {
		this.address_id = address_id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}
