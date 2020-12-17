package com.qa.api.gorest.Tests;

import org.testng.annotations.Test;

import com.qa.api.gorest.Pojo.CreateBookerToken;
import com.qa.api.gorest.RestClient.Restclient;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateHerokuappBookerToken {
	String baseURI="https://restful-booker.herokuapp.com";
	String basePath="/auth";
	@Test
	public void getBookerToken()
	{
		CreateBookerToken credentials=new CreateBookerToken("admin","password123");
		Response response=Restclient.doPost(baseURI, "JSON", null, null, basePath, true, credentials);
		JsonPath tokenJosn = response.jsonPath();
		System.out.println("token response is:" + tokenJosn.prettyPrint());
		Object tokenVal=tokenJosn.get("token");
		System.out.println("token value is: "+ tokenVal);
	}

}
