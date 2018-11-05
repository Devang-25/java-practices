package com.elasticsearch.winterbe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/*
 * how to synchronize access to mutable shared variables 
 * via the synchronized keyword, locks and semaphores.
 */
public class Concurrency2 {
	private static int count;

	/*
	 * When calling this method concurrently from multiple threads we're in serious
	 * trouble:
	 * 
	 */
	void increment() {
		count = count + 1;
	}

	/*
	 * The reason is that we share a mutable variable upon different threads without
	 * synchronizing the access to this variable which results in a race condition.
	 * 
	 * A race condition or race hazard is the behavior of an electronics, software,
	 * or other system where the output is dependent on the sequence or timing of
	 * other uncontrollable events. It becomes a bug when events do not happen in
	 * the order the programmer intended.
	 */
	public void unsynchronizedSharedVar() {
		int count = 0;

		ExecutorService executor = Executors.newFixedThreadPool(2);

		IntStream.range(0, 10000).forEach(i -> executor.submit(this::increment));

		ConcurrentUtil.stop(executor);

		System.out.println(count); // 9965
	}

	synchronized void incrementSync() {
		count = count + 1;
	}

	/*
	 * We can utilize synchronized to fix the above race conditions when
	 * incrementing the count:
	 * 
	 * When using incrementSync() concurrently we get the desired result count of
	 * 10000. No race conditions occur any longer and the result is stable with
	 * every execution of the code:
	 * 
	 * Internally Java uses a so called monitor also known as monitor lock or
	 * intrinsic lock in order to manage synchronization. This monitor is bound to
	 * an object, e.g. when using synchronized methods each method share the same
	 * monitor of the corresponding object.
	 * 
	 * All implicit monitors implement the reentrant characteristics. Reentrant
	 * means that locks are bound to the current thread. A thread can safely acquire
	 * the same lock multiple times without running into deadlocks (e.g. a
	 * synchronized method calls another synchronized method on the same object).
	 */
	public void synchronizedSharedVar() {

		ExecutorService executor = Executors.newFixedThreadPool(2);

		IntStream.range(0, 10000).forEach(i -> executor.submit(this::incrementSync));

		ConcurrentUtil.stop(executor);

		System.out.println(count); // 10000
	}

	/*
	 * The synchronized keyword is also available as a block statement.
	 */

	void incrementBlockSync() {
		synchronized (this) {
			count = count + 1;
		}
	}
}
