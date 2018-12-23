package com.preety.connectionpool.blockingqueue;

public interface ConnectionPool {
	
	public DBConnection aquireConnection() throws InterruptedException;
	public void releaseConnection(DBConnection c) throws InterruptedException;
}
