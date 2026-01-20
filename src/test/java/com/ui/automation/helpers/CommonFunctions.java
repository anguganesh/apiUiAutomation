package com.ui.automation.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonFunctions {

	private final VisibilityHelper visibilityHelper;

	public CommonFunctions(VisibilityHelper visibilityHelper) {
		// TODO Auto-generated constructor stub
		this.visibilityHelper = visibilityHelper;
	}

	@SuppressWarnings("null")
	public void highlightElement(WebElement element) {
		WebDriver driver = null;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: #00fa9a ');", element);
	}

	public void clearFieldData(WebElement locator) {
		visibilityHelper.waitForVisibilityOf(locator);
		highlightElement(locator);
		locator.click();
		locator.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		locator.sendKeys(Keys.BACK_SPACE);
		locator.sendKeys("");
	}

	public String getAttribute(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}

	public void enterData(String dataToEnter, WebElement locator) {
		if (null == dataToEnter || dataToEnter.isEmpty()) {
			return;
		}
		try {
			visibilityHelper.waitForVisibilityOf(locator);
			visibilityHelper.waitForElementToBeInteractable(locator);
			highlightElement(locator);
			clearFieldData(locator);
			locator.sendKeys(dataToEnter);
		} catch (Throwable ex) {
			log.debug("Exception occurred: {}. No data was entered on WebElement {}", ex, locator);
		}
	}

}
