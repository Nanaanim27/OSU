package edu.osu.cse._2221.homework15;

import java.util.Arrays;
import java.util.Iterator;

import components.queue.Queue;
import components.queue.Queue2;

public class Homework15 {

	/**
	 * Reports the smallest integer in the given {@code Queue<Integer>}.
	 * 
	 * @param q
	 *            the queue of integer
	 * @return the smallest integer in the given queue
	 * @requires <pre>
	 * {@code q /= empty_string}
	 * </pre>
	 * @ensures <pre>
	 * {@code min is in entries(q) and
	 *  for all x: integer
	 *      where (x is in entries(q))
	 *    (min <= x)}
	 * </pre>
	 */
	private static int min(Queue<Integer> q) {
		assert q.length() != 0: "Violation of: \"Queue is not empty\"";
		int min = q.front();
		for (int i : q) {
			if (i < min)
				min = i;
		}
		return min;
	}

	/**
	 * Reports an array of two {@code int}s with the smallest and the
	 * largest integer in the given {@code Queue<Integer>}.
	 * 
	 * @param q
	 *            the queue of integer
	 * @return an array of two {@code int}s with the smallest and the
	 *         largest integer in the given queue
	 * @requires <pre>
	 * {@code q /= empty_string}
	 * </pre>
	 * @ensures <pre>
	 * {@code { minAndMax[0], minAndMax[1] } is subset of entries(q) and
	 *  for all x: integer
	 *      where (x in in entries(q))
	 *    (minAndMax[0] <= x <= minAndMax[1])}
	 * </pre>
	 */
	private static int[] minAndMax(Queue<Integer> q) {
		assert q.length() != 0: "Violation of: \"Queue is not empty\"";
		int min, max;
		min = max = q.front();

		for (int i : q) {
			if (i < min)
				min = i;
			if (i > max)
				max = i;
		}
		return new int[] { min, max };
	}

	/**
	 * Reports an array of two {@code int}s with the smallest and the
	 * largest integer in the given {@code Queue<Integer>}.
	 * 
	 * @param q
	 *            the queue of integer
	 * @return an array of two {@code int}s with the smallest and the
	 *         largest integer in the given queue
	 * @requires <pre>
	 * {@code q /= empty_string}
	 * </pre>
	 * @ensures <pre>
	 * {@code { minAndMax[0], minAndMax[1] } is subset of entries(q) and
	 *  for all x: integer
	 *      where (x in in entries(q))
	 *    (minAndMax[0] <= x <= minAndMax[1])}
	 * </pre>
	 */
	private static int[] minAndMax2(Queue<Integer> q) {
		assert q.length() != 0: "Violation of: \"Queue is not empty\"";
		int[] pair = new int[2];
		int min, max;
		min = max = q.front();

		Iterator<Integer> iter = q.iterator();
		while (iter.hasNext()) {
			pair[0] = iter.next();
			if (iter.hasNext())
				pair[1] = iter.next();
			else
				pair[1] = pair[0];
			Arrays.sort(pair);
			if (pair[0] < min)
				min = pair[0];
			if (pair[1] > max)
				max = pair[1];
		}
		pair[0] = min;
		pair[1] = max;
		return pair;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] { 4, 2 };
		System.out.println(Arrays.toString(arr));
		swap(arr);
		System.out.println(Arrays.toString(arr));
	}

	static void swap(int[] arr) {
		int temp = arr[0];
		arr[0] = arr[1];
		arr[1] = temp;
	}
	
}
