import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Processor {
	private static final int MAX=1000;
	private static int completed=0;
	private static int failed=0;
	
	void handleError(String file, Exception e) {
		System.out.println("File processing failed, file: " + file + ", Error: " + e.getCause());

	}
	
	void startProcess(int poolsize , List<Worker> workers) {
		ExecutorService es= Executors.newFixedThreadPool(poolsize);
		Map<String, Future> filefutures= new HashMap<String, Future>();
		for(int i=0; i<poolsize; i++) {
			Worker worker = workers.get(i);
			Future<?> done = es.submit(worker);
			filefutures.put(worker.getFilepath(), done);
		}
		es.shutdown();
		try {
			es.awaitTermination(1, TimeUnit.MINUTES);
			System.out.println("witing for all process to complete for 1 min");
		} catch (InterruptedException e) {
			System.out.println("interrupted while waiting");
		} 
		
		for(String file: filefutures.keySet()) {
			try {
				filefutures.get(file).get(10, TimeUnit.SECONDS);
				
			} catch (InterruptedException e) {
				failed++;
				handleError(file, e);
				JsonUtil.writeToFile(file, e.getCause().toString());
			} catch (ExecutionException e) {
				failed++;
				handleError(file, e); 
				JsonUtil.writeToFile(file, e.getCause().toString());
			} catch (TimeoutException e) {
				failed++;
				handleError(file, e); 
				JsonUtil.writeToFile(file, e.getCause().toString());
			}
		}
		boolean allDone=true;
		for(Future f: filefutures.values()) {
			allDone &=f.isDone();
		}
		System.out.println("all done!! " + allDone);

	}

	public static void main(String[] args) {
		List<Worker> workers= new ArrayList<Worker>();
		for(int i=0; i<MAX; i++) {
			String filepath=  "basepath/" + i + "/file_" + i;
			Worker t= new Worker(i,filepath)  ;
			workers.add(t);
		}
		
		Processor p= new Processor();
		p.startProcess(MAX, workers);
	
	}

}
