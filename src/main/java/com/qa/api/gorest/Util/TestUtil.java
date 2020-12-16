package com.qa.api.gorest.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {
	/**
	 * This method used to convert POJO into Json
	 * @param obj
	 * @return jsonString
	 */
	
	public static String getSerializedJson(Object obj)
	{
		
		ObjectMapper mapper=new ObjectMapper();
		String jsonString=null;
		try
		{
		 jsonString=mapper.writeValueAsString(obj);
		 System.out.println("Json Body PayLoad: " +jsonString);
		}catch(JsonProcessingException e)
		{
			e.printStackTrace();
		}
		 return jsonString;
		 
	}

}
