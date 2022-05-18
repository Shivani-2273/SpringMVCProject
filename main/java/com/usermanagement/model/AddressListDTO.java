package com.usermanagement.model;

import java.util.List;

public class AddressListDTO {
	private List<Address> address;

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "addressListDTO [addressmodel=" + address + "]";
	}
	
	
}
