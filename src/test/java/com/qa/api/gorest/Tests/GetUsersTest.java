package com.qa.api.gorest.Tests;
//@Epic,@Feature,@Severity  @step are allure annotations
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.RestClient.Restclient;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
@Epic("111....This suit will examine all kind of GET calls in GoRestAPI....") 
@Feature("*** GET API-GoRest***")
public class GetUsersTest {
	// String baseURI,String contentType,String Token,Map<String,String>
	// paramMaps,String basePath,boolean log

	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String Token = "a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038";
	
	@Description("--Get all the  created users---" )
	@Test(priority=1)
	 	
	public void getAllUsersAPITest() {
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+Token);
		Response response = Restclient.doGet(baseURI, "JSON", authTokenMap, null, basePath, true);
		System.out.println("statuscode:	" + response.getStatusCode());
		System.out.println("pretty print :	" + response.prettyPrint());

	}
	@Description("--Get specific users---" )
	@Test(priority=2)
	@Severity(SeverityLevel.BLOCKER)	
	 
	public void getAllUsersWithQueryParamsTest() {
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+Token);
		Map<String, String> paramMaps = new HashMap<String, String>();
		paramMaps.put("gender", "Male");
		paramMaps.put("name", "asha");
		Response response = Restclient.doGet(baseURI, "JSON", authTokenMap, paramMaps, basePath, true);
		System.out.println("statuscode:	" + response.getStatusCode());
		System.out.println("pretty print :	" + response.prettyPrint());

	}

}
