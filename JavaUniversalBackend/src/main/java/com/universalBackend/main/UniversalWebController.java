package com.universalBackend.main;


import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.json.simple.JSONArray;


@RestController
public class UniversalWebController {

	@RequestMapping(value = "/insert", method = RequestMethod.POST , produces = "application/json")
	@ResponseBody
	public String insertdata( @RequestBody String body) {

		JSONObject clientRequest = ParseRequest.stringToJsonObject(body);
		String collection = clientRequest.get("collection").toString();
		JSONObject data = (JSONObject) clientRequest.get("data");

		JSONObject retval= MongoDBHandler.getHandler().insert(data, collection);
		return retval.toJSONString();
    }
	
	
	@RequestMapping(value = "/get", method = RequestMethod.POST , produces = "application/json")
	@ResponseBody
	public String getdata( @RequestBody String body) {

		JSONObject clientRequest = ParseRequest.stringToJsonObject(body);
		String collection = clientRequest.get("collection").toString();
		JSONObject data = (JSONObject) clientRequest.get("data");

		JSONArray retval= MongoDBHandler.getHandler().get(data, collection);
		return retval.toJSONString();
    }
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST , produces = "application/json")
	@ResponseBody
	public String updatedata( @RequestBody String body) {

		JSONObject clientRequest = ParseRequest.stringToJsonObject(body);
		String collection = clientRequest.get("collection").toString();
		JSONObject data = (JSONObject) clientRequest.get("data");

		JSONObject retval= MongoDBHandler.getHandler().update(data, collection);
		return retval.toJSONString();
    }
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST , produces = "application/json")
	@ResponseBody
	public String deletedata( @RequestBody String body) {

		JSONObject clientRequest = ParseRequest.stringToJsonObject(body);
		String collection = clientRequest.get("collection").toString();
		JSONObject data = (JSONObject) clientRequest.get("data");

		JSONObject retval= MongoDBHandler.getHandler().delete(data, collection);
		return retval.toJSONString();
    }
}