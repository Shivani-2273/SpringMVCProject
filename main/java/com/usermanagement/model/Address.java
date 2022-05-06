package com.usermanagement.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private int addressId;

	@Column(name = "street_line")
	private String addressLine;
	private String city;
	private String state;
	private String pin;

	@Transient
	private String removeAddressId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Address() {
		super();
	}


	public Address(int addressId, String addressLine, String city, String state, String pin, String removeAddressId,
			User user) {
		super();
		this.addressId = addressId;
		this.addressLine = addressLine;
		this.city = city;
		this.state = state;
		this.pin = pin;
		this.removeAddressId = removeAddressId;
		this.user = user;
	}




	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
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

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getRemoveAddressId() {
		return removeAddressId;
	}

	public void setRemoveAddressId(String removeAddressId) {
		this.removeAddressId = removeAddressId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
