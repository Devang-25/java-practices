package com.preety.connectionpool.semaphore;

import java.util.Random;

public class Consumer implements Runnable{
	private Broker broker;
	private int pid;
	private Random random= new Random();
	

	public Consumer(Broker broker, int pid) {
		super();
		this.broker = broker;
		this.pid = pid;
	}


	public void run() {
		
		try {
			Connection conn= broker.acquireConnection();
			System.out.println("acquired connection: "+ conn);
			// do something with conn
			Thread.sleep(random.nextInt(1000));
			// release connection after done.
			broker.releaseConnection(conn);
			System.out.println("release connection: "+ conn);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
