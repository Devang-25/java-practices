package com.preety.forkjoinpool;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction{
	private int workLoad;
	
	 

	public SimpleRecursiveAction(int workLoad) {
		super();
		this.workLoad = workLoad;
	}



	@Override
	protected void compute() {
		
		if(this.workLoad>100) {
			System.out.println("load should run in parallel.. " + workLoad);
			int load1= workLoad/2;
			int load2= workLoad/2;
			if(workLoad%2==1) {
				load1= workLoad/2 +1;
			}
			SimpleRecursiveAction task1=	new SimpleRecursiveAction(load1);
			SimpleRecursiveAction task2= new SimpleRecursiveAction(load2);
			task1.fork();
			task2.fork();
		} else {
			System.out.println("load can run sequentially.. " + workLoad);
		}
		
	}

}
