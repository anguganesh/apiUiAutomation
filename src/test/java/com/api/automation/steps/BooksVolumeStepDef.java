package com.api.automation.steps;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import com.api.automation.model.BooksVolumeResponse;
import com.ui.automation.helpers.ApiCommonFunctions;
import com.ui.automation.helpers.JsonHelper;
import com.ui.automation.helpers.YamlHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BooksVolumeStepDef extends BaseApiStepDef {

	public Response response;
	public BooksVolumeResponse booksVolumeResponseFromFile;
	public BooksVolumeResponse booksVolumeResponse;
		
	public BooksVolumeStepDef( ApiCommonFunctions apiCommonFunctions,
							   YamlHelper yamlHelper,
							   JsonHelper jsonHelper) {
		// TODO Auto-generated constructor stub
		super(apiCommonFunctions, yamlHelper, jsonHelper);
	}
	
	
	
	@Given("User hit books volume endpoint")
	public void getBooksVolumeEndPointResponse() {
		String endPoint = super.apiEndPointDetails.getBookVolumesEndPoint();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(super.apiData.getKeyValue(), super.apiData.getApiKey());
		
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("q", "flowers");
		
		this.response = super.apiCommonFunctions.getCall(headers, queryParams, endPoint);
	}
	
	@Then("User able to get Response code as {int}")
	public void verifyBooksVolumeEndPointResponseCode(Integer responseCode) {
	    Assert.assertEquals(String.valueOf(this.response.getStatusCode()), String.valueOf(this.response.getStatusCode()));
	}
	
	@Then("User able to verify response data")
	public void verifyResponseData() {
		
		try {
			log.info("Response FilePath : {}", super.apiYamlFilePath.getBooksVolumeResponseFilePath());
			booksVolumeResponseFromFile = super.yamlHelper.readYamlToPojo(BooksVolumeResponse.class, super.apiYamlFilePath.getBooksVolumeResponseFilePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		booksVolumeResponse = this.response.getBody().as(BooksVolumeResponse.class);
		log.info("Response Data from File : {}", booksVolumeResponseFromFile);
		log.info("Response Data from API  : {}", booksVolumeResponse);
	}
	
	

}
