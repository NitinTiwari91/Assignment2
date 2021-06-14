package com.twitterhashtag;

import com.twitterhashtag.service.ITweetsProcessor;
import com.twitterhashtag.service.ProcessTweets;

public class BreakHashTag {

	public static void main(String[] args) {
		ITweetsProcessor breakHashtag = new ProcessTweets();
		breakHashtag.processTweetsAndStoreInOutputFile("resources/as_tweets.txt", "resources/output.txt");
	}
}
