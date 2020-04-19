package com.blasanka.helthcare.users.exceptions;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3539815578003378409L;
	
	public DataNotFoundException(String message) {
		super(message);
	}
}