package com.ui.automation.steps;

import java.io.IOException;

import org.testng.Assert;

import com.ui.api.automation.configuration.Hooks;
import com.ui.automation.helpers.JsonHelper;
import com.ui.automation.helpers.YamlHelper;
import com.ui.automation.model.LoginDataModel;
import com.ui.automation.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginStepDef extends BaseStepDefn {

	private final LoginPage loginPage;
	private final YamlHelper yamlHelper;
	private final JsonHelper jsonHelper;
	private LoginDataModel inputLoginData;

	public LoginStepDef(Hooks hooks, YamlHelper yamlHelper, JsonHelper jsonHelper, LoginPage loginPage) { 
		super(hooks);
		this.yamlHelper = yamlHelper;
		this.jsonHelper = jsonHelper;
		this.loginPage = loginPage;
	}

	@Given("User has to login first")
	public void user_has_to_login_first() {
		setLoginPagePojo();
		//setLoginPagePojoUsingJson();
		this.loginPage.login(this.inputLoginData);
		System.out.println("user has logged In successfully");
	}

	@When("User navigate to {string}")
	public void user_navigate_to(String url) {
		super.driver.get(url);
		System.out.println("User navigated to " + url);
	}
	
	@When("User navigates to {string}")
	public void user_navigates_to(String url) {
		super.driver.get(url);
		System.out.println("User navigated to " + url);
		Assert.assertTrue(false);
	}

	public LoginDataModel setLoginPagePojo() {
		String loginFilePath = super.uiYamlFilePath.getLoginYamlFilePath();
		try {
			this.inputLoginData =  this.yamlHelper.readYamlToPojo(LoginDataModel.class, loginFilePath);
			System.out.println("Login File Path : " + loginFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.inputLoginData;
	}
	
	
	public LoginDataModel setLoginPagePojoUsingJson() {
		String loginFilePath = super.uiJsonFilePath.getLoginJsonFilePath();
		System.out.println("Login File Path : " + loginFilePath);
		try {
			this.inputLoginData =  this.jsonHelper.readJsonToPojo(LoginDataModel.class, loginFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.inputLoginData;
	}
}
