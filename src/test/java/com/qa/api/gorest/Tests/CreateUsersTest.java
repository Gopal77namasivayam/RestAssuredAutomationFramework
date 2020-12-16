package com.qa.api.gorest.Tests;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.Pojo.UserFromPojo;
import com.qa.api.gorest.RestClient.Restclient;
import com.qa.api.gorest.Util.ExcelUtil;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateUsersTest {
	/*
	 * String baseURI, String contentType, String Token, Map<String, String> paramMaps,
			String basePath, boolean log ,Object obj)
	 */

	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String Token = "a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038";
	
	@DataProvider
	public Object[][] getUserData()
	{
		Object[][] userdata=ExcelUtil.getTestData("userdata");
		return userdata;
	}
	
	@Test(dataProvider="getUserData")
	public  void createUSersPostAPiTest(String name,String email,String gender,String status)
	{
		UserFromPojo user=new UserFromPojo(name,email,gender,status);
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+Token);
		Response response=Restclient.doPost(baseURI, "JSON", authTokenMap, null, basePath, true, user);
 
		
		//JsonPath js=response.jsonPath();
		
		
		System.out.println("ContentType:  "+response.getContentType());
		System.out.println("statuscode:	"+response.getStatusCode());
		System.out.println("Header:	"+response.header("Server"));
		System.out.println("Cookies:	"+response.cookie(""));
		System.out.println("pretty print :	"+response.prettyPrint());
//		
//		System.out.println("meta info are: "+js.getString("meta"));
//		System.out.println("Pages: "+js.getString("meta.pagination"));
//		System.out.println("Page: "+js.getString("meta.pagination.page"));
//		System.out.println("Total number of id: "+js.getString("meta.pagination.total"));
//		System.out.println("limit is pages are: "+js.getString("meta.pagination.limit"));
		
		
//		System.out.println("id is" +js.get("data.id"));
//		System.out.println("name is"+js.get("data.name"));
//		System.out.println("email is"+js.get("data.email"));
//		System.out.println("gender is"+js.get("data.gender"));
//		System.out.println("status"+js.get("data.status"));
		
		
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.header("Server"), "nginx");
//		Assert.assertEquals(js.get("data.id"), equalTo());
//		Assert.assertEquals(js.get("data.name"),equalTo(user.getName()));
//		Assert.assertEquals(js.get("data.email"), equalTo(user.getEmail()));
//		Assert.assertEquals(js.get("data.gender"), equalTo(user.getGender()));
//		Assert.assertEquals(js.get("data.status"), equalTo(user.getStatus()));
		
		
	}

}
