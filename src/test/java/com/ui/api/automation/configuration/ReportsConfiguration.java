package com.ui.api.automation.configuration;

import io.qameta.allure.Attachment;

public class ReportsConfiguration {
	
private final Hooks hooks;
	
	public ReportsConfiguration(Hooks hooks) {
		// TODO Auto-generated constructor stub
		this.hooks = hooks;
	}
	
	
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] takesScreenshotForAllureReport() {
		return hooks.getScreenshotAsBytes();		
	}

}
