package com.api.automation.steps;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.testng.Assert;
import com.api.automation.model.GetResponseUserDetails;
import com.ui.api.automation.common.enums.ApiEnumerations;
import com.ui.automation.helpers.ApiCommonFunctions;
import com.ui.automation.helpers.JsonHelper;
import com.ui.automation.helpers.YamlHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.common.mapper.TypeRef;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetAllUserDetailsStepDef extends BaseApiStepDef {

	private List<GetResponseUserDetails> listOfGetResponseUserDetailsFromFile;
	private Integer resultCode;

	public GetAllUserDetailsStepDef(ApiCommonFunctions apiCommonFunctions, YamlHelper yamlHelper,
			JsonHelper jsonHelper) {
		// TODO Auto-generated constructor stub
		super(apiCommonFunctions, yamlHelper, jsonHelper);
	}

	@Given("User get All User details")
	public void getAllUserDetails() {

		Map<String, String> headers = new HashMap<String, String>();
		String authorizationValue = ApiEnumerations.BEARER.getValue() + ApiEnumerations.SPACE.getValue()
				+ super.apiData.getGoRestAuthorizationValue();
		headers.put(ApiEnumerations.AUTHORIZATION.getValue(), authorizationValue);

		Map<String, String> queryParams = new HashMap<String, String>();

		BaseApiStepDef.response = super.apiCommonFunctions.getCall(headers, queryParams,
				super.apiEndPointDetails.getGoRestGetAllUserDetailsEndPoint());
	}

	@Then("Verify response data for all userDetails endpoint")
	public void verifyResponseDataForAllUserDetails() {

		List<GetResponseUserDetails> listOfGetResponseUserDetailsFromApi = BaseApiStepDef.response.getBody()
				.as(new TypeRef<List<GetResponseUserDetails>>() {
				});
		try {
			this.listOfGetResponseUserDetailsFromFile = super.yamlHelper.readYamlToPojoList(
					GetResponseUserDetails.class, super.apiYamlFilePath.getAllUserDetailsResponseFilePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Integer> integerListFromApi = listOfGetResponseUserDetailsFromApi.stream()
				.map(eachResponseApiData -> eachResponseApiData.getId()).collect(Collectors.toList());

		for (Integer eachInteger : integerListFromApi) {
			resultCode = listOfGetResponseUserDetailsFromApi.stream()
					.filter(eachResponseApiData -> eachResponseApiData.getId().equals(eachInteger)).findFirst().get()
					.compareTo(listOfGetResponseUserDetailsFromFile.stream()
							.filter(eachResponseApiData -> eachResponseApiData.getId().equals(eachInteger)).findFirst()
							.get());

			Assert.assertEquals("NOT able to validate AllResponseData", "0", String.valueOf(resultCode));
		}
	}
}
