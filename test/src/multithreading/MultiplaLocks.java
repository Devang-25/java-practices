package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiplaLocks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Worker().main();
	}

}

class Worker {

	private Object lock1 = new Object();
	private Object lock2 = new Object();

	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();

	public Random random = new Random();

	public void stageOne() {
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
	}

	public void stageTwo() {
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}
	}

	public void process() {
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}

	}

	public void main() {
		System.out.println("starting...");

		long start = System.currentTimeMillis();
		System.out.println("starting time: " + start);

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				process();

			}

		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				process();

			}

		});

		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("end time: " + end);
		System.out.println("start - end time: " + (end - start));
		System.out.println("list1 size: " + list1.size() + ", list2 size: " + list2.size());
	}
}