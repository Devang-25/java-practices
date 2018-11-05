package multithreading;

/*
 Solving problem:
 Interference happens when two operations, running in different threads, but acting on the same data, 
 interleave. This means that the two operations consist of multiple steps, and the sequences of steps overlap.
 eg: incrementing common data count
 */

public class SynchronizedKey {
	
	public int count=0;
	
	public  synchronized void increment() {
		count++;
	}
	
   public void dowork() {
	   Thread t1= new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0; i< 10000; i++) {
					increment();
				}
				
			}
			
		});
		
		Thread t2= new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0; i< 10000; i++) {
					increment();
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
		System.out.println(count);
   }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SynchronizedKey sk= new SynchronizedKey();
		sk.dowork();
		
	}

}
