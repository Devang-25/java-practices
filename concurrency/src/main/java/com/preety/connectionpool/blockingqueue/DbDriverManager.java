package com.preety.connectionpool.blockingqueue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


public class DbDriverManager {
	public  static Connection getMySqlDbConnection(String url, Properties info) throws SQLException {
		return DriverManager.getConnection(url, info);
	}
	
	public static MongoClient getMongoConnection(String url) {
		return new MongoClient(new MongoClientURI(url));
	}
	
	public static DBConnection getConnection(String url) {
		return new DBConnection(url);
	}

}

