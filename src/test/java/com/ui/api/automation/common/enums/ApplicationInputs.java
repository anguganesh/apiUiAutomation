package com.ui.api.automation.common.enums;

public enum ApplicationInputs {

	EMPTY_STRING("");
	
	private final String enumValue;
	
	private ApplicationInputs(String value) {
		// TODO Auto-generated constructor stub
		this.enumValue = value;
	}
	
	public String getValue() {
		return this.enumValue;
	}
}