package com.preety.connectionpool.trylock;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

public class App {
	
	
	@Test
	public void givenUsingTimer_whenCancelingTimerTask_thenCorrect()
	  throws InterruptedException {
	    TimerTask task = new TimerTask() {
	        public void run() {
	            System.out.println("Task performed on " + new Date());
	            cancel();
	        }
	    };
	    Timer timer = new Timer("Timer");
	     
	    timer.scheduleAtFixedRate(task, 1000L, 1000L);
	     
	    Thread.sleep(1000L * 2);
	}
	
	@Test
	public void givenUsingTimer_whenCancelingTimer_thenCorrect() 
	  throws InterruptedException {
	    TimerTask task = new TimerTask() {
	        public void run() {
	            System.out.println("Task performed on " + new Date());
	        }
	    };
	    Timer timer = new Timer("Timer");
	     
	    timer.scheduleAtFixedRate(task, 1000L, 1000L);
	     
	    Thread.sleep(1000L * 2); 
	    timer.cancel(); 
	}
	
	@Test
	public void givenUsingTimer_whenStoppingThread_thenTimerTaskIsCancelled() 
	  throws InterruptedException {
	    TimerTask task = new TimerTask() {
	        public void run() {
	            System.out.println("Task performed on " + new Date());
	            
	            // TODO: stop the thread here
	        }
	    };
	    Timer timer = new Timer("Timer");
	     
	    timer.scheduleAtFixedRate(task, 1000L, 1000L);
	     
	    Thread.sleep(1000L * 2); 
	}

}
