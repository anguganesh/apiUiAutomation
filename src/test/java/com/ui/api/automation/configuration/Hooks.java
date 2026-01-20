package com.ui.api.automation.configuration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.ui.api.automation.config.datapath.UiJsonFilePath;
import com.ui.api.automation.config.datapath.UiYamlFilePath;
import com.ui.automation.helpers.YamlHelper;
import com.ui.automation.model.BrowserDetailsModel;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hooks {

	private WebDriver driver;
	public BrowserDetailsModel browserDetails;
	public final YamlHelper yamlHelper;
	private UiYamlFilePath uiYamlFilePath;
	private UiJsonFilePath uiJsonFilePath;
	
	public Hooks(YamlHelper yamlHelper) {
		// TODO Auto-generated constructor stub
		this.yamlHelper = yamlHelper;
	}

	public void setFilePathLocation(UiYamlFilePath uiYamlFilePathObject, UiJsonFilePath uiJsonFilePathObject) {
		this.uiYamlFilePath = uiYamlFilePathObject;
		this.uiJsonFilePath = uiJsonFilePathObject;
	}

	public WebDriver launchBrowser() {

		if (this.driver == null)
			initializeDriver();
		return driver;
	}

	public void closeBrowser() {
		System.out.println("Before closing Browser : " + this.driver);
		if (driver != null) {
			driver.close();
			driver.quit();
			//driver = null;
		}
	}

	public WebDriverWait getWait() {
		WebDriverWait wait = new WebDriverWait(this.getDriver(), Duration.ofSeconds(this.browserDetails.getExplicitWait()));
		return wait;
	}

	private void initializeDriver() {
		this.getBrowserDetails();
		if ("chrome".equals(browserDetails.getBrowserName())) {
			setChromeDriver();
		} else if ("firefox".equals(browserDetails.getBrowserName())) {
			setFirefoxDriver();
		}
	}

	@SuppressWarnings("deprecation")
	public void setChromeDriver() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("enable-automation");
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.addArguments("--disable-extensions"); // disabling extensions
		chromeOptions.addArguments("--disable-gpu"); // applicable to windows OS only
		chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model
		chromeOptions.addArguments("--dns-prefetch-disable");
		chromeOptions.addArguments("--remote-allow-origins=*");

		if (browserDetails.getHeadless()) {
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--disable-gpu");
			chromeOptions.addArguments("window-size=1920,1080");
		}

		WebDriverManager.chromedriver().setup();

		if (browserDetails.getRemote())
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
			} catch (MalformedURLException malEx) {
				log.debug("Getting WebDriver exception : {}", malEx.getMessage());
			}
		else
			driver = new ChromeDriver(chromeOptions);

		driver.get(browserDetails.getBaseUrl());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(this.browserDetails.getImplicitWait()));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(browserDetails.getPageLoadTimeOut()));
	}

	public void setFirefoxDriver() {
		WebDriverManager.firefoxdriver().setup();
	}

	public BrowserDetailsModel getBrowserDetails() {
		try {
			browserDetails = yamlHelper.readYamlToPojo(BrowserDetailsModel.class,
					this.uiYamlFilePath.getBrowserYamlFilePath());
			log.info(browserDetails.toString());
		} catch (IOException IoEx) {
			log.debug("Getting POJO Exception :{}", IoEx.getMessage());
		}
		return browserDetails;
	}

	public byte[] getScreenshotAsBytes() {
		return ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES);
	}

	public void copyScreenshotAsFile(Scenario scenario) {
		TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
		String screenshotFilePath = "./target/Screenshots/" + scenario.getName() + ".PNG";
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(screenshotFilePath);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getBase64StringOfScreenshot() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public UiYamlFilePath getYamlFilePathObject() {
		return this.uiYamlFilePath;
	}

	public UiJsonFilePath getJsonFilePathObject() {
		return this.uiJsonFilePath;
	}
} 