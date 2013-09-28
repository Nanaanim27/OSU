package edu.osu.cse._2221;

public class TestExample {

	public static void main(String[] args) {
		int[] arr = { 1, 3, 2, 1, 4, 5, 2, 1, 5, 8, 1,
				11, 13, 20, 43, 2};
		System.out.println(countBigger(arr, 15));
	}
	
	private static int countBigger(int[] a, int n) {
		int count = 0;
		for (int i : a) {
			if (i > n) {
				count++;
			}
		}
		return count;
	}
	
}
