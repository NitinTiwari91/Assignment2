package com.twitterhashtag.service;

public interface ITweetsProcessor {

//	HashSet<String> loadDictionary();
	void processTweetsAndStoreInOutputFile(String tweetsFilePath, String outputFilePath);
}
