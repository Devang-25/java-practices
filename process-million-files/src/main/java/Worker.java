
public class Worker implements Runnable{
	private int pid;
	private String filepath;
	
	
	public int getPid() {
		return pid;
	}

	public String getFilepath() {
		return filepath;
	}

	public Worker(int pid, String filepath) {
		this.pid=pid;
		this.filepath= filepath;
	}

	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("done processing");
	}

}
