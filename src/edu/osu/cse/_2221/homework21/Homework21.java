package edu.osu.cse._2221.homework21;

import components.set.Set;
import components.set.Set1L;

public class Homework21 {

	//Removed documentation to save paper/ink
	/**
	 * Sets the values of {@code strSet} to the set of characters in {@code str}
	 * By default, and simply speaking logically, there will be no duplicates. 
	 */
	private static void generateElements(String str, Set<Character> strSet) {
		if (strSet == null || str == null || strSet.size() == 0)
			return;
		
		Set<Character> chars = new Set1L<>();
		for (char c : str.toCharArray()) {
			chars.add(c);
		}
		strSet.transferFrom(chars);
	}
	
	//Removed documentation to save paper/ink
	/** 
	 * Substring from {@code position} to first char 
	 * in string passed {@code position} that is not in {@code separators} 
	 */ 
	private static String nextWordOrSeparator(String text, int position,
	        Set<Character> separators) {
		assert position >= 0 && position < text.length(): "Violation of: position is within the bounds of text";
		String sub = "";
		for (char c : text.substring(position, text.length()).toCharArray()) {
			if (!separators.contains(c))
				sub += c;
			else
				break;
		}
		return sub;
	}
	
}
