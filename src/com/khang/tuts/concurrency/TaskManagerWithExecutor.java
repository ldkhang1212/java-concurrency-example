package com.khang.tuts.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskManagerWithExecutor {
	int  corePoolSize  =    5;
	int  maxPoolSize   =   10;
	long keepAliveTime = 500;
	ExecutorService executor =  new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime,
			TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
	public void executeTask(String requestObj) {
		executor.execute(() -> {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(String.format("Thread %s handled request %s", Thread.currentThread().getName(), requestObj));
		});
	}
	
	public void destroy() {
		this.executor.shutdown();
	}

	public static void main(String[] args) {
		TaskManagerWithExecutor taskManager = new TaskManagerWithExecutor();
		for (int i = 0; i < 100; i++) {
			taskManager.executeTask("Request-" + i);
		}
		System.out.println("Completed");
		taskManager.destroy();
	}
}
