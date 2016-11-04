package com.khang.tuts.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Callable<Long> heavyCalulator = new Callable<Long>() {

			@Override
			public Long call() throws Exception {
				Thread.sleep(2000);
				return 6_000l;
			}
		};
		Future<Long> resultFuture = executor.submit(heavyCalulator);
		while (!resultFuture.isDone()) {
			System.out.println("Still running then wait");
		}
		long result = resultFuture.get();
		executor.shutdown();
		System.out.println("Result " + result);

	}

}
