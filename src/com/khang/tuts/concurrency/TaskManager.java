package com.khang.tuts.concurrency;

public class TaskManager {
	
	public void executeTask(String requestObj) {
		Thread t = new Thread(() -> {
			System.out.println("Handled request " + requestObj + Thread.currentThread().getName());
		});
		t.start();
	}
	public static void main(String[] args) {
		TaskManager taskManager = new TaskManager();
		for (int i = 0; i < 100; i++) {
			taskManager.executeTask("Request-" + i);
		}
	}
}
