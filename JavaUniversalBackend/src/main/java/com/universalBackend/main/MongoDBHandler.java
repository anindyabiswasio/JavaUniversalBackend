package com.universalBackend.main;


import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;




public class MongoDBHandler {
	private static MongoDBHandler self;
	private static MongoClient mongo=null;
	private MongoDBHandler() {}

	public static MongoDBHandler getHandler()
	{
		if(self==null) 
		{
			self= new MongoDBHandler();
			mongo = new MongoClient( MongoDBHandlerConfig.mongo_url , MongoDBHandlerConfig.mongo_port );
			//DB db = mongo.getDB("database name");
			//boolean auth = db.authenticate("username", "password".toCharArray());
		}
		return self;
	}

	public JSONArray get(JSONObject data, String collection)
	{
		System.out.println("GET Operation....");
		System.out.println("Collection : "+collection);
		System.out.println("Data : "+data);
		
		MongoDatabase mydatabase = mongo.getDatabase(MongoDBHandlerConfig.mongo_database);
		MongoCollection<Document> table = mydatabase.getCollection(collection);
		
		BasicDBObject searchQuery = new BasicDBObject();
		
		FindIterable<Document> cursor = table.find(searchQuery);
		JSONArray retval= new JSONArray();
		for(Document doc : cursor) {
			retval.add(ParseRequest.stringToJsonObject(doc.toJson()));
		}
		return retval;
	}
	
	public JSONObject insert(JSONObject data, String collection)
	{
		System.out.println("INSERT Operation....");
		System.out.println("Collection : "+collection);
		System.out.println("Data : "+data);
		
		MongoDatabase mydatabase = mongo.getDatabase(MongoDBHandlerConfig.mongo_database);
		MongoCollection<Document> table = mydatabase.getCollection(collection);
		
		Document document = Document.parse(data.toJSONString());
		table.insertOne(document);
		
		JSONObject retval = new JSONObject();
		retval.put("status", "ack");
		return retval;
	}

	public JSONObject update(JSONObject data, String collection)
	{
		System.out.println("UPDATE Operation....");
		System.out.println("Collection : "+collection);
		System.out.println("Data : "+data);
		return null;
	}

	public JSONObject delete(JSONObject data, String collection)
	{
		System.out.println("DELETE Operation....");
		System.out.println("Collection : "+collection);
		System.out.println("Data : "+data);
		return null;
	}
}
