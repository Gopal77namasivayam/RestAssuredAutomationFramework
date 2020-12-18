package com.qa.api.gorest.Tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.gorest.Pojo.UserFromPojo;
import com.qa.api.gorest.RestClient.Restclient;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@Epic("222....This suit will examine all kind of Post calls in GoRestAPI....") 
@Feature("*** POST API-GoRest***")
public class UpdateUsersTest {
	
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String Token = "a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038";
	
	@Description("-- create  users API call---" )
	@Severity(SeverityLevel.BLOCKER)
	@Step("Post call with {0},{1},{2},{3}")
	@Test
	public void updateUserTest()
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
		user.setName("Asha Ayyappan");
		Response Updatedresponse=Restclient.doPut(baseURI, "JSON", tokenMap, null, "/public-api/users/"+ userid, true, user);
		JsonPath UpdateduserJson=Updatedresponse.jsonPath();
		System.out.println(UpdateduserJson.prettyPrint());
		Object updatedUsername=UpdateduserJson.get("data.name");
		System.out.println("Updated user name is"+updatedUsername);
		Assert.assertEquals(updatedUsername, user.getName());
		
		 
	}

}
