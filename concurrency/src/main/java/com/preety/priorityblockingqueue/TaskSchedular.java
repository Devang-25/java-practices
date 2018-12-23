package com.preety.priorityblockingqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskSchedular {

	public static void main(String[] args) throws IOException, InterruptedException {
		Broker broker= new Broker();
		Map<Character, Task> taskmap= new HashMap<Character, Task>();
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		String line=null;
		Task t1=null;
		while((line= br.readLine())!= null) {
			if(line.length()<1) {
				System.out.println("invalid input, retry");
			}
			Character taskId= line.charAt(0);
			String[] dep= line.split(" ");
			
			t1= new Task(taskId, 1);
			
			Task t2=taskmap.get(taskId);
			if(t2!=null) {
				t2.setPriority(t2.getPriority()+1);
			}else {
				taskmap.put(taskId, t1);
			}
			
			for(int i=1; i<dep.length; i++) {
				Task t3= new Task(taskId, 2);
				Task t4=taskmap.get(taskId);
				if(t4!=null) {
					t4.setPriority(t4.getPriority()+1);
				}else {
					taskmap.put(taskId, t3);
				}
			}
			for(Character task: taskmap.keySet()) {
				broker.addTask(taskmap.get(task));
			}
		}
		
		
		ExecutorService executer= Executors.newCachedThreadPool();
		for(Character task: taskmap.keySet()) {
			executer.submit(new Consumer(broker));
		}
		


	}

}
