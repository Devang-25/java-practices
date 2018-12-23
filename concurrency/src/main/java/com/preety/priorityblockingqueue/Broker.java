package com.preety.priorityblockingqueue;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Broker {
	//Comparator<? super E> comparator;
	int initialCapacity= 10*4/3 +1;
	BlockingQueue<Task> queue= new PriorityBlockingQueue<Task>(initialCapacity, new Comparator<Task>() {

		public int compare(Task o1, Task o2) {
			if(o1.getPriority()> o2.getPriority()) return 1;
			if(o1.getPriority()< o2.getPriority()) return -1;
			return 0;
		}
	});
	
	public void addTask(Task task) throws InterruptedException {
		queue.put(task);
	}
	public Task removeTask() throws InterruptedException {
		return queue.poll(2, TimeUnit.SECONDS);
	}
}
