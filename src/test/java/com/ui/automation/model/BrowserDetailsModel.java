package com.ui.automation.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrowserDetailsModel extends BaseScenario {

	String browserName;
	String baseUrl;
	Boolean headless;
	Boolean remote;
	Integer pageLoadTimeOut;
	Long explicitWait;
	Long implicitWait;
	Long waitForAttributeTimeout;
	Integer waitForAttributePollingInterval;

	
}

