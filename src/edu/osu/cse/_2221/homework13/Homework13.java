package edu.osu.cse._2221.homework13;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.utilities.FormatChecker;
import components.xmltree.XMLTree;

public class Homework13 {

	public static void main(String[] args) {
		NaturalNumber n1 = new NaturalNumber1L(1332);
		NaturalNumber n2 = new NaturalNumber1L(1332);
		
		System.out.println(productOfDigits1(n1));
		System.out.println(productOfDigits2(n2));
		
		System.out.println("\n" + n1);
		System.out.println(n2);
	}
	
	/**
	 * Returns the product of the digits of {@code n}.
	 *
	 * @param n
	 *            {@code NaturalNumber} whose digits to multiply
	 * @return the product of the digits of {@code n}
	 * @clears n
	 * @ensures <pre>
	 * {@code productOfDigits1 = [product of the digits of n]}
	 * </pre>
	 */
	private static NaturalNumber productOfDigits1(NaturalNumber n) {
		String num = n.toString();
		NaturalNumber value = new NaturalNumber1L(1);
		String charString;
		for (char c : num.toCharArray()) {
			if (FormatChecker.canParseInt((charString = String.valueOf(c)))) {
				value.multiply(new NaturalNumber1L(Integer.parseInt(charString)));
			}
		}
		n.clear();
		return value;
	}
	
	/**
	 * Returns the product of the digits of {@code n}.
	 *
	 * @param n
	 *            {@code NaturalNumber} whose digits to multiply
	 * @return the product of the digits of {@code n}
	 * @ensures <pre>
	 * {@code productOfDigits2 = [product of the digits of n]}
	 * </pre>
	 */
	private static NaturalNumber productOfDigits2(NaturalNumber n) {
		String num = n.toString();
		NaturalNumber value = new NaturalNumber1L(1);
		String charString;
		for (char c : num.toCharArray()) {
			if (FormatChecker.canParseInt((charString = String.valueOf(c)))) {
				value.multiply(new NaturalNumber1L(Integer.parseInt(charString)));
			}
		}
		return value;
	}
	
	/**
	 * Reports the value of {@code n} as an {@code int}, when {@code n} is
	 * small enough.
	 * 
	 * @param n
	 *            the given {@code NaturalNumber}
	 * @return the value
	 * @requires <pre>
	 * {@code n <= Integer.MAX_VALUE}
	 * </pre>
	 * @ensures <pre>
	 * {@code toInt = n}
	 * </pre>
	 */
	private static int toInt(NaturalNumber n) {
		assert n.canConvertToInt() : "Violation of: n <= Integer.MAX_VALUE";
		return n.toInt();
	}
	
	/**
	 * Reports whether the given tag appears in the given {@code XMLTree}.
	 * 
	 * @param xml
	 *            the {@code XMLTree}
	 * @param tag
	 *            the tag name
	 * @return true if the given tag appears in the given {@code XMLTree},
	 *         false otherwise
	 * @ensures <pre>
	 * {@code findTag =
	 *    [true if the given tag appears in the given {@code XMLTree}, false otherwise]}
	 * </pre>
	 */
	private static boolean findTag(XMLTree xml, String tag) {
		if (xml.isTag() && xml.label().equals(tag)) {
			return true;
		}
		for (int i = 0; i < xml.numberOfChildren(); i++) {
			XMLTree child = xml.child(i);
			if (findTag(child, tag)) {
				return true;
			}
		}
		return false;
	}
	
}
