package edu.osu.cse._2221.lab13;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Compute integer power with interval halving and test it.
 * 
 * @author Put your name here
 * 
 */
public final class IntegerRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private IntegerRoot() {
    }

    /**
     * Returns {@code n} to the power {@code p}.
     * 
     * @param n
     *            the number to which we want to apply the power
     * @param p
     *            the power
     * @return the number to the power
     * @requires <pre>
     * {@code Integer.MIN_VALUE <= n ^ (p) <= Integer.MAX_VALUE and p >= 0}
     * </pre>
     * @ensures <pre>
     * {@code power = n ^ (p)}
     * </pre>
     */
    private static int power(int n, int p) {
        int result = 1, count = 0;
        while (count < p) {
            result *= n;
            count++;
        }
        return result;
    }

    /**
     * Returns the {@code r}-th root of {@code n}.
     * 
     * @param n
     *            the number to which we want to apply the root
     * @param r
     *            the root
     * @return the root of the number
     * @requires <pre>
     * {@code n >= 0 and r > 0}
     * </pre>
     * @ensures <pre>
     * {@code root ^ (r) <= n < (root + 1) ^ (r)}
     * </pre>
     */
    private static int root(int n, int r) {

        int upper = n;
        int lower = 1;
        int guess;
        
        while ((lower =  power(r, (guess = (upper+lower) / 2 ))) <= n && (upper =  power(r+1, (guess))) > n) {
        }
        return guess;

    }

    /**
     * Main method.
     * 
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final int[] numbers = { 0, 0, 0, 1, 1, 1, 82, 82, 82, 82, 82, 3, 9, 27,
                81, 243 };
        final int[] roots = { 1, 2, 5, 1, 2, 17, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5 };
        final int[] results = { 0, 0, 0, 1, 1, 1, 82, 9, 4, 3, 2, 3, 3, 3, 3, 3 };

        for (int i = 0; i < numbers.length; i++) {
            int x = root(numbers[i], roots[i]);
            if (x == results[i]) {
                out.println("Test passed: root(" + numbers[i] + ", " + roots[i]
                        + ") = " + results[i]);
            } else {
                out.println("*** Test failed: root(" + numbers[i] + ", "
                        + roots[i] + ") expected <" + results[i]
                        + "> but was <" + x + ">");
            }
        }

        out.close();
    }

}