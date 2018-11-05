package multithreading;

import java.util.Scanner;

public class WaitAndNotify {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Processor3 proc= new Processor3();
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

class Processor3{
	
	public void produce() throws InterruptedException {
		synchronized(this) {
			System.out.println("procuder process running");
			wait();
			System.out.println(" resumed");
		}
		
		
	} 
	
	public void consume() throws InterruptedException {
		Thread.sleep(2000);
		
		synchronized(this) {
			Scanner sc= new Scanner(System.in);
			System.out.println(" waiting for return key");
			sc.nextLine();
			System.out.println(" pressed return key");
			notify();
		}
	}
}
