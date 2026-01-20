package com.api.automation.steps;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.api.automation.model.GetRequestUserDetails;
import com.api.automation.model.GetResponseUserDetails;
import com.ui.api.automation.common.enums.ApiEnumerations;
import com.ui.automation.helpers.ApiCommonFunctions;
import com.ui.automation.helpers.JsonHelper;
import com.ui.automation.helpers.YamlHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostNewDataStepDef extends BaseApiStepDef {

	private GetRequestUserDetails getRequestUserDetails;
	private GetResponseUserDetails getResponseUserDetails;
	
	
	public PostNewDataStepDef(ApiCommonFunctions apiCommonFunctions, YamlHelper yamlHelper, JsonHelper jsonHelper) {
		// TODO Auto-generated constructor stub
		super(apiCommonFunctions, yamlHelper, jsonHelper);		
	}
	
	@Given("User post user details using {string}")
	public void postUserDetails(String scenario) {
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(ApiEnumerations.AUTHORIZATION.getValue(), ApiEnumerations.BEARER.getValue() + 
				                                              ApiEnumerations.SPACE.getValue() +
				                                              super.apiData.getGoRestAuthorizationValue());
		try {
			this.getRequestUserDetails = super.yamlHelper.readYamlToPojoList(GetRequestUserDetails.class, super.apiYamlFilePath.getUserDataForPostRequestFilePath())
					                 .stream().filter(eachRequestUserDetails -> eachRequestUserDetails.getScenario().equals(scenario)).findFirst().get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   BaseApiStepDef.response = super.apiCommonFunctions.postCall(headers, super.apiEndPointDetails.getGoRestPostEndPoint(), getRequestUserDetails, ContentType.JSON);
	}
	
	@Then("Verify reponse data for post call")
	public void verifyReponseData() {
	    
	   getResponseUserDetails =	BaseApiStepDef.response.as(GetResponseUserDetails.class);
	   log.info("Response Data : {}", getResponseUserDetails.toString());
	}
	



}
