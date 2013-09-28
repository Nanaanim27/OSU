package edu.osu.cse._2221.lab11;

import java.util.LinkedList;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

public class Hailstone5 {

	public static void main(String[] args) {
		try (SimpleWriter out = new SimpleWriter1L();
				SimpleReader in = new SimpleReader1L()) {
			out.println("Hailstone 5");

			String cont = "y";
			int value;
			do {
				value = getPositiveInteger(in, out);
				LinkedList<NaturalNumber> list = generateSeries(new NaturalNumber1L(value), out, new LinkedList<NaturalNumber>());
				out.println("Series: " + list);
				out.println("\nContinue? (y/n)");
				cont = in.nextLine();
			} while (cont.equals("y"));
		}
	}

	/**
	 * Repeatedly asks the user for a positive integer until the user enters
	 * one. Returns the positive integer.
	 * 
	 * @param in
	 *            the input stream
	 * @param out
	 *            the output stream
	 * @return a positive integer entered by the user
	 */
	private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
		int value = -1;
		String valueS;
		do {
			out.print("Enter a positive integer: ");
			value = FormatChecker.canParseInt((valueS = in.nextLine())) ? Integer.parseInt(valueS) : -1;
		} while(value < 0);
		return value;
	}
	
	/**
	 * Generates and outputs the Hailstone series starting with the given
	 * {@code NaturalNumber}.
	 * 
	 * @param n
	 *            the starting natural number
	 * @param out
	 *            the output stream
	 * @updates {@code out.content}
	 * @requires <pre>
	 * {@code n > 0 and out.is_open}
	 * </pre>
	 * @ensures <pre>
	 * {@code out.content = #out.content * [the Hailstone series starting with n]}
	 * </pre>
	 */
	private static LinkedList<NaturalNumber> generateSeries(NaturalNumber num, SimpleWriter out, LinkedList<NaturalNumber> numbers) {

		numbers.add(new NaturalNumber1L(num));
		if (num.canConvertToInt() && num.toInt() == 1) {
			return numbers;
		}

		int lastDigit = new NaturalNumber1L(num).divideBy10();
		if (lastDigit % 2 == 0) {
			num.divide(new NaturalNumber1L(2));
		}
		else {
			num.multiply(new NaturalNumber1L(3));
			num.increment();
		}

		return generateSeries(num, out, numbers);
	}

}
