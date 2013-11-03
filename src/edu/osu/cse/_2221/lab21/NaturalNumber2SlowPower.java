package edu.osu.cse._2221.lab21;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stopwatch.Stopwatch;
import components.stopwatch.Stopwatch1;

/**
 * Extension of {@code NaturalNumber1L} with main method to time the performance
 * of the inherited fast power method.
 * 
 * @author Paolo Bucci
 * 
 */
public final class NaturalNumber2SlowPower extends NaturalNumber2 {

    /**
     * Default constructor.
     */
    public NaturalNumber2SlowPower() {
    }

    /**
     * Constructor from {@code int}.
     * 
     * @param i
     *            {@code int} to initialize from
     */
    public NaturalNumber2SlowPower(int i) {
        super(i);
    }

    /**
     * Constructor from {@code String}.
     * 
     * @param s
     *            {@code String} to initialize from
     */
    public NaturalNumber2SlowPower(String s) {
        super(s);
    }

    /**
     * Constructor from {@code NaturalNumber}.
     * 
     * @param n
     *            {@code NaturalNumber} to initialize from
     */
    public NaturalNumber2SlowPower(NaturalNumber n) {
        super(n);
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
        Stopwatch timer = new Stopwatch1();

        final int maxP = 1000;
        final int deltaP = 50;

        out.print("Enter a natural number: ");
        String input = in.nextLine();
        NaturalNumber n = new NaturalNumber2SlowPower(input);
        NaturalNumber nCopy = n.newInstance();
        out.println("p,t");
        for (int p = 0; p <= maxP; p += deltaP) {
            nCopy.copyFrom(n);
            timer.clear(); // reset the stopwatch
            timer.start(); // start the stopwatch to keep track of time
            nCopy.power(p); // operation being timed
            timer.stop(); // stop the stopwatch to record elapsed time
            out.println(p + "," + timer.elapsed() + " (" + nCopy + ")");
        }

        in.close();
        out.close();
    }
}