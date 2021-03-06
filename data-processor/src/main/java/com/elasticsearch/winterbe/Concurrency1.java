package com.elasticsearch.winterbe;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/*
 * how to execute code in parallel via threads, tasks and executor services.
 */
public class Concurrency1 {
	/*
	 * All modern operating systems support concurrency both via processes and
	 * threads. Processes are instances of programs which typically run independent
	 * to each other, e.g. if you start a java program the operating system spawns a
	 * new process which runs in parallel to other programs. Inside those processes
	 * we can utilize threads to execute code concurrently, so we can make the most
	 * out of the available cores of the CPU.
	 * 
	 * Before starting a new thread you have to specify the code to be executed by
	 * this thread, often called the task. This is done by implementing Runnable - a
	 * functional interface defining a single void no-args method run() as
	 * demonstrated in the following example:
	 * 
	 * 
	 * Due to concurrent execution we cannot predict if the runnable will be invoked
	 * before or after printing 'done'. The order is non-deterministic, thus making
	 * concurrent programming a complex task in larger applications.
	 * 
	 * TimeUnit is a useful enum for working with units of time.
	 */

	public static void startThread() {

		Runnable task = () -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("Hello " + threadName);
		};

		task.run();

		Thread thread = new Thread(task);
		thread.start();

		System.out.println("Done!");

		Runnable runnable = () -> {
			try {
				String name = Thread.currentThread().getName();
				System.out.println("Foo " + name);
				TimeUnit.SECONDS.sleep(1);
				System.out.println("Bar " + name);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Thread t = new Thread(runnable);
		t.start();

	}

	/*
	 * Executors# The Concurrency API introduces the concept of an ExecutorService
	 * as a higher level replacement for working with threads directly. Executors
	 * are capable of running asynchronous tasks and typically manage a pool of
	 * threads, so we don't have to create new threads manually. All threads of the
	 * internal pool will be reused under the hood for revenant tasks, so we can run
	 * as many concurrent tasks as we want throughout the life-cycle of our
	 * application with a single executor service.
	 * 
	 * This is how the first thread-example looks like using executors:
	 * 
	 * An ExecutorService provides two methods to stop exectorservice: shutdown()
	 * stop taking new tasks but let current running task to complete.
	 * 
	 * shutdownNow() interrupts all running tasks and shut the executor down
	 * immediately.
	 * 
	 * awaitTermination wait for the given period to timeout if any running task.
	 * After a maximum of given timee the executor finally shuts down by
	 * interrupting all running tasks.
	 */
	public static void executors() {

		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("Hello " + threadName);
		});
		try {
			System.out.println("attempt to shutdown executor");
			executor.shutdown();
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("tasks interrupted");
		} finally {
			if (!executor.isTerminated()) {
				System.err.println("cancel non-finished tasks");
			}
			executor.shutdownNow();
			System.out.println("shutdown finished");
		}

	}

	/*
	 * Callables and Futures#
	 * 
	 * In addition to Runnable executors support another kind of task named
	 * Callable. Callables are functional interfaces just like runnables but instead
	 * of being void they return a value.
	 * 
	 * This lambda expression defines a callable returning an integer after sleeping
	 * for one second:
	 * 
	 * submit() doesn't wait until the task completes, the executor service cannot
	 * return the result of the callable directly. Instead the executor returns a
	 * special result of type Future which can be used to retrieve the actual result
	 * at a later point in time.
	 * 
	 * After submitting the callable to the executor we first check if the future
	 * has already been finished execution via isDone().
	 * 
	 * Calling the method get() blocks the current thread and waits until the
	 * callable completes before returning the actual result.
	 * 
	 */
	public static void callablesAndFutures() {

		Callable<Integer> task = () -> {
			try {
				TimeUnit.SECONDS.sleep(2);
				return 123;
			} catch (InterruptedException e) {
				throw new IllegalStateException("task interrupted", e);
			}
		};

		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<Integer> future = executor.submit(task);

		System.out.println("future done? " + future.isDone());

		Integer result = null;
		try {
			result = future.get();

			// future.get(1, TimeUnit.SECONDS); // to timeout to stop running forever
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("future done? " + future.isDone());
		System.out.print("result: " + result);
	}

	/*
	 * InvokeAll# Executors support batch submitting of multiple callables at once
	 * via invokeAll(). This method accepts a collection of callables and returns a
	 * list of futures.
	 */
	public static void callableInvokeAll() {
		ExecutorService executor = Executors.newWorkStealingPool();

		List<Callable<String>> callables = Arrays.asList(() -> "task1", () -> "task2", () -> "task3");

		try {
			executor.invokeAll(callables).stream().map(future -> {
				try {
					return future.get();
				} catch (Exception e) {
					throw new IllegalStateException(e);
				}
			}).forEach(System.out::println);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * InvokeAny# Another way of batch-submitting callables is the method
	 * invokeAny() which works slightly different to invokeAll(). Instead of
	 * returning future objects this method blocks until the first callable
	 * terminates and returns the result of that callable.
	 * 
	 * In order to test this behavior we use this helper method to simulate
	 * callables with different durations. The method returns a callable that sleeps
	 * for a certain amount of time until returning the given result:
	 * 
	 * We use this method to create a bunch of callables with different durations
	 * from one to three seconds. Submitting those callables to an executor via
	 * invokeAny() returns the string result of the fastest callable - in that case
	 * task2:
	 * 
	 * 
	 * The example uses yet another type of executor created via
	 * newWorkStealingPool(). This factory method is part of Java 8 and returns an
	 * executor of type ForkJoinPool which works slightly different than normal
	 * executors. Instead of using a fixed size thread-pool ForkJoinPools are
	 * created for a given parallelism size which per default is the number of
	 * available cores of the hosts CPU.
	 */

	public static Callable<String> callable(String result, long sleepSeconds) {
		return () -> {
			TimeUnit.SECONDS.sleep(sleepSeconds);
			return result;
		};
	}

	public static void callableInvokeAny() {

		ExecutorService executor = Executors.newWorkStealingPool();

		List<Callable<String>> callables = Arrays.asList(callable("task1", 2), callable("task2", 1),
				callable("task3", 3));

		String result = null;
		try {
			result = executor.invokeAny(callables);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);

		// => task2
	}

	/*
	 * Scheduled Executors# In order to periodically run common tasks multiple
	 * times, we can utilize scheduled thread pools.
	 * 
	 * A ScheduledExecutorService is capable of scheduling tasks to run either
	 * periodically or once after a certain amount of time has elapsed.
	 * 
	 * This code sample schedules a task to run after an initial delay of three
	 * seconds has passed:
	 * 
	 * ScheduledFuture which - in addition to Future - provides the method
	 * getDelay() to retrieve the remaining delay. After this delay has elapsed the
	 * task will be executed concurrently.
	 * 
	 * In order to schedule tasks to be executed periodically, executors provide the
	 * two methods scheduleAtFixedRate() and scheduleWithFixedDelay(). The first
	 * method is capable of executing tasks with a fixed time rate
	 * 
	 * Please keep in mind that scheduleAtFixedRate() doesn't take into account the
	 * actual duration of the task. So if you specify a period of one second but the
	 * task needs 2 seconds to be executed then the thread pool will working to
	 * capacity very soon.
	 * 
	 * In that case you should consider using scheduleWithFixedDelay() instead. This
	 * method works just like the counterpart described above. The difference is
	 * that the wait time period applies between the end of a task and the start of
	 * the next task.
	 * 
	 * This example schedules a task with a fixed delay of one second between the
	 * end of an execution and the start of the next execution. The initial delay is
	 * zero and the tasks duration is two seconds. So we end up with an execution
	 * interval of 0s, 3s, 6s, 9s and so on.
	 */

	public static void scheduledExecutors() {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
		ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);

		try {
			TimeUnit.MILLISECONDS.sleep(1337);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
		System.out.printf("Remaining Delay: %sms", remainingDelay);

		ScheduledExecutorService executor1 = Executors.newScheduledThreadPool(1);

		Runnable task1 = () -> System.out.println("Scheduling: " + System.nanoTime());

		int initialDelay = 0;
		int period = 1;
		executor1.scheduleAtFixedRate(task1, initialDelay, period, TimeUnit.SECONDS);

		ScheduledExecutorService executor2 = Executors.newScheduledThreadPool(1);

		Runnable task2 = () -> {
			try {
				TimeUnit.SECONDS.sleep(2);
				System.out.println("Scheduling: " + System.nanoTime());
			} catch (InterruptedException e) {
				System.err.println("task interrupted");
			}
		};

		executor2.scheduleWithFixedDelay(task2, 0, 1, TimeUnit.SECONDS);
	}

	public static void main(String args[]) {
		// callablesAndFutures();
		callableInvokeAny();
	}
}
