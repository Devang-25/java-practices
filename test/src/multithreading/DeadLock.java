package multithreading;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Runner2 proc = new Runner2();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					proc.firstThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					proc.secondThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		proc.finished();

	}

}

class Runner2 {

	private Account acc1 = new Account();
	private Account acc2 = new Account();

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	private void aquireLock(Lock firstLock, Lock secondLock) throws InterruptedException {
		while (true) {
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;

			gotFirstLock = firstLock.tryLock();
			gotSecondLock = secondLock.tryLock();

			if (gotFirstLock && gotSecondLock) {
				return;
			}
			if (gotFirstLock)
				firstLock.unlock();
			if (gotSecondLock)
				secondLock.unlock();
			
			Thread.sleep(1);
		}
	}

	public void firstThread() throws InterruptedException {
		aquireLock(lock1, lock2);
		Random random = new Random();
		try {
			System.out.println("transfering from acct1 to acct2");
			for (int i = 0; i < 10000; i++) {
				Account.transfer(acc1, acc2, random.nextInt(100));
			}
			System.out.println("transfering done from acct1 to acct2");
		} finally {
			lock1.unlock();
			lock2.unlock();
		}
	}

	public void secondThread() throws InterruptedException {
		aquireLock(lock1, lock2);
		Random random = new Random();
		try {
			System.out.println("transfering from acct2 to acct1");
			for (int i = 0; i < 10000; i++) {
				Account.transfer(acc2, acc1, random.nextInt(100));
			}
			System.out.println("transfering done from acct2 to acct1");
		} finally {
			lock1.unlock();
			lock2.unlock();
		}
	}

	public void finished() {
		System.out.println("acc1 balance: " + acc1.getBalance());
		System.out.println(" acc2 balance: " + acc2.getBalance());
		System.out.println("total balance: " + (acc1.getBalance() + acc2.getBalance()));
	}
}

class Account {
	private int balance = 10000;

	public void withdraw(int amount) {
		balance -= amount;
	}

	public void deposite(int amount) {
		balance += amount;
	}

	public int getBalance() {
		return balance;
	}

	public static void transfer(Account acc1, Account acc2, int amount) {
		acc1.withdraw(amount);
		acc2.deposite(amount);
	}
}
