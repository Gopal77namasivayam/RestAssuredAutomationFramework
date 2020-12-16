package com.qa.api.gorest.RestClient;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.Map;

import com.qa.api.gorest.Util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Restclient {
	// HTTP methods GET,POST,PUT and DELETE
	/**
	 * This method used to call getAPI
	 * 
	 * @param baseURI
	 * @param contentType
	 * @param Token
	 * @param paramMaps
	 * @param basePath
	 * @param log
	 * @return this method returning response from the get call
	 */

	public static Response doGet(String baseURI, String contentType, Map<String, String> Token,
			Map<String, String> paramMaps, String basePath, boolean log) {
		if (setBaseURI(baseURI))

		{
			RequestSpecification request = createRequest(log, contentType, Token, paramMaps);
			return getResponse("GET", request, basePath);
		}
		return null;
	}

	/**
	 * 
	 * @param baseURI
	 * @param contentType
	 * @param Token
	 * @param paramMaps
	 * @param basePath
	 * @param log
	 * @param obj         is super class for all object
	 * @return This method returns the response from the post call
	 */
	public static Response doPost(String baseURI, String contentType, Map<String, String> Token,
			Map<String, String> paramMaps, String basePath, boolean log, Object obj) {
		
		if (setBaseURI(baseURI))

		{
			RequestSpecification request = createRequest(log, contentType, Token, paramMaps);
			addRequestPayLoad(request, obj);
			return getResponse("POST", request, basePath);
		}
		return null;
	}
	
	public static Response doPut(String baseURI, String contentType, Map<String, String> Token,
			Map<String, String> paramMaps, String basePath, boolean log, Object obj) {
		
		if (setBaseURI(baseURI))

		{
			RequestSpecification request = createRequest(log, contentType, Token, paramMaps);
			addRequestPayLoad(request, obj);
			return getResponse("PUT", request, basePath);
		}
		return null;
	}
	
	
	public static Response doDelete(String baseURI, String contentType, Map<String, String> Token,
			Map<String, String> paramMaps, String basePath, boolean log, Object obj) {
		
		if (setBaseURI(baseURI))

		{
			RequestSpecification request = createRequest(log, contentType, Token, paramMaps);
			addRequestPayLoad(request, obj);
			return getResponse("DELETE", request, basePath);
		}
		return null;
	}

	public static void addRequestPayLoad(RequestSpecification request, Object obj) {
		if (obj instanceof Map) {
			request.formParams((Map<String,String>)obj);
		} else {
			String jsonPayLoad = TestUtil.getSerializedJson(obj);
			request.body(jsonPayLoad);
		}
	}

	private static boolean setBaseURI(String baseURI) {
		if (baseURI == null || baseURI.isEmpty()) {
			System.out.println("Please eneer the valid baseURI");
			return false;
		}
		try {
			RestAssured.baseURI = baseURI;
			return true;
		} catch (Exception e) {
			System.out.println("some exception in while assigning the basic URI");
			return false;
		}
	}

	private static RequestSpecification createRequest(boolean log, String contentType, Map<String, String> Token,
			Map<String, String> paramMaps) {
		RequestSpecification request;
		if (log) {
			request = RestAssured.given().log().all();
		} else {
			request = RestAssured.given();
		}
		if (contentType != null) {
			if (contentType.equalsIgnoreCase("JSON")) {
				request.contentType(ContentType.JSON);
			} else if (contentType.equalsIgnoreCase("XML")) {
				request.contentType(ContentType.XML);
			} else if (contentType.equalsIgnoreCase("TEXT")) {
				request.contentType(ContentType.TEXT);
			} else if (contentType.equalsIgnoreCase("multipart")) {
				request.multiPart("image", new File("C:\\Users\\Namasivayam\\Desktop\\images\\gopal.jpeg"));
			}
		}

		if (Token.size() > 0) {
			// request.header("Authorization", "Bearer " + Token);
			request.headers(Token);
		}

		if (!(paramMaps == null)) {
			request.queryParams(paramMaps);
		}

		return request;

	}

	private static Response getResponse(String httpMethod, RequestSpecification request, String basePath) {
		return executeAPI(httpMethod, request, basePath);
	}

	private static Response executeAPI(String httpMethod, RequestSpecification request, String basePath) {
		Response response = null;
		switch (httpMethod) {
		case "GET":
			response = request.get(basePath);
			break;
		case "POST":
			response = request.post(basePath);
			break;
		case "PUT":
			response = request.put(basePath);
			break;
		case "DELETE":
			response = request.delete(basePath);
			break;

		default:
			System.out.println("Please pass the valid http method");
			break;
		}

		return response;
	}

}

//String baseURI,String contentType,String Token,Map<String,String> paramMaps,String basePath,boolean log