package com.khang.tuts.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MyRecursiveAction extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7233950500270227277L;
	private long workLoad = 0;

	public MyRecursiveAction(long workLoad) {
		this.workLoad = workLoad;
	}

	@Override
	protected void compute() {
		// if work is above threshold, break tasks up into smaller tasks
		if (this.workLoad > 16) {
			System.out.println("Splitting workLoad : " + this.workLoad);

			List<MyRecursiveAction> subtasks = new ArrayList<MyRecursiveAction>();
			subtasks.addAll(createSubtasks());
			/*for (RecursiveAction subtask : subtasks) {
				subtask.fork();*/
				invokeAll(subtasks);
			/*}
			for (RecursiveAction subtask : subtasks) {
				subtask.join();
			}*/
			
		} else {
			System.out.println(String.format("Doing workLoad myself: %s on %s", this.workLoad, Thread.currentThread().getName()));
		}
	}

	private List<MyRecursiveAction> createSubtasks() {
		List<MyRecursiveAction> subtasks = new ArrayList<MyRecursiveAction>();

		MyRecursiveAction subtask1 = new MyRecursiveAction(this.workLoad / 2);
		MyRecursiveAction subtask2 = new MyRecursiveAction(this.workLoad / 2);

		subtasks.add(subtask1);
		subtasks.add(subtask2);

		return subtasks;
	}

	public static void main(String[] args) throws InterruptedException {
		ForkJoinPool forkJoinPool = new ForkJoinPool(4);
		MyRecursiveAction myRecursiveAction = new MyRecursiveAction(2000);

		forkJoinPool.invoke(myRecursiveAction);
		Thread.sleep(5000);
		System.out.println("Completed");

	}

}
