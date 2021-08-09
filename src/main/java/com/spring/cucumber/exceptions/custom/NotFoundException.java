package com.spring.cucumber.exceptions.custom;

import lombok.Data;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -6288606512492165335L;

	private Class clazz;
	private String searchParameter;

	public NotFoundException() {
		this("Resource Not Found");
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Class clazz,String searchParameter) {
		super(clazz.getSimpleName()+" was not found for parameters "+searchParameter);
	}
}