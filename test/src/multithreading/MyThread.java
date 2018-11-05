package multithreading;

public class MyThread{
	

	public static void main(String[] args) {

//		Runner r= new Runner();
//		Thread t= new Thread(new Runner1());
//		
//		r.start();
//		t.start();
		
		Thread t1= new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=0; i<100; i++) {
					System.out.println("hello2 " + i);
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		t1.start();

	}

}

class Runner1 implements Runnable{

	@Override
	public void run() {
		for(int i=0; i<100; i++) {
			System.out.println("hello2 " + i);
		}
		
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
}

class Runner extends Thread{
	public void run() {
		for(int i=0; i<100; i++) {
			System.out.println("hello1 " + i);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
