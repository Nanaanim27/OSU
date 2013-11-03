package edu.osu.cse._2221.project8;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Utilities that could be used with RSA cryptosystems.
 * 
 * @author Put your name here
 * 
 */
public final class CryptoUtilities {

	/**
	 * Private constructor so this utility class cannot be instantiated.
	 */
	private CryptoUtilities() {
	}

	/**
	 * Useful constant, not a magic number: 3.
	 */
	private static final int THREE = 3;

	/**
	 * Pseudo-random number generator.
	 */
	private static Random generator = new Random1L();

	/**
	 * Returns a random number uniformly distributed in the interval [0, n].
	 * 
	 * @param n
	 *            top end of interval
	 * @return random number in interval
	 * @requires <pre>
	 * {@code n > 0}
	 * </pre>
	 * @ensures <pre>
	 * {@code randomNumber = [a random number uniformly distributed in [0, n]]}
	 * </pre>
	 */
	public static NaturalNumber randomNumber(NaturalNumber n) {
		assert !n.isZero() : "Violation of: n > 0";
		final int base = 10;
		int d = n.divideBy10();
		if (n.isZero()) {
			/*
			 * Incoming n has only one digit and it is d, so generate a random
			 * number uniformly distributed in [0, d], restore n, and return
			 */
			int result = (int) ((d + 1) * generator.nextDouble());
			n.multiplyBy10(d);
			return new NaturalNumber2(result);
		}
		/*
		 * Incoming n has more than one digit, so generate a random number
		 * (NaturalNumber) uniformly distributed in [0, n], and another (int)
		 * uniformly distributed in [0, 9] (i.e., a random digit)
		 */
		NaturalNumber result = randomNumber(n);
		int lastDigit = (int) (base * generator.nextDouble());
		if (result.equals(n) && (lastDigit >= d + 1)) {
			/*
			 * In this case, we need to try again because generated number would
			 * be greater than n; the recursive call's argument is not "smaller"
			 * than the incoming value of n, but this recursive call has no more
			 * than a 90% chance of being made (and for large n, far less than
			 * that), so the probability of termination is 1
			 */
			n.multiplyBy10(d);
			return randomNumber(n);
		}
		/*
		 * Put together full random number, restore n, and return
		 */
		result.multiplyBy10(lastDigit);
		n.multiplyBy10(d);
		return result;
	}

	/**
	 * Finds the greatest common divisor of n and m.
	 * 
	 * @param n
	 *            one number
	 * @param m
	 *            the other number
	 * @updates n
	 * @clears m
	 * @ensures <pre>
	 * {@code n = [greatest common divisor of #n and #m]}
	 * </pre>
	 */
	public static void reduceToGCD(NaturalNumber n, NaturalNumber m) {
		if (n.compareTo(m) == 0) {
			//At this point, we have found the GCD, and n == GCD.
			m.clear();
		}
		else {
			int comparison = n.compareTo(m);
			(comparison > 0 ? n : m).subtract(comparison > 0 ? m : n);
			reduceToGCD(n, m);
		}
	}

	/**
	 * Reports whether n is even.
	 * 
	 * @param n
	 *            the number to be checked
	 * @return true iff n is even
	 * @ensures <pre>
	 * {@code isEven = (n mod 2 = 0)}
	 * </pre>
	 */
	public static boolean isEven(NaturalNumber n) {
		return n.newInstance().divideBy10() % 2 == 0;
	}

	/**
	 * Updates n to its p-th power modulo m.
	 * 
	 * @param n
	 *            number to be raised to a power
	 * @param p
	 *            the power
	 * @param m
	 *            the modulus
	 * @updates n
	 * @requires <pre>
	 * {@code m > 1}
	 * </pre>
	 * @ensures <pre>
	 * {@code n = #n ^ (p) mod m}
	 * </pre>
	 */
	public static void powerMod(NaturalNumber n, NaturalNumber p, NaturalNumber m) {

		/*
		 * Use the fast-powering algorithm as previously discussed in class,
		 * with the additional feature that every multiplication is followed
		 * immediately by "reducing the result modulo m"
		 */

		assert m.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: m > 1";

		if (p.compareTo(new NaturalNumber2(0)) == 0) { //Anything to the power of 0 is 1
			n.setFromInt(1);
		}
		else {
			NaturalNumber nCopy = n.newInstance(), pCopy = p.newInstance();
			if (isEven(n)) {
				pCopy.divide(new NaturalNumber2(2));
				powerMod(nCopy, pCopy, m);
				nCopy.multiply(nCopy);
			}
			else {
				pCopy.decrement();
				powerMod(nCopy, pCopy, m);
				nCopy.multiply(n);
			}
			
			n.transferFrom(nCopy.divide(m));
		}
	}

	/**
	 * Reports whether w is a "witness" that n is composite, in the sense that
	 * either it is a square root of 1 (mod n), or it fails to satisfy the
	 * criterion for primality from Fermat's theorem.
	 * 
	 * @param w
	 *            witness candidate
	 * @param n
	 *            number being checked
	 * @return true iff w is a "witness" that n is composite
	 * @requires <pre>
	 * {@code n > 2  and  1 < w < n - 1}
	 * </pre>
	 * @ensures <pre>
	 * {@code isWitnessToCompositeness =
	 *     (w ^ 2 mod n = 1)  or  (w ^ (n-1) mod n /= 1)}
	 * </pre>
	 */
	public static boolean isWitnessToCompositeness(NaturalNumber w, NaturalNumber n) {
		assert n.compareTo(new NaturalNumber2(2)) > 0 : "Violation of: n > 2";
		assert (new NaturalNumber2(1)).compareTo(w) < 0 : "Violation of: 1 < w";
		n.decrement();
		assert w.compareTo(n) < 0 : "Violation of: w < n - 1";
		n.increment();

		NaturalNumber temp = new NaturalNumber2(w);
		
		powerMod(w, new NaturalNumber2(2), new NaturalNumber2(n));
		if (w.canConvertToInt() && w.toInt() == 1)
			return true;
		w.copyFrom(temp);
		
		NaturalNumber nCopy = new NaturalNumber2(n);
		nCopy.decrement();
		powerMod(w, new NaturalNumber2(nCopy), n);
		
		return w.canConvertToInt() && w.toInt() != 1;
	}

