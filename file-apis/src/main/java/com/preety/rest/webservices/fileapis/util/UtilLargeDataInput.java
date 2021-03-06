package com.preety.rest.webservices.fileapis.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UtilLargeDataInput {

	public static void readFromFileLineByLine(String pathname) {
		try (BufferedReader in = new BufferedReader(new FileReader(pathname))) {
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readStreamFromFile(String pathname) {
		try {
			List<String> alist = Files.lines(Paths.get(pathname)).filter(line -> line.contains("abc"))
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readStreamFromSysInput(int size) {
		int numberOfLinesToBeRead = 4;
		System.out.println("input data");

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in), 2 * 1024);
		try {
			String s;
			while ((s = in.readLine()) != null) {

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stream<String> stream = in.lines().limit(numberOfLinesToBeRead);
		stream.map(UtilLargeDataInput::doSOmething);
	}

	public static void processTransactions(List<Transaction> transactions) {
		List<Integer> transactionsIds = transactions.stream().filter(t -> t.getType() == Transaction.GROCERY)
				.sorted(Comparator.comparing(Transaction::getValue).reversed()).map(Transaction::getId)
				.collect(Collectors.toList());
	}

	public static void parallelTask() {

		/*
		 * There actually is a trick how to execute a parallel operation in a specific
		 * fork-join pool. If you execute it as a task in a fork-join pool, it stays
		 * there and does not use the common one.
		 * 
		 */
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2");
		ForkJoinPool forkJoinPool = new ForkJoinPool(2);
		try {
			System.out.println("inside forkJoinPool");
			forkJoinPool.submit(() ->
			// parallel task here, for example

			IntStream.range(1, 1000000).parallel().filter(UtilLargeDataInput::isPrime)).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("outside forkJoinPool");
	}

	public static boolean isPrime(long n) {
		if (n <= 1)
			return false;
		for (int i = 2; i < Math.floor(Math.sqrt(n)); i++) {
			if (n % i == 0) {
				System.out.println("false" + Thread.currentThread().getName());
				return false;
			}
		}
		System.out.println("false");
		return true;
	}

	public static String doSOmething(String s) {
		// reverse
		char[] charstr = s.toCharArray();
		int n = s.length();
		for (int i = 0; i < n / 2; i++) {
			charstr[i] = charstr[n - 1 - i];
		}
		s = String.valueOf(charstr);
		return s;
	}

	public static void main(String args[]) {
		String pathname = "";
		// readStreamFromSysInput(10);
		parallelTask();
	}

}
