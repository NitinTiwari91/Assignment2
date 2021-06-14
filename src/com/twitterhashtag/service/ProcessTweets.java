package com.twitterhashtag.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;

import com.twitterhashtag.utils.Constants;
import com.twitterhashtag.utils.Utils;

public class ProcessTweets implements ITweetsProcessor {

//	private HashSet<String> dictionarySet;
//
//	@Override
//	public HashSet<String> loadDictionary() {
//		if (dictionarySet == null) {
//			String fileName = "resources/dictionary.txt";
//			try (BufferedReader br = new BufferedReader(
//					new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));) {
//
//				String line;
//				dictionarySet = new HashSet<>();
//				while ((line = br.readLine()) != null) {
//					dictionarySet.add(line);
//				}
//			} catch (FileNotFoundException e) {
//				System.out.println("Dictionary File not found");
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return dictionarySet;
//	}

	@Override
	public void processTweetsAndStoreInOutputFile(String tweetsFilePath, String outputFilePath) {
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(tweetsFilePath), StandardCharsets.UTF_8));
				PrintWriter printWriter = new PrintWriter(new FileWriter(outputFilePath));) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains(Constants.HASH)) {
					Matcher hashTagMatcher = Utils.getHashTagMatcher(line);
					while (hashTagMatcher.find()) {
						/******ONLY HASHTAG VALUE IS AVAILABLE HERE*******/
						String processedHashTag = getProcessedHashtag(hashTagMatcher.group());
						line = line.replace(hashTagMatcher.group(), processedHashTag);
						System.out.println("Original Hashtag::" + hashTagMatcher.group() + " :: Processed To---> " + processedHashTag);
					}
				}
				printWriter.println(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Tweets File not found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Process Completed please refresh resource folder and check data in output file");
	}

	/**
	 * This method will convert original hashtag into splited form
	 * such as '#AmericanSniper' will be processed to 'American Sniper'
	 */
	private String getProcessedHashtag(String hashTagString) {
		String processedHashTag = "";
		for(int i = 0; i < hashTagString.length(); i++) {
		   Character ch = hashTagString.charAt(i);
		     if(Character.isUpperCase(ch))
		    	 processedHashTag += " " + ch;
		     else
		    	 processedHashTag += ch;
		}
		return processedHashTag.replace(Constants.HASH, "").trim();
	}
}
