package multithreading;

import java.util.LinkedList;
import java.util.Random;

public class Example {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Processor4 proc= new Processor4();
		Thread t1= new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					proc.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		Thread t2= new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					proc.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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


	}


}

class Processor4 {
	private LinkedList<Integer> list = new LinkedList();
	private int limit = 10;
	private Object lock = new Object();

	public void produce() throws InterruptedException {

		int value = 0;
		while (true) {
			synchronized (lock) {
				while (list.size() == limit) {
					lock.wait();
				}
				list.add(value++);
				lock.notify();
			}
		}

	}

	public void consume() throws InterruptedException {
		Random random= new Random();
		while (true) {
			synchronized (lock) {
				while(list.size()==0) {
					lock.wait();
				}
				System.out.println("size of list: " + list.size());
				int value = list.removeFirst();
				System.out.println("value: " + value);
				lock.notify();
			}
			Thread.sleep(random.nextInt(1000));
		}
		
	}

}