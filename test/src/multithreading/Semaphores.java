package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Semaphores {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 200; i++) {
			executor.submit(new Runnable() {

				@Override
				public void run() {
					Connection.getInstance().connect();

				}

			});
		}
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);

	}

}

class Connection {

	private static Connection instance = new Connection();
	private int connections = 0;
	Semaphore sem = new Semaphore(10, true);

	private Connection() {
	}

	public static Connection getInstance() {
		return instance;
	}

	public void connect() {
		try {
			sem.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			doConnect();
		} finally {
			sem.release();
		}
	}

	public void doConnect() {

		synchronized (this) {
			connections++;
			System.out.println("current connections: " + connections);
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		synchronized (this) {
			connections--;
		}

	}
}
