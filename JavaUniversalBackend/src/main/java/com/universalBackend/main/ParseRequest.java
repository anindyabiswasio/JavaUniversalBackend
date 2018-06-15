package com.universalBackend.main;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParseRequest {
	
	@SuppressWarnings("unchecked")
	public static JSONObject stringToJsonObject(String body)
	{
		JSONObject bodyData;
		try {
		bodyData = (JSONObject) (new JSONParser().parse(body));
		}catch (ParseException e) {
			bodyData = new JSONObject();
			bodyData.put("error", "Error 201: Input not valid JSON");
		}
		return bodyData;
	}
}
