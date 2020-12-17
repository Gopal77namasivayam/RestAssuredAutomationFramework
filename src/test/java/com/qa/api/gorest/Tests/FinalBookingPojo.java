package com.qa.api.gorest.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.gorest.Pojo.BasicBookingdetails;
import com.qa.api.gorest.Pojo.BookingDates;
import com.qa.api.gorest.RestClient.Restclient;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FinalBookingPojo {
	
	String baseURI="https://restful-booker.herokuapp.com";
	String basePath="/booking/";
	@Test
	public void createBooking()
	{
	BookingDates bookingdates = new BookingDates("2020-12-19","2020-12-28");
	BasicBookingdetails finalBooking= new BasicBookingdetails("Gopal100", "siva", 15000, true,"Lunch",bookingdates);
	Response response=Restclient.doPost(baseURI, "JSON", null, null, basePath, true, finalBooking);
	JsonPath jsonPath = response.jsonPath();
	System.out.println(response.statusCode());
	System.out.println(response.prettyPrint());
	Object bookinID =jsonPath.get("bookingid");
	Object nameinpost =jsonPath.get("booking.firstname");
	System.out.println("Generated Booking is "+bookinID);
	Response getResponse=Restclient.doGet(baseURI, "JSON", null, null, "/booking/"+bookinID, true);
	System.out.println("get booking json response is"+getResponse.prettyPrint());
	JsonPath getBookingJson = getResponse.jsonPath();
	Object BookingIdinGet=getBookingJson.get("bookingid");
	Object nameinGet=getBookingJson.get("firstname");
	
	Assert.assertEquals(nameinGet, nameinpost);
	
	
	
	

}
}