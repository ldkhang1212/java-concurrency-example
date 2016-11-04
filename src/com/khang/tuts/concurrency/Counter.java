package com.khang.tuts.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Counter {

	protected long count = 0;

	public long add(long value) {
		this.count = this.count + value;
		return this.count;
	}




	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(100);
		Counter counter = new Counter();
		List<Callable<Long>> tasks = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			executor.submit(new Callable<Long>() {
				
				@Override
				public Long call() throws Exception {
					Thread.sleep(1000);
					long value = counter.add(1);
					System.out.println("Current value: " + value);
					return value;
				}
			});
		}
		executor.invokeAll(tasks);
	}
}
