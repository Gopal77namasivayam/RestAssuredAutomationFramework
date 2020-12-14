package com.qa.api.gorest.RestClient;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Restclient {

	public static Response doGet(String baseURI, String contentType, String Token, Map<String, String> paramMaps,
			String basePath, boolean log) {
		setBaseURI(baseURI);
		RequestSpecification request = createRequest(log, contentType, Token, paramMaps);
		 return getResponse("GET", request, basePath);
	}

	public static void setBaseURI(String baseURI) {
		RestAssured.baseURI = baseURI;
	}

	public static RequestSpecification createRequest(boolean log, String contentType, String Token, Map<String, String> paramMaps) {
		RequestSpecification request;
		if (log) {
			request = RestAssured.given().log().all();
		} else {
			request = RestAssured.given();
		}
		
		if(contentType.equalsIgnoreCase("JOSN"))
		{
			request.contentType(ContentType.JSON);
		}else if(contentType.equalsIgnoreCase("XML"))
		{
			request.contentType(ContentType.XML);
		}else if(contentType.equalsIgnoreCase("TEXT"))
		{
			request.contentType(ContentType.TEXT);
		}
		
		if(Token!=null)
		{
			request.header("Authorization","Bearer "  +Token);
		}
		
		if(!(paramMaps==null))
		{
			request.queryParams(paramMaps);
		}
		
		return request;
	
	}
	
	public static Response getResponse(String httpMethod,RequestSpecification request,String basePath)
	{
		 return executeAPI(httpMethod, request, basePath);
	}
	
	public static Response executeAPI(String httpMethod,RequestSpecification request,String basePath)
	{
		Response response=null;
		switch (httpMethod) {
		case "GET":
			response=request.get(basePath);
			break;
		case "POST":
			response=request.post(basePath);
			break;
		case "PUT":
			response=request.put(basePath);
			break;
		case "DELETE":
			response=request.delete(basePath);
			break;

		default:
			System.out.println("Please pass the valid http method");
			break;
		}
		
		return response;
	}

}

//String baseURI,String contentType,String Token,Map<String,String> paramMaps,String basePath,boolean log