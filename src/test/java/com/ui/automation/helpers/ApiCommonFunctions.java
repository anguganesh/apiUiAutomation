package com.ui.automation.helpers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.hamcrest.Matchers;
import com.ui.api.automation.config.datapath.ApiData;
import com.ui.api.automation.config.datapath.ApiEndPointDetails;
import com.ui.api.automation.config.datapath.ApiJsonFilePath;
import com.ui.api.automation.config.datapath.ApiYamlFilePath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiCommonFunctions {

	private Response response;
	private ApiYamlFilePath apiYamlFilePath;
	private ApiJsonFilePath apiJsonFilePath;
	private ApiEndPointDetails apiEndPointDetails;
	private ApiData apiData;

	public void setApiDetails(ApiYamlFilePath apiYamlFilePath, ApiJsonFilePath apiJsonFilePath,
			ApiEndPointDetails apiEndPointDetails, ApiData apiData) {
		this.apiYamlFilePath = apiYamlFilePath;
		this.apiJsonFilePath = apiJsonFilePath;
		this.apiEndPointDetails = apiEndPointDetails;
		this.apiData = apiData;
	}

	public void attachResponse() {
		//Allure.addAttachment(this.response.asPrettyString(), ContentType.TEXT.toString(), "Response : ");
	}

	public void attachRequest(Object requestBody) {
		//Allure.addAttachment(requestBody.toString(), ContentType.TEXT.toString(), "Request : ");
	}

	public Response getCall(Map<String, String> headers, Map<String, String> queryParams, String endPoint) {
		URI uri = null;
		try {
			uri = new URI(endPoint);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.response = RestAssured.given().queryParams(queryParams).headers(headers).get(uri);
		attachResponse();
		this.response.then().log().all().assertThat()
				.time(Matchers.lessThan(Long.parseLong(this.apiData.getRestApiResponseTime())), TimeUnit.SECONDS)
				.extract().response();
		

		
		return this.response;

	}

	public <T> Response postCall(@NotNull Map<String, String> headers, @NotNull String endPoint, @NotNull T requestBody,
			@NotNull ContentType contentType) {

		URI uri = null;
		try {
			uri = new URI(endPoint);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.response = RestAssured.given().contentType(contentType).headers(headers).body(requestBody).post(uri);

		// attachRequest(requestBody);
		// attachResponse();

		this.response.then().log().all().assertThat()
				.time(Matchers.lessThan(Long.parseLong(apiData.getRestApiResponseTime())), TimeUnit.SECONDS).extract()
				.response();

		return response;

	}

	public ApiYamlFilePath getApiYamlFilePathObject() {
		return this.apiYamlFilePath;
	}

	public ApiJsonFilePath getApiJsonFilePathObject() {
		return this.apiJsonFilePath;
	}

	public ApiEndPointDetails getApiEndPointDetailsObject() {
		return this.apiEndPointDetails;
	}

	public ApiData getApiDataObject() {
		return this.apiData;
	}

}
