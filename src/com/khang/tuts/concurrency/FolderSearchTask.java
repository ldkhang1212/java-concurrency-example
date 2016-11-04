package com.khang.tuts.concurrency;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FolderSearchTask extends RecursiveTask<Long> {
    private final Folder folder;
    private final String searchedWord;
    
    FolderSearchTask(Folder folder, String searchedWord) {
        super();
        this.folder = folder;
        this.searchedWord = searchedWord;
    }
    
    @Override
    protected Long compute() {
        long count = 0L;
        List<RecursiveTask<Long>> forks = new LinkedList<>();
        for (Folder subFolder : folder.getSubFolders()) {
            FolderSearchTask task = new FolderSearchTask(subFolder, searchedWord);
            forks.add(task);
            task.fork();
        }
        for (Document document : folder.getDocuments()) {
            DocumentSearchTask task = new DocumentSearchTask(document, searchedWord);
            forks.add(task);
            task.fork();
        }
        for (RecursiveTask<Long> task : forks) {
            count = count + task.join();
        }
        return count;
    }
    
    public static void main(String[] args) throws IOException {
    	Folder folder = Folder.fromDirectory(new File("//home//ldkhang//data"));
		long start = System.currentTimeMillis();
    	ForkJoinPool forkJoinPool = new ForkJoinPool();
    	long count = forkJoinPool.invoke(new FolderSearchTask(folder, "Unsigned32"));
    	System.out.println(count);
    	long end = System.currentTimeMillis();
    	System.out.println("Completed after " + (end - start) / 1000);
	}
}