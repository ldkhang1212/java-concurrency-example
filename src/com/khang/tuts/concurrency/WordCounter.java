package com.khang.tuts.concurrency;

import java.io.File;
import java.io.IOException;

public class WordCounter {
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
	
	Long occurrencesCount(Folder folder, String searchedWord) {
		long count = 0;
		for (Document doc : folder.getDocuments()) {
			count += occurrencesCount(doc, searchedWord);
		}
		for (Folder subFolder : folder.getSubFolders()) {
			count += occurrencesCount(subFolder, searchedWord);
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		Folder folder = Folder.fromDirectory(new File("//home//ldkhang//data"));
		long start = System.currentTimeMillis();
		System.out.println(new WordCounter().occurrencesCount(folder, "Unsigned32"));
		long end = System.currentTimeMillis();
		System.out.println("Completed after " + (end - start) / 1000);
	}
}
