package com.preety.connectionpool.trylock;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

	private static ConnectionPool connPool;
	private int poolSize = 10;
	private List<Connection> availableConnections;
	private List<Connection> usedConnections;
	private Properties info;
	private String url = "localhost:27017";

	private Lock lock = new ReentrantLock();

	private ConnectionPool() throws SQLException {
		int ic = poolSize * 3 / 4 + 1;
		availableConnections = new ArrayList<Connection>(ic);
		usedConnections = new ArrayList<Connection>(ic);
		for (int i = 0; i <= poolSize; i++) {
			availableConnections.add(new Connection(url, info));

		}
	}

	public static ConnectionPool getInstance() throws SQLException {
		synchronized (ConnectionPool.class) {
			if (connPool == null) {
				connPool = new ConnectionPool();
			}
		}
		return connPool;
	}

	public void setPoolSize(int size) {
		this.poolSize = size;
	}

	public Connection acquireConnection() throws InterruptedException, NoConnectionAVailable, ConnectionTimeOut {
		
		System.out.println("waiting thread for connection " + Thread.currentThread().getName());
		boolean flag= lock.tryLock(30, TimeUnit.SECONDS); 
		if (flag) {
			try {
				if (availableConnections.size() > 0) {
					Connection c = availableConnections.remove(availableConnections.size() - 1);
					usedConnections.add(c);
					Thread.sleep(100);
					System.out.println("connection acquired by " + Thread.currentThread().getName());
					return c;
				} else {
					System.out.println("No connection available " + Thread.currentThread().getName());
					throw new NoConnectionAVailable("No connection available");
				}
			} finally {
				System.out.println(Thread.currentThread().getName() + ": Lock released.");
				lock.unlock();
			}
		} else {
			System.out.println("Connection timed out " + Thread.currentThread().getId());
			throw new ConnectionTimeOut("Connection timed out");
		}
	}

	public void releaseConnection(Connection conn) {
		usedConnections.remove(conn);
		availableConnections.add(conn);
	}

}
