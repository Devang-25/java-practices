package multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor2 implements Runnable{
	private CountDownLatch latch;
	
	Processor2(CountDownLatch latch){
		this.latch=latch;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		System.out.println("starting");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("ending");
		latch.countDown();
	}
	
}

public class CountLatch {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CountDownLatch latch= new CountDownLatch(2);
		
		ExecutorService executor= Executors.newFixedThreadPool(3);
		
		for(int i=0; i<3; i++) {
			executor.submit(new Processor2(latch) );
		}
		
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("completed");
	}

}
