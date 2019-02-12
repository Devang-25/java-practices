package com.preety.connectionpool.semaphore;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) {
		Broker broker= new Broker();
		ExecutorService ex= Executors.newFixedThreadPool(10);
		for(int i=0; i<100; i++) {
			ex.submit(new Consumer(broker, i));
		}
		
		ex.shutdown();
		try {
			ex.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