	/**
	 * Reports whether n is a prime; may be wrong with "low" probability.
	 * 
	 * @param n
	 *            number to be checked
	 * @return true means n is very likely prime; false means n is definitely
	 *         composite
	 * @requires <pre>
	 * {@code n > 1}
	 * </pre>
	 * @ensures <pre>
	 * {@code isPrime1 = [n is a prime number, with small probability of error
	 *         if it is reported to be prime, and no chance of error if it is
	 *         reported to be composite]}
	 * </pre>
	 */
	public static boolean isPrime1(NaturalNumber n) {
		assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";
		/*
		 * 2 and 3 are primes; other evens are composite; rest of the code works
		 * for odd n >= 5
		 */
		if (n.compareTo(new NaturalNumber2(THREE)) <= 0) {
			return true;
		}
		if (isEven(n)) {
			return false;
		}
		/*
		 * Simply check whether 2 is a witness that n is composite (which works
		 * surprisingly well :-)
		 */
		return !isWitnessToCompositeness(new NaturalNumber2(2), n);
	}

	/**
	 * Reports whether n is a prime; may be wrong with "low" probability.
	 * 
	 * @param n
	 *            number to be checked
	 * @return true means n is very likely prime; false means n is definitely
	 *         composite
	 * @requires <pre>
	 * {@code n > 1}
	 * </pre>
	 * @ensures <pre>
	 * {@code isPrime1 = [n is a prime number, with small probability of error
	 *         if it is reported to be prime, and no chance of error if it is
	 *         reported to be composite]}
	 * </pre>
	 */
	public static boolean isPrime2(NaturalNumber n) {
		assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";

		/*
		 * Use the ability to generate random numbers (provided by the
		 * randomNumber method above) to generate several witness candidates --
		 * say, 10 to 50 candidates -- guessing that n is prime only if none of
		 * these candidates is a witness to n being composite (based on fact #3
		 * as described in the project description); use the code for isPrime1
		 * as a guide for how to do this, and pay attention to the requires
		 * clause of isWitnessToCompositeness
		 */

		final int NUM_CANDIDATES = 50;
		for (int i = 0; i < NUM_CANDIDATES; i++) {
			if (isWitnessToCompositeness(randomNumber(n), n)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Generates a likely prime number at least as large as some given number.
	 * 
	 * @param n
	 *            minimum value of likely prime
	 * @updates n
	 * @requires <pre>
	 * {@code n > 1}
	 * </pre>
	 * @ensures <pre>
	 * {@code n >= #n  and  [n is very likely a prime number]}
	 * </pre>
	 */
	public static void generateNextLikelyPrime(NaturalNumber n) {
		assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";
		
		NaturalNumber two = new NaturalNumber2(2);
		while(true) {
			int remainder;
			if ((remainder = n.divideBy10()) % 2 == 0) {
				n.multiplyBy10(remainder);
				n.increment();
			}
			System.out.println("Value of n: "  + n);
			n.add(two);
			if (isPrime1(n)) {
				break;
			}
		}
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		SimpleReader in = new SimpleReader1L();
		SimpleWriter out = new SimpleWriter1L();


		//Sanity check of randomNumber method -- just so everyone can see how
		//it might be "tested"

		final int testValue = 17;
		final int testSamples = 100000;
		NaturalNumber test = new NaturalNumber2(testValue);
		int[] count = new int[testValue + 1];
		for (int i = 0; i < count.length; i++) {
			count[i] = 0;
		}
		for (int i = 0; i < testSamples; i++) {
			NaturalNumber rn = randomNumber(test);
			assert rn.compareTo(test) <= 0 : "Help!";
			count[rn.toInt()]++;
		}
		for (int i = 0; i < count.length; i++) {
			out.println("count[" + i + "] = " + count[i]);
		}
		out.println("  expected value = " + (double) testSamples
				/ (double) (testValue + 1));


		//Check user-supplied numbers for primality, and if a number is not
		//prime, find the next likely prime after it

		while (true) {
			out.print("n = ");
			NaturalNumber n = new NaturalNumber2(in.nextLine());
			if (n.compareTo(new NaturalNumber2(2)) < 0) {
				out.println("Bye!");
				break;
			} else {
				if (isPrime1(n)) {
					out.println(n + " is probably a prime number"
							+ " according to isPrime1.");
				} else {
					out.println(n + " is a composite number"
							+ " according to isPrime1.");
				}
				if (isPrime2(n)) {
					out.println(n + " is probably a prime number"
							+ " according to isPrime2.");
				} else {
					out.println(n + " is a composite number"
							+ " according to isPrime2.");
					generateNextLikelyPrime(n);
					out.println("  next likely prime is " + n);
				}
			}
		}


		//Close input and output streams

		in.close();
		out.close();
	}
}