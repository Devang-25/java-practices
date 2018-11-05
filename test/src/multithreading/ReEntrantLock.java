package multithreading;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantLock {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Processor5 proc= new Processor5();
		Thread t1= new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					proc.firstThread();
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
					proc.secondThread();
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
		proc.finished();

	}



}

class Processor5 {
	
	private int count=0;
	private Lock lock = new ReentrantLock();
	private Condition cond= lock.newCondition();
	
	private void increment() {
		for(int i=0; i<1000; i++) {
			count++;
		}
	}

	public void firstThread() throws InterruptedException {
		lock.lock();
		System.out.println("waiting..");
		cond.await();
		System.out.println("woken up..");
		try {
			System.out.println("incrementing first");
			increment();
		}
		finally {
			System.out.println("unlocking first");
			lock.unlock();
		}
		
	}

	public void secondThread() throws InterruptedException{
		Thread.sleep(1000);
		lock.lock();
		
		System.out.println("enter return key");
		new Scanner(System.in).nextLine();
		System.out.println("entered return key");
		
		cond.signal();
		
		try {
			System.out.println("incrementing second");
			increment();
		}
		finally {
			System.out.println("unlocking second");
			lock.unlock();
		}
	}

	public void finished() {
		System.out.println("Count is " + count);
	}
}
