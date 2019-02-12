package com.preety.semaphore_and_mutex;

public class SemaphorMonitor {
	private int N;
	private int[] emptyBuf;

	/*
	 * Initialize a value atomically  P (or Down or Wait) definition Atomic
	 * operation Wait for semaphore to become positive and then decrement
	 */
	void P(int s) {
		while (s <= 0)
			;
		s--;
	}

	/*
	 * V (or Up or Signal) definition Atomic operation Increment semaphore by 1
	 */
	void V(int s) {
		s++;
	}

	/*
	 * Binary semaphores can provide mutual exclusion (solution of critical section
	 * problem) Counting semaphores can represent a resource with multiple instances
	 * (e.g. solving producer/consumer problem)  Signaling events (persistent
	 * events that stay relevant even if nobody listening right now)
	 */

	/*
	 * Classic Synchronization Problems  There are a number of “classic” problems
	 * that represent a class of synchronization situations  Critical Section
	 * problem  Producer/Consumer problem  Reader/Writer problem  5 Dining
	 * Philosophers
	 */

	void producer() {
		int emptyCount = N;
		int fullCount = 0;
		int mutex = 1;
		int emptyBuf =N; 
		int fullBuf = 0;
		while (true) { // 1 for true in binary
			// produce an item
			P(emptyBuf);
			P(mutex);
			// put the item in buffer
			V(mutex);
			V(fullBuf);
		}
	}

}
