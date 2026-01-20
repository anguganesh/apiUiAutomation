package com.ui.automation.helpers;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.ui.api.automation.common.enums.WebElementAttributes;
import com.ui.api.automation.configuration.Hooks;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VisibilityHelper {

	private final Hooks hook;
  

	  public VisibilityHelper(Hooks hook) {
	    this.hook = hook;
	  }

	  /**
	   * Waits until the given element is visible. The element must be present on the DOM before the
	   * waiting starts
	   *
	   * @param element Element to check
	   */
	  public void waitForVisibilityOf(WebElement element) {
	    hook.getWait().until(ExpectedConditions.visibilityOf(element));
	  }

	  public void waitForElementEnabled(WebElement element) {
	    hook.getWait().until((ExpectedCondition<Boolean>) driver -> element.isEnabled());
	  }

	  public void waitForInVisibilityOf(WebElement element) {
	    hook.getWait().until(ExpectedConditions.invisibilityOf(element));
	  }

	  public void waitForInvisibilityOfElements(List<WebElement> elements) {
	    hook.getWait().until(ExpectedConditions.invisibilityOfAllElements(elements));
	  }

	  public void waitForInvisibilityOfElementsWithText(String locator, String elementText) {
	    hook.getWait()
	        .until(
	            ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(locator), elementText));
	  }

	  public void waitForElementToBeInteractable(WebElement element) {
	    hook.getWait().until(ExpectedConditions.elementToBeClickable(element));
	  }

	  public void waitForElementToBeSelected(WebElement element) {
	    hook.getWait().until(ExpectedConditions.elementToBeSelected(element));
	  }

	  public void waitForElementsVisibility(List<WebElement> elements) {
	    hook.getWait().until(ExpectedConditions.visibilityOfAllElements(elements));
	  }

	  public void waitForElementAttributeToBeLoaded(
	      WebElement element, WebElementAttributes attribute) {
	    /*hook.getWait()
	        .withTimeout(Duration.ofSeconds(hook.getBrowserDetails().getWaitForAttributeTimeout()))
	        .pollingEvery(Duration.ofSeconds(hook.getBrowserDetails().getWaitForAttributePollingInterval()))
	        .until(
	            (ExpectedCondition<Boolean>)
	                driver ->
	                    !Objects.equals(
	                        commonFunctions.getAttribute(element, attribute.getAttributeName()),
	                        ApplicationInputs.EMPTY_STRING.getValue()));*/
	  }

	  /**
	   * Waits for presence and visibility of the element matched by given selector. The element can be
	   * present in the DOM or not before the waiting starts
	   *
	   * @param by Selector of the element
	   */
	  public void waitForPresenceOf(By by) {
	    hook.getWait().until(ExpectedConditions.visibilityOfElementLocated(by));
	  }

	  public void waitForPageLoad(int seconds) {
		hook.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(seconds));
	  }

	  public boolean waitForContentToLoad(int seconds) {		
	    WebDriverWait wait = new WebDriverWait(this.hook.getDriver(), Duration.ofSeconds(seconds));
	    ExpectedCondition<Boolean> jsLoad =
	        d ->
	            ((JavascriptExecutor) d)
	                .executeScript("return document.readyState")
	                .toString()
	                .equals("complete");
	    return wait.until(jsLoad);
	  }

	  public void waitForAttributeToLoad(
	      int seconds, WebElement element, String attribute, String value) {		  
	    WebDriverWait wait = new WebDriverWait(hook.getDriver(), Duration.ofSeconds(seconds));
	    wait.until(ExpectedConditions.attributeContains(element, attribute, value));
	  }

	  public void waitForAttributeToLoad(WebElement element, String attribute) {
	    hook.getWait().until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
	  }

	  public void waitForAttributeToLoad(int seconds, WebElement element, String attribute) {
	    WebDriverWait wait = new WebDriverWait(this.hook.getDriver(), Duration.ofSeconds(seconds));
	    wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
	  }

	  public void waitForBackEndToBeUpdated(int seconds) {
	    try {
	      Thread.sleep(seconds * 1000);
	    } catch (InterruptedException ie) {
	      log.error("InterruptedException occurred in waitForBackEndToBeUpdated " + ie);
	    }
	  }
	  
}