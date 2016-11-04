package com.khang.tuts.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureExample {
	public static void main(String[] args) throws InterruptedException {
		CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "Hello World";
			}
		}).thenApply(new Function<String, Integer>() {

			@Override
			public Integer apply(String t) {
				return t.length();
			}
		}).thenAccept(new Consumer<Integer>() {

			@Override
			public void accept(Integer result) {
				System.out.println("Result: " + result);
				
			}
		});
		Thread.sleep(2000);
	}
	
	class Reporter {
		void report(String ip) {
			
		}
	}
}
