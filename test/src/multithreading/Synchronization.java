package multithreading;

/*
 Solving Problem:
 A thread doesn't have a local copy of memory. Part of the memory the thread reads/writes could be from a cache, 
 instead of main memory. ... So if one thread tries to change a int variable for example of some object
  it caches the int variable and if it changes it other thread might not see the change.
  eg: running state is changed by main thread but Processor thread gets data from cache. Volatile helps here.
 */

import java.util.Scanner;

class Processor extends Thread{
	private volatile boolean running=true; //prevent thread caching their variable,It helps if it is not changed from same thread, but changed from other thread. Makes it visible
	public void run() {
		while(running) {
			System.out.println("hello");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void shutdown() {
		this.running= false;
		
	}
}

public class Synchronization {

	public static void main(String[] args) {
		Processor proc= new Processor();
		proc.start();
		
		System.out.println("press enter to stop");
		
		Scanner sc= new Scanner(System.in);
		sc.nextLine();
		proc.shutdown();
	}
}
