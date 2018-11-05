package multithreading;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ExecutorService executer= Executors.newFixedThreadPool(3);
		for(int i=0; i< 5; i++) {
			executer.submit(new Processor1(i));
		}
		
		executer.shutdown();
		try {
			executer.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("All tasks submitted");
	}

}

class Processor1 extends Thread{
	private int id;
	
	Processor1(int id){
		this.id=id;
	}
	
	public void run() {
		System.out.println("Starting thread: " + id);	
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("ending thread: " + id);	
	}
}
