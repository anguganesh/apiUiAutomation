package com.ui.api.automation.common.enums;

public enum ApiEnumerations {

	FORWARD_SLASH("/"),
	AUTHORIZATION("Authorization"),
	BEARER("Bearer"),
	SPACE(" "),
	ZERO_STRING("0");
	
	private final String enumValue;
	
	private ApiEnumerations(String value) {
		// TODO Auto-generated constructor stub
		this.enumValue = value;
	}
	
	public String getValue() {
		return this.enumValue;
	}
}
