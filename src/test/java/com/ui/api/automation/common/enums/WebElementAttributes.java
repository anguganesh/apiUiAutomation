package com.ui.api.automation.common.enums;

public enum WebElementAttributes {

	TITLE("title"),
	NAME("name"),
	ID("id");
	
	private final String attributeName;
	
	private WebElementAttributes(String attributeName) {
		// TODO Auto-generated constructor stub
		this.attributeName = attributeName;
	}
	
	public String getAttributeName() {
		return this.attributeName;
	}
	
}