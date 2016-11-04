package com.khang.tuts.concurrency;

import java.util.concurrent.RecursiveTask;

public class DocumentSearchTask extends RecursiveTask<Long> {
	private final Document document;
	private final String searchedWord;

	DocumentSearchTask(Document document, String searchedWord) {
		super();
		this.document = document;
		this.searchedWord = searchedWord;
	}

	@Override
	protected Long compute() {
		//System.out.println("Running on " + Thread.currentThread().getName());
		return occurrencesCount(document, searchedWord);
	}

	String[] wordsIn(String line) {
		return line.trim().split("(\\s|\\p{Punct})+");
	}

	Long occurrencesCount(Document document, String searchedWord) {
		long count = 0;
		for (String line : document.getLines()) {
			for (String word : wordsIn(line)) {
				if (searchedWord.equals(word)) {
					count = count + 1;
				}
			}
		}
		return count;
	}

}