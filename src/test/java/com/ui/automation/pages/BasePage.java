package com.ui.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.ui.api.automation.configuration.Hooks;
import com.ui.automation.helpers.CommonFunctions;
import com.ui.automation.helpers.VisibilityHelper;

public class BasePage {

	public WebDriver driver;
	public final VisibilityHelper visibilityHelper;
	public final CommonFunctions commonFunctions;
	@SuppressWarnings("unused")
	private final Hooks hooks;
		
	public BasePage(Hooks hooks,
			        CommonFunctions commonFunctions,
			        VisibilityHelper visibilityHelper) {
		// TODO Auto-generated constructor stub
		this.hooks = hooks;
		this.commonFunctions = commonFunctions;
		this.visibilityHelper = visibilityHelper;
		this.driver = hooks.getDriver();
		PageFactory.initElements(this.driver, this);
	}
	
}
