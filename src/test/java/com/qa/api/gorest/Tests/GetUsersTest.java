package com.qa.api.gorest.Tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.RestClient.Restclient;

import io.restassured.response.Response;

public class GetUsersTest {
	// String baseURI,String contentType,String Token,Map<String,String>
	// paramMaps,String basePath,boolean log

	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String Token = "a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038";

	@Test
	public void getAllUsersAPITest() {
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+Token);
		Response response = Restclient.doGet(baseURI, "JSON", authTokenMap, null, basePath, true);
		System.out.println("statuscode:	" + response.getStatusCode());
		System.out.println("pretty print :	" + response.prettyPrint());

	}

	@Test
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
