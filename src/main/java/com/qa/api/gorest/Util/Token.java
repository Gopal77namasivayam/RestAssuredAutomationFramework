package com.qa.api.gorest.Util;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.github.fge.jsonschema.main.cli.Main;

import io.restassured.path.json.JsonPath;

public class Token {
	public static Map<Object, Object> apptokenMap;
	public static String AuthToken;
	public static Map<String,String> tokenMap=new HashMap<String,String>();
	public static  String client_id="607d6c14258b364";
	public static Map<Object, Object> getAccessToken()
	
	
	{
	Map<String,String> formParam1=new HashMap<String,String>();
	formParam1.put("refresh_token","d0f16a13742a86602341ed5da6ca3eae15d97269");
	formParam1.put("grant_type","refresh_token");
	formParam1.put("client_id","607d6c14258b364");
	formParam1.put("client_secret","94d10c15aab2359b01c4c18d4438b3d5efa71f0e");
	
	
	JsonPath tokenjson=given()
	.formParams(formParam1)
	.when()
	.post("https://api.imgur.com/oauth2/token")
	.then()
	.extract().jsonPath();
	System.out.println(tokenjson.getMap(""));
	 apptokenMap = tokenjson.getMap("");
	  return apptokenMap;
	
	}
	
	
	public static Map<String, String> getAuthToken() {
		 
		 AuthToken = apptokenMap.get("access_token").toString();
		 System.out.println("Your Auth token is"+AuthToken);
		 tokenMap.put("Authorization", "Bearer "+ AuthToken);
		 return tokenMap;
	}
	
	public static Map<String, String> getClientId() {
		 
		 
		 System.out.println("Your client id is"+client_id);
		 tokenMap.put("Authorization", "Client-ID " + client_id);
		 return tokenMap;
	}

}
