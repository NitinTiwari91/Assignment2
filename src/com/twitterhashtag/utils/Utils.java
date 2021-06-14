package com.twitterhashtag.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	public static Matcher getHashTagMatcher(String input) {
		Pattern hashTagPattern = Pattern.compile(Constants.HASHTAG_PATTERN);
		Matcher matcher = hashTagPattern.matcher(input);
		return matcher;
	}
}
