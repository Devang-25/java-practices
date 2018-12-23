package com.preety.forkjoinpool;

import java.util.concurrent.RecursiveTask;

public class SimpleRecursiveTask  extends RecursiveTask<Integer>{
	private int workLoad;
	
	 

	public SimpleRecursiveTask(int workLoad) {
		super();
		this.workLoad = workLoad;
	}
	@Override
	protected Integer compute() {
		if(this.workLoad>100) {
			System.out.println("load should run in parallel.. " + workLoad);
			int load1= workLoad/2;
			int load2= workLoad/2;
			if(workLoad%2==1) {
				load1= workLoad/2 +1;
			}
			SimpleRecursiveTask task1=	new SimpleRecursiveTask(load1);
			SimpleRecursiveTask task2= new SimpleRecursiveTask(load2);
			
			task1.fork();
			task2.fork();
			
			int solution=0;
			solution += task1.join();
			solution +=task2.join();
			return solution;
		} else {
			System.out.println("load can run sequentially.. " + workLoad);
			return 2 * workLoad;
		}
	}

}
