package com.preety.priorityblockingqueue;

public class Consumer implements Runnable{
	private Broker broker;
	
	public Consumer( Broker broker) {
		super();
		this.broker = broker;
	}

	public void run() {
		try {
			
		Task t=	broker.removeTask();
		Thread.sleep(t.getExecutionTime());
		System.out.println("task completed: " + t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
