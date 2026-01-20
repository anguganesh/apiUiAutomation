package com.ui.automation.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.ui.api.automation.configuration.Hooks;
import com.ui.automation.model.LoginDataModel;
import com.ui.automation.helpers.CommonFunctions;
import com.ui.automation.helpers.VisibilityHelper;




public class LoginPage extends BasePage {

   
	
	public LoginPage(Hooks hooks,
			         CommonFunctions commonFunctions,
			         VisibilityHelper visibilityHelper) {
		// TODO Auto-generated constructor stub
		super(hooks, commonFunctions, visibilityHelper);
		PageFactory.initElements(super.driver, this);
	}
	
	@FindBy(how = How.ID, using="")
	private WebElement username;
	@FindBy(how = How.ID, using="")
	private WebElement password;
	
	@FindBy(how = How.CSS, using="testing")
	private WebElement newWebElement;
	
	
	public void login(LoginDataModel inputLoginData) {
		System.out.println("Input Login Data : " + inputLoginData);
		System.out.println(super.driver.getTitle());
	}

	
	public void clickElement() {
		this.newWebElement.click();
	}
		
}
