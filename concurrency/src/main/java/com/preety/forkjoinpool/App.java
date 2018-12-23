package com.preety.forkjoinpool;

import java.util.concurrent.ForkJoinPool;

public class App {

	public static void main(String[] args) {
		ForkJoinPool pool= new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		//pool.invoke(new SimpleRecursiveAction(241));
		
		System.out.println(pool.invoke(new SimpleRecursiveTask(120)));

	}

}
