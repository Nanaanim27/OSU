package edu.osu.cse._2221.homework12;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

public class Homework12 {

	/**
	 * Returns the number of digits of {@code n}.
	 * 
	 * @param n
	 *            {@code NaturalNumber} whose digits to count
	 * @return the number of digits of {@code n}
	 * @ensures <pre>
	 * {@code numberOfDigits = [number of digits of n]}
	 * </pre>
	 */
	private static int numberOfDigits(NaturalNumber n) {
		return numberOfDigitsHelper(n, 0);
	}
	
	/**
	 * A helper method for {@link #numberOfDigits(NaturalNumber)}}
	 */
	private static int numberOfDigitsHelper(NaturalNumber n, int count) {
		if (!n.isZero()) {
			count++;
			n.divideBy10();
			return numberOfDigitsHelper(n, count);
		}
		return count;
	}
	
	/**
	 * Returns the sum of the digits of {@code n}.
	 * 
	 * @param n
	 *            {@code NaturalNumber} whose digits to add
	 * @return the sum of the digits of {@code n}
	 * @ensures <pre>
	 * {@code sumOfDigits = [sum of the digits of n]}
	 * </pre>
	 */
	private static int sumOfDigits(NaturalNumber n) {
		return sumOfDigitsHelper(n, 0);
	}
	
	/**
	 * A helper method for {@link #sumOfDigits(NaturalNumber)}
	 */
	private static int sumOfDigitsHelper(NaturalNumber n, int sum) {
		if (!n.isZero()) {
			sum += n.divideBy10();
			return sumOfDigitsHelper(n, sum);
		}
		return sum;
	}
	
	/**
	 * Returns the sum of the digits of {@code n}.
	 * 
	 * @param n
	 *            {@code NaturalNumber} whose digits to add
	 * @return the sum of the digits of {@code n}
	 * @ensures <pre>
	 * {@code sumOfDigits = [sum of the digits of n]}
	 * </pre>
	 */
	private static NaturalNumber sumOfDigitsTwo(NaturalNumber n) {
		return sumOfDigitsTwoHelper(n, new NaturalNumber1L(0));
	}
	
	/**
	 * A helper method for {@link #sumOfDigitsTwo(NaturalNumber)}
	 */
	private static NaturalNumber sumOfDigitsTwoHelper(NaturalNumber n, NaturalNumber count) {
		if (!n.isZero()) {
			count.add(new NaturalNumber1L(n.divideBy10()));
			return sumOfDigitsTwoHelper(n, count);
		}
		return count;
	}
	
	/**
	 * Divides {@code n} by 2.
	 * 
	 * @param n
	 *            {@code NaturalNumber} to be divided
	 * @updates {@code n}
	 * @ensures <pre>
	 * {@code 2 * n <= #n < 2 * (n + 1)}
	 * </pre>
	 */
	private static void divideBy2(NaturalNumber n) {
		//meh
	}
	
	/**
	 * Checks whether a {@code String} is a palindrome.
	 * 
	 * @param s
	 *            {@code String} to be checked
	 * @return true if {@code s} is a palindrome, false otherwise
	 * @ensures <pre>
	 * {@code s = rev(s)}
	 * </pre>
	 */
	private static boolean isPalindrome(String s) {
		return isPalindromeHelper(s, 0);
	}
	
	/**
	 * Helper method for {@link #isPalindrome(String)}
	 */
	private static boolean isPalindromeHelper(String s, int index) {
		char start = s.charAt(index), end = s.charAt(s.length()-1-index);
		if (index >= s.length() / 2) {
			return true;
		}
		if (start != end) {
			return false;
		}
		return isPalindromeHelper(s, index+1);
	}
	
	static boolean isPrime(int num) {
		for (int i = 2; i < num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	
	
	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		for (int i : nums) 
			System.out.println(isPrime(i));
	}
	
}
