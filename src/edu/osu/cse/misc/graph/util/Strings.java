package edu.osu.cse.misc.graph.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Strings {

	/**
	 * Finds the number of occurrences of a given char in a String.
	 * 
	 * @param s A String
	 * @param c A char
	 * @return A count of occurrences of a given char in the provided String.
	 */
	public static int instancesOf(String s, char c) {
		int count = 0;
		for (char ch : s.toCharArray()) {
			if (ch == c) {
				count++;
			}
		}
		return count;
	}

	public static int numberOfMatches(String str, Pattern pattern) {
		Matcher m = pattern.matcher(str);
		m.reset();
		int count = 0;
		while (m.find()) {
			count++;
		}
		return count;
	}

	/**
	 * Finds all matches from a Pattern in the given String and returns them as an array of Strings.
	 * 
	 * @param str A String to check
	 * @param pattern A Pattern to utilize
	 * @return An array of Strings containing the matches based on the provided parameters. An empty array is returned if no matches are found.
	 */
	public static String[] matchesToArray(String str, Pattern pattern) {
		Matcher m = pattern.matcher(str);
		ArrayList<String> matches = new ArrayList<>();
		while (m.find()) {
			matches.add(m.group());
		}
		return matches.toArray(new String[matches.size()]);
	}

	/**
	 * Finds the first match in the provided String from the given Pattern.
	 * 
	 * @param str A String to check
	 * @param pattern A Pattern to utilize
	 * @return A String of the first match bassed on the provided Pattern. <tt>null</tt> if no match is found.
	 */
	public static String firstMatch(String str, Pattern pattern) {
		Matcher m = pattern.matcher(str);
		if (m.find()) {
			return m.group();
		}
		return null;
	}

	/**
	 * Converts a number into a String denoting its sign.
	 * <br />For example, 5.5 would return as +5.5 and -5.5 would return as -5.5
	 * 
	 * @param value A floating point number.
	 * @return The provided value with its sign prefixed.
	 */
	public static String toStringWithSign(double value) {
		return (Math.signum(value) >= 0 ? "+" : "-") + Math.abs(value);
	}

	/**
	 * Checks a formula for certain aspects that are unsuitable for evaluation.<pre>
	 * Currently checks for:
	 *  -Implicit multiplication in scaler terms, such as "3x"
	 * </pre>
	 * 
	 * @param formula A formula to check.
	 * @return The original formula in an acceptable form. <tt>null</tt> is returned if an issue 
	 * is found with the provided formula that would cause the Pattern checks to go awry.
	 */
	public static String checkFormula(String formula) {
		int failsafe = 0;
		for (char c = 'A'; c <= 'z'; c++) {
			if (Character.isAlphabetic(c)) {
				Matcher m = Regex.patternForScaledTerm(c, false).matcher(formula);
				while (m.find()) {
					failsafe++;
					if (failsafe > Math.pow(formula.length(), 2)) {
						return null;
					}
					int index = m.start();
					String subLeft = formula.substring(0, index + 1);
					String subRight = formula.substring(index + 1, formula.length());
					formula = subLeft + "*" + subRight;
					m.reset(formula);
				}
				failsafe = 0;
			}
		}
		return formula;
	}

}
