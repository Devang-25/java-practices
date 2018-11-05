package com.elasticsearch;

import java.net.UnknownHostException;
import java.util.function.Function;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;


public class MongoConnection {

	    private static MongoConnection conn = new MongoConnection();
	    private  MongoClient client;
	    private  DB db;
	    private MongoConnection(){
	        try{
	            init();
	        }catch(Exception ex){
	            ex.printStackTrace();
	        }
	    }
	    private void init() throws UnknownHostException{
	        this.client = new MongoClient("localhost" , 27017);
	    }
	    public static MongoConnection get(){
	        return conn;
	    }
	    public MongoConnection connectDb(String dbname){
	        if(db !=null){
	            throw new RuntimeException("Already conected to " + db.getName() + "can't connect " + dbname);
	        }
	        this.db = client.getDB(dbname);
	        System.out.println("DB Details :: " + db.getName());
	        return conn;
	    }
	    public <T,X> DBCursor findByKey(String collectionName,String key,T value,Function<T,X> convertDataType){
	        DBCollection collection = db.getCollection(collectionName);
	        BasicDBObject searchQuery = new BasicDBObject();
	        searchQuery.put(key, convertDataType.apply(value));
	        System.out.println("search Query ::" + searchQuery);
	        DBCursor cursor = collection.find(searchQuery);
	        return cursor;
	    }
	}
