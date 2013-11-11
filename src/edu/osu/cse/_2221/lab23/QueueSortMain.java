package edu.osu.cse._2221.lab23;

import java.util.Comparator;

import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program to sort lines from an input file in lexicographic order by using
 * selection sort on {@code Queue<String>}.
 * 
 * @author Paolo Bucci
 */
public final class QueueSortMain {

	/**
	 * Compare {@code String}s in lexicographic order.
	 */
	private static class StringLT implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	}

	/**
	 * Default constructor--private to prevent instantiation.
	 */
	private QueueSortMain() {
		// no code needed here
	}

	/**
	 * @mathdefinitions <pre>
	 * {@code STRING_OF_LINES(s: string of character): string of string of character
	 *  satisfies
	 *   if s = empty_string
	 *    then STRING_OF_LINES(s) = empty_string
	 *    else if "\n" is not substring of s
	 *     then STRING_OF_LINES(s) = <s>
	 *     else there exists a, b: string of character
	 *           (s = a * "\n" * b  and
	 *            "\n" is not substring of a  and
	 *            STRING_OF_LINES(s) = <a> * STRING_OF_LINES(b))
	 * }
	 * </pre>
	 */

	/**
	 * Gets one line at a time from {@code in} until end of input, and puts them
	 * into the queue {@code q}.
	 * 
	 * @param in
	 *            the source of the lines to be input
	 * @param q
	 *            the queue of lines that are read
	 * @updates {@code in}
	 * @replaces {@code q}
	 * @requires <pre>
	 * {@code in.is_open}
	 * </pre>
	 * @ensures <pre>
	 * {@code in.is_open  and 
	 *  in.ext_name = #in.ext_name  and
	 *  in.content = ""  and
	 *  lines = entries(STRING_OF_LINES(#in.content))
	 * }
	 * </pre>
	 */
	private static void getLinesFromInput(SimpleReader in, Queue<String> q) {
		assert in != null : "Violation of: in is not null";
		assert q != null : "Violation of: q is not null";
		assert in.isOpen() : "Violation of: in.is_open";

		q.clear();
		while (!in.atEOS()) {
			String str = in.nextLine();
			q.enqueue(str);
		}
	}

	/**
	 * Puts lines from {@code q}, one line at a time, onto {@code out}.
	 * 
	 * @param out
	 *            the stream to receive output
	 * @param q
	 *            the queue of lines that are output
	 * @updates {@code out}
	 * @requires <pre>
	 * {@code out.is_open}
	 * </pre>
	 * @ensures <pre>
	 * {@code out.is_open  and 
	 *  out.ext_name = #out.ext_name  and
	 *  out.content = #out.content *
	 *   [the entries of q, in some order, with appropriate mark-up]
	 * }
	 * </pre>
	 */
	private static void putLinesToOutput(SimpleWriter out, Queue<String> q) {
		assert out != null : "Violation of: out is not null";
		assert q != null : "Violation of: q is not null";
		assert out.isOpen() : "Violation of: out.is_open";

		out.println();
		out.println("--- Start of Queue<String> output ---");
		for (String str : q) {
			out.println(str);
		}
		out.println("--- End of Queue<String> output ---");
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 *            the command line arguments; unused here
	 */
	public static void main(String[] args) {
		SimpleReader in = new SimpleReader1L();
		SimpleWriter out = new SimpleWriter1L();

		/*
		 * Get input file name and open input stream
		 */
		out.print("Enter an input file name: ");
		String fileName = in.nextLine();
		SimpleReader file = new SimpleReader1L(fileName);

		/*
		 * Get lines from input and output them, unsorted
		 */
		Queue<String> q = new Queue1LSort1();
		getLinesFromInput(file, q);
		putLinesToOutput(out, q);

		/*
		 * Sort lines into non-decreasing lexicographic order
		 */
		Comparator<String> cs = new StringLT();
		q.sort(cs);

		/*
		 * Output lines in sorted order
		 */
		putLinesToOutput(out, q);

		/*
		 * Reverse the order of the comparator
		 */

		Comparator<String> cs2 = new StringLT() {
			@Override
			public int compare(String o1, String o2) {
				return super.compare(o2, o1);
			}
		};
		q.sort(cs2);
		
		/*
		 * Output lines in sorted order
		 */
		putLinesToOutput(out, q);

		in.close();
		out.close();
	}

}