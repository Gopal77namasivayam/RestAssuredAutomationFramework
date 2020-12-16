package com.qa.api.gorest.Tests;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.RestClient.Restclient;
import com.qa.api.gorest.Util.Token;

import io.restassured.response.Response;

 

public class GetImgurAPITest {
	public static Map<Object, Object> tokenMap1;
	public static String accessToken;
	public static String accountUserName;
	public static String refreshToken;
	public static String baseURI="https://api.imgur.com";
	public static Map <String, String> authToken;
	
	@BeforeMethod	
	public void setUp()
	{
		tokenMap1=Token.getAccessToken();
		accessToken=tokenMap1.get("access_token").toString();
		accountUserName=tokenMap1.get("account_username").toString();
		refreshToken=tokenMap1.get("refresh_token").toString();
	}
	
	
	@Test
	public void getAccountBloclStatus()
	{
		Map<String,String> mapAcceessToken=new HashMap<String,String>();
		 authToken = Token.getAuthToken();
		Response response=Restclient.doGet(baseURI, null, authToken, null, "/3/account/namasivayam60", true);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
	}
	
	@Test
	public void getAccountImageStatus()
	{
		Map<String,String> mapAcceessToken=new HashMap<String,String>();
		 authToken = Token.getAuthToken();
		Response response=Restclient.doGet(baseURI, null, authToken, null, "/3/account/me/images", true);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
	}
	
	@Test
	public void uplaodImagePostApiTest()
	{
		Map<String, String> clientIdMap = Token.getClientId();
		Map<String,String>ImageUploadFormMap=new HashMap<String,String>();
		
		ImageUploadFormMap.put("title", "energyHero");
		ImageUploadFormMap.put("description", "des of the image");
		
		Response response=Restclient.doPost(baseURI, "multipart", clientIdMap, null,"/3/image", true, ImageUploadFormMap);
		//Restclient.doPost(baseURI, contentType, Token, paramMaps, basePath, log, obj)
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
	}
	
	

}
