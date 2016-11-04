package com.khang.tuts.concurrency;

public class ThreadExample {
	
	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				/*try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				System.out.println("Running on " + Thread.currentThread().getName());
			}
		});
		t.setDaemon(true);
		t.start(); // Run on a new thread
		//t.run(); // Run a main thread
		System.out.println("Thread completed on " + Thread.currentThread().getName());
	}
}
