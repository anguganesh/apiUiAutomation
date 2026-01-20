package com.api.automation.steps;

import com.ui.api.automation.config.datapath.ApiData;
import com.ui.api.automation.config.datapath.ApiEndPointDetails;
import com.ui.api.automation.config.datapath.ApiJsonFilePath;
import com.ui.api.automation.config.datapath.ApiYamlFilePath;
import com.ui.automation.helpers.ApiCommonFunctions;
import com.ui.automation.helpers.JsonHelper;
import com.ui.automation.helpers.YamlHelper;
import io.restassured.response.Response;

public class BaseApiStepDef {	
	
	public final ApiCommonFunctions apiCommonFunctions;
	public final YamlHelper yamlHelper;
	public final JsonHelper jsonHelper;
	public ApiJsonFilePath apiJsonFilePath;
	public ApiYamlFilePath apiYamlFilePath;
	public ApiEndPointDetails apiEndPointDetails;
	public ApiData apiData;
	public static Response response;
	
	public BaseApiStepDef(ApiCommonFunctions apiCommonFunctions,
						  YamlHelper yamlHelper,
						  JsonHelper jsonHelper) {
		// TODO Auto-generated constructor stub
		this.apiCommonFunctions = apiCommonFunctions;
		this.yamlHelper = yamlHelper;
		this.jsonHelper = jsonHelper;
		this.apiJsonFilePath = this.apiCommonFunctions.getApiJsonFilePathObject();
		this.apiYamlFilePath = this.apiCommonFunctions.getApiYamlFilePathObject();
		this.apiEndPointDetails = this.apiCommonFunctions.getApiEndPointDetailsObject();
		this.apiData = this.apiCommonFunctions.getApiDataObject();
	}


}
