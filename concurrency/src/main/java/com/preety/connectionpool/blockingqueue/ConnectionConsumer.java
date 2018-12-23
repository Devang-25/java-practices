package com.preety.connectionpool.blockingqueue;

import java.util.Random;

public class ConnectionConsumer implements Runnable {
	ConnectionPool pool;
	Random random= new Random();

	public ConnectionConsumer(ConnectionPool pool) {
		super();
		this.pool = pool;
	}

	public void run() {
		try {
			DBConnection conn = this.pool.aquireConnection();
			if (conn == null) {

				throw new TimeOutConnection("Connection timed out");
			}
			doTask();
			pool.releaseConnection(conn);
			System.out.println("completed task, released connection");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (TimeOutConnection e) {
			e.printStackTrace();
		}

	}
	
	public void doTask() throws InterruptedException {
		Thread.sleep(random.nextInt(500));
	}

}
