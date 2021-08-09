package com.spring.cucumber.models;

public enum AddressType {
	CURRENT(0),PERMANENT(1),OFFICE(2);
	
	private final int addressType;

	AddressType(int addressType) { 
		this.addressType = addressType;
	}
	
	public int getAddressType() {
		return this.addressType;
	}
}