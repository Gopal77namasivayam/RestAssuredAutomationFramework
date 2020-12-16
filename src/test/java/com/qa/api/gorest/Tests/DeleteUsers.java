package com.qa.api.gorest.Tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.gorest.Pojo.UserFromPojo;
import com.qa.api.gorest.RestClient.Restclient;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class DeleteUsers {
	
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String Token = "a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038";
	
	
	@Test
	public void deleteUserTest()
	{
		Map<String,String> tokenMap= new HashMap<String, String>();
		tokenMap.put("Authorization", "Bearer "+ Token);
		UserFromPojo user=new UserFromPojo("Aash Gopal","ashasuccess4@gmail.com","Female","Active");
		Response response=Restclient.doPost(baseURI, "JSON", tokenMap, null, basePath, true, user);
		System.out.println(response.prettyPrint());
		System.out.println("Status code received is"+response.statusCode());
		JsonPath userJson=response.jsonPath();
		Object userid=userJson.get("data.id");
		System.out.println("user id is"+userid);
		 
		Response Updatedresponse=Restclient.doDelete(baseURI, "JSON", tokenMap, null, "/public-api/users/"+ userid, true, user);
		JsonPath UpdateduserJson=Updatedresponse.jsonPath();
		System.out.println(UpdateduserJson.prettyPrint());
		Object datavalue=UpdateduserJson.get("data");
		System.out.println("data value is"+datavalue);
		Assert.assertEquals(datavalue, null);
		
		 
	}

}
