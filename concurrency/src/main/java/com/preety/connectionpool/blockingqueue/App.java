package com.preety.connectionpool.blockingqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class App {
	private static int reqCount=1000;
	private Semaphore semaphore;
	
	public App(Semaphore semaphore) {
		super();
		this.semaphore = semaphore;
	}

	public static void main(String [] args) {
		Semaphore semaphore= new Semaphore(reqCount);
		
		ExecutorService executors= Executors.newCachedThreadPool();
		String url="";
		ConnectionPool pool= MysqlConnectionPool.getInstance(url);
		for(int i=0; i< reqCount; i++) {
			executors.submit(new ConnectionConsumer(pool));
			
		}
		
		executors.shutdown();

		try {
			executors.awaitTermination(60, TimeUnit.MINUTES);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
	}

}
