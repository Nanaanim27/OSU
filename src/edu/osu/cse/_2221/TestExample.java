package edu.osu.cse._2221;

import components.queue.Queue;
import components.queue.Queue2;
import components.stack.Stack;
import components.stack.Stack2;

public class TestExample {
	
	public static void main(String[] args) {
		Queue<String> q = new Queue2<>();
		q.enqueue("String 1");
		q.enqueue("String 2");
		q.enqueue("String 3");
		flip(q);
		System.out.println(q);
	}
	
	private static void flip(Queue<String> q) {
		Stack<String> s = new Stack2<>();
		for (String str : q) {
			s.push(str);
			System.out.println("Pushed " + str);
		}
		q.clear();
		int len = s.length();
		while (q.length() != len) {
			q.enqueue(s.pop());
		}
	}
	
	
}
