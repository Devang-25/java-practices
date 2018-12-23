package com.preety.connectionpool.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MysqlConnectionPool implements ConnectionPool{
	private static MysqlConnectionPool connectionPoolInstance;
	private BlockingQueue<DBConnection> connections;
	private final int MAX_CONNECTIONS=10;
	
	private MysqlConnectionPool(String url) {
		super();
		int cap= MAX_CONNECTIONS*4/3 +1;
		this.connections = new ArrayBlockingQueue<DBConnection>(cap);
		for(int i=0; i<MAX_CONNECTIONS; i++) {
			connections.add(DbDriverManager.getConnection(url));
		}
	}

	public static MysqlConnectionPool getInstance(String url) {
		if(connectionPoolInstance==null) {
			synchronized(ConnectionPool.class) {
				if(connectionPoolInstance==null) {
					connectionPoolInstance= new MysqlConnectionPool( url);
				}
			}
		}
		return connectionPoolInstance;
	}

	public DBConnection aquireConnection() throws InterruptedException {
		System.out.println("waiting for connection " + Thread.currentThread().getName());
		return connections.poll(1, TimeUnit.MINUTES);
	}

	public void releaseConnection(DBConnection conn) throws InterruptedException {
		
		connections.put(conn);
		
	}

}
