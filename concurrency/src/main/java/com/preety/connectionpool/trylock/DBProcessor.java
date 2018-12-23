package com.preety.connectionpool.trylock;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class MyDao implements Runnable {

	private int processid;
	private int trials;

	public MyDao(int processid, int trials) {
		super();
		this.processid = processid;
		this.trials = trials;
	}

	public void run() {
		try {
			Connection conn= connect(this.trials);
			ConnectionPool.getInstance().releaseConnection(conn);
			System.out.println("released connection.. " + Thread.currentThread().getName());
		} catch (ConnectionNotEstablished e) {
			System.out.println(Thread.currentThread().getName() + "------------" + e.getMessage() + " after "
					+ this.trials + " trials" + "------------");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  catch (SQLException e) {
			e.printStackTrace();
		} catch (ConnectionTimeOut e) {
			e.printStackTrace();
		}
	}

	public Connection connect(int trials)
			throws ConnectionNotEstablished, InterruptedException, ConnectionTimeOut, SQLException {
			
		try {
			return ConnectionPool.getInstance().acquireConnection();
		} catch (NoConnectionAVailable e) {
			if (trials > 0) {
				Thread.sleep(1000);
				System.out.println("trying again.. " + Thread.currentThread().getName());
				return connect(trials - 1);
			} else {
				throw new ConnectionNotEstablished("All Connection trial failed ");
			}
		}
	}

}

public class DBProcessor {
	public static void main(String args[]) {
		int count = 100;
		ExecutorService executor = Executors.newFixedThreadPool(count);

		for (int i = 0; i < count; i++) {
			executor.submit(new MyDao(i, 3));
		}

		executor.shutdown();

		try {
			executor.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
}
