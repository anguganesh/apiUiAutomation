package com.api.automation.steps;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import com.api.automation.model.GetResponseUserDetails;
import com.ui.api.automation.common.enums.ApiEnumerations;
import com.ui.automation.helpers.ApiCommonFunctions;
import com.ui.automation.helpers.JsonHelper;
import com.ui.automation.helpers.YamlHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetUserDetailsStepDef extends BaseApiStepDef {
	
	public GetResponseUserDetails getResponseUserDetailsFromApi;
	public GetResponseUserDetails getResponseUserDetailsFromFile;

	public GetUserDetailsStepDef(ApiCommonFunctions apiCommonFunctions, YamlHelper yamlHelper, JsonHelper jsonHelper) {
		// TODO Auto-generated constructor stub
		super(apiCommonFunctions, yamlHelper, jsonHelper);
	}

	@Given("User get User details using {int}")
	public void getUserDetails(Integer userId) {
		String userDetailsGetEndpoint = super.apiEndPointDetails.getGoRestGetEndPoint()
				+ ApiEnumerations.FORWARD_SLASH.getValue() + userId;

		Map<String, String> headers = new HashMap<String, String>();
		String authorizationValue = ApiEnumerations.BEARER.getValue() + ApiEnumerations.SPACE.getValue()
				+ super.apiData.getGoRestAuthorizationValue();
		headers.put(ApiEnumerations.AUTHORIZATION.getValue(), authorizationValue);

		Map<String, String> queryParams = new HashMap<String, String>();
		BaseApiStepDef.response = super.apiCommonFunctions.getCall(headers, queryParams, userDetailsGetEndpoint);
	}

	@Then("Verify Response code as {int}")
	public void verifyResponseCode(Integer responseCode) {
		log.info("Response Code : {}", BaseApiStepDef.response.getStatusCode());
		Assert.assertEquals("Response code is NOT 200", String.valueOf(responseCode),
				String.valueOf(BaseApiStepDef.response.getStatusCode()));
	}

	@Then("Verify response data for userDetails endpoint")
	public void verifyResponseData() {

		getResponseUserDetailsFromApi = BaseApiStepDef.response.getBody().as(GetResponseUserDetails.class);
		try {
			getResponseUserDetailsFromFile = super.yamlHelper.readYamlToPojo(GetResponseUserDetails.class,
					super.apiYamlFilePath.getUserDetailsGetResponseYamlFilePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
				
		Assert.assertEquals("User Details data is NOT Equal", ApiEnumerations.ZERO_STRING.getValue(),
				String.valueOf(getResponseUserDetailsFromApi.compareTo(getResponseUserDetailsFromFile)));

	}

}
