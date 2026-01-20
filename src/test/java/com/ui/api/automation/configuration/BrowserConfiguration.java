package com.ui.api.automation.configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.apache.hc.core5.http.ContentType;
import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.ui.api.automation.config.datapath.ApiData;
import com.ui.api.automation.config.datapath.ApiEndPointDetails;
import com.ui.api.automation.config.datapath.ApiJsonFilePath;
import com.ui.api.automation.config.datapath.ApiYamlFilePath;
import com.ui.api.automation.config.datapath.UiJsonFilePath;
import com.ui.api.automation.config.datapath.UiYamlFilePath;
import com.ui.automation.helpers.ApiCommonFunctions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

public class BrowserConfiguration {

	public final Hooks hooks;
	private final ApiCommonFunctions apiCommonFunctions;
	private final ReportsConfiguration reportConfiguration;
	private UiYamlFilePath uiYamlFilePath;
	private UiJsonFilePath uiJsonFilePath;
	private ApiYamlFilePath apiYamlFilePath;
	private ApiJsonFilePath apiJsonFilePath;
	private ApiEndPointDetails apiEndPointDetails;
	private ApiData apiData;

	public BrowserConfiguration(Hooks hooks, ApiCommonFunctions apiCommonFunctions,
			ReportsConfiguration allureReportConfiguration) {
		// TODO Auto-generated constructor stub
		this.hooks = hooks;
		this.apiCommonFunctions = apiCommonFunctions;
		this.reportConfiguration = allureReportConfiguration;
	}

	@Before(order = 0)
	public void configureFilePathLocation() {
		try (ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ObjectConfig.class)) {
			this.uiYamlFilePath = context.getBean(UiYamlFilePath.class);
			this.uiJsonFilePath = context.getBean(UiJsonFilePath.class);
			this.apiYamlFilePath = context.getBean(ApiYamlFilePath.class);
			this.apiJsonFilePath = context.getBean(ApiJsonFilePath.class);
			this.apiEndPointDetails = context.getBean(ApiEndPointDetails.class);
			this.apiData = context.getBean(ApiData.class);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hooks.setFilePathLocation(this.uiYamlFilePath, this.uiJsonFilePath);

		apiCommonFunctions.setApiDetails(this.apiYamlFilePath, this.apiJsonFilePath, this.apiEndPointDetails,
				this.apiData);
	}

	@Before(order = 1)
	public void openBrowser(Scenario scenario) {

		if (scenario.getSourceTagNames().toString().contains("@UI")) {
			System.out.println("Thread : " + Thread.currentThread().threadId() + " : " + scenario.getName());
			System.out.println("Driver Address Initial : " + hooks.getDriver());
			hooks.launchBrowser();
			System.out.println("Driver Address after creating : " + hooks.getDriver());

		}
	}

	@After()
	public void closeBrowser(Scenario scenario) throws IOException {
		System.out.println("Executing CloseBrowser Method");
		if (scenario.getSourceTagNames().toString().contains("@UI")) {
			if (scenario.isFailed()) {
				hooks.copyScreenshotAsFile(scenario);
				scenario.attach(hooks.getScreenshotAsBytes(), ContentType.IMAGE_PNG.toString(), scenario.getName());
				ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, MediaEntityBuilder
				 .createScreenCaptureFromBase64String(hooks.getBase64StringOfScreenshot()).build());
				Allure.addAttachment("Allure Report Screenshot for test failure for scenario: " +
				 scenario.getName(),  new ByteArrayInputStream(reportConfiguration.takesScreenshotForAllureReport()));
			}

			hooks.closeBrowser();
		}

	}

}