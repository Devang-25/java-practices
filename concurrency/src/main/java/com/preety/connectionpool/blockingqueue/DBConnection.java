package com.preety.connectionpool.blockingqueue;

import java.util.concurrent.atomic.AtomicInteger;

public class DBConnection {
	private final AtomicInteger id = new AtomicInteger(0);
	private String connectionUrl;

	public DBConnection(String url) {
		connectionUrl= url;
		id.getAndIncrement();
	}

	@Override
	public String toString() {
		return "DBConnection [id=" + id + ", connectionUrl=" + connectionUrl + "]";
	}
	
	

}
