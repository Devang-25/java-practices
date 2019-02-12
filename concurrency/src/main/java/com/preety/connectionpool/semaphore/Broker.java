package com.preety.connectionpool.semaphore;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Broker {

	private Queue<Connection> connections;
	private final int count = 10;
	// private Semaphore semaphoreProd;
	private Semaphore semaphoreCon;

	Broker() {
		// semaphoreProd= new Semaphore(count);
		semaphoreCon = new Semaphore(0, true);
		connections = new LinkedList<Connection>();
		for (int i = 0; i < count; i++) {
			// no need of produder semaphore. Already produced data in start
			connections.add(new Connection(i));
			semaphoreCon.release();

		}
	}

	public Connection acquireConnection() throws InterruptedException {
		semaphoreCon.tryAcquire(2, TimeUnit.MINUTES);
		return connections.poll();
	}

	public void releaseConnection(Connection conn) {
		connections.add(conn);
		semaphoreCon.release();
	}

}
