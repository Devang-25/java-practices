package com.preety.connectionpool.semaphore;

public class Connection {
	private int id;
	

	public Connection(int id) {
		super();
		this.id = id;
	}


	@Override
	public String toString() {
		return "Connection [id=" + id + "]";
	}

}
