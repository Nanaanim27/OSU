package edu.osu.cse._2331.hw6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Prim {

	static String OUT = "OUT", IN = "IN";
	static boolean DEBUG = false;

	public static void main(String[] args) {
		//System.setOut(new DOut());

		final String INPUT_FILE = "input.txt", OUTPUT_FILE = "output.txt";


		String[] input = read(INPUT_FILE);

		System.out.println("--- INPUT ---");
		for (String s : input)
			System.out.println(s);
		System.out.println("--- INPUT ---");
		System.out.println();

		String nodesAndEdges = input[0];

		Node[] nodes = new Node[Integer.parseInt(String.valueOf(nodesAndEdges.charAt(0)))];
		initNodes(input, nodes);

		final Queue<Edge> inToOutEdges = new PriorityQueue<Edge>() {
			@Override
			public boolean offer(Edge e) {
				if (!contains(e))
					return super.offer(e);
				return false;
			}
		};
		Queue<Node> in = new PriorityQueue<Node>(nodes.length, new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return n1.id - n2.id;
			}
		});

		in.add(nodes[0]);
		nodes[0].group = IN;
		for (Edge e : nodes[0].allEdges)
			inToOutEdges.offer(e);


		while (in.size() != nodes.length) {

			System.out.println();
			System.out.println("in: " + in);
			System.out.println("potential edges: " + inToOutEdges);

			Edge min = null;
			while (!inToOutEdges.isEmpty()) {
				min = inToOutEdges.poll();
				if (!in.contains(min.n1) || !in.contains(min.n2))
					break;
			}
			if (min == null)
				break;
			min.used = true;
			if (min != null)
				System.out.println("min edge: " + min + " (" + min.insId + ")");

			Node comingIn = in.contains(min.n1) ? min.n2 : min.n1;
			in.add(comingIn);
			min.n2.resultEdges.offer(min);
			min.n1.resultEdges.offer(min);

			for (Edge e : comingIn.allEdges)
				if (!e.used)
					inToOutEdges.offer(e);
		}

		System.out.println();
		System.out.println("--- RESULT ---");
		
		String result = "";
		
		int edgeCount = 0;
		int nodeCount = in.size();
		while (!in.isEmpty()) {
			Node n = in.poll();
			System.out.print("Node " + n + ": ");
			while (!n.resultEdges.isEmpty()) {
				Edge e = n.resultEdges.poll();
				if (e.n2 == n) {
					Node temp = e.n1;
					e.n1 = e.n2;
					e.n2 = temp;
				}
				edgeCount++;
				result += (e.n2 + " " + e.weight + " ");
			}
			result += "\n";
		}
		System.out.println(result.trim());
		System.out.println("--- RESULT ---");


		generateOutputFile(OUTPUT_FILE, result, nodeCount, edgeCount/2);

		System.err.println("Done.");
	}

	static String[] read(String inputFile) {
		BufferedReader readIn = null;
		try {
			readIn = new BufferedReader(new InputStreamReader(new FileInputStream(new File(inputFile))));
			String[] firstLine = readIn.readLine().split(" ");
			if (firstLine.length == 2) {
				String[] result = new String[Integer.parseInt(firstLine[0]) + 1]; //firstLine[0] is # nodes, thus number of lines of content
				result[0] = firstLine[0] + " " + firstLine[1];

				//Should iterate proper amount of times given a properly formatted input. Any exception gets caught and simply results in the error return, { }
				for (int i = 1; i < result.length; i++) {
					result[i] = readIn.readLine();
				}
				return result;
			}

		} catch (Exception any) {
			//Will return an empty array
		} finally {
			if (readIn != null)
				try { readIn.close(); } catch (IOException e) {}
		}

		return new String[] {};
	}

	static void initNodes(String[] input, Node[] nodes) {
		for (int i = 1; i < input.length; i++) {
			nodes[i-1] = new Node(i-1);
			nodes[i-1].group = OUT;
		}

		String[] lineData;
		int lineLength;
		for (int i = 1; i < input.length; i++) {

			lineData = input[i].split(" ");
			lineLength = lineData.length;

			if (lineLength % 2 == 0) {
				for (int j = 0; j < lineLength;) {
					int nodeNum = Integer.parseInt(lineData[j++]);
					int edgeWeight = Integer.parseInt(lineData[j++]);

					Node from = nodes[i-1];
					Node to = nodes[nodeNum-1];
					Edge e = Edge.edge(from, to, edgeWeight);

					from.allEdges.add(e);
					to.allEdges.add(e);
				}
			}
		}
	}

	static void generateOutputFile(String outputFile, String result, int nodeCount, int edgeCount) {
		File f = new File(outputFile);
		PrintWriter writeOut = null;
		try {
			writeOut = new PrintWriter(f);
			writeOut.println(nodeCount + " " + edgeCount);
			writeOut.println(result.trim());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (writeOut != null)
				writeOut.close();
		}
	}




	static class Edge implements Comparable<Edge> {

		static List<Edge> edges = new ArrayList<>();

		static int id = 0;

		int insId;
		Node n1, n2;
		int weight;
		boolean used = false;

		Edge(Node n1, Node n2, int weight) {
			this.n1 = n1;
			this.n2 = n2;
			this.weight = weight;
			this.insId = id++;

			edges.add(this);
		}

		@Override
		public String toString() {
			return String.valueOf(this.weight);
		}

		@Override
		public boolean equals(Object o) {
			if (o == this)
				return true;
			if (o instanceof Edge) {
				Edge other = (Edge) o;
				return other.weight == this.weight && other.n1 == this.n2 && other.n2 == this.n1;
			}
			return false;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

		static Edge edge(Node n1, Node n2, int weight) {
			for (Edge e : edges)
				if ( ((e.n1 == n1 && e.n2 == n2) || (e.n1 == n2 && e.n2 == n1)) && e.weight == weight) {
					return e;
				}
			Edge edge = new Edge(n1, n2, weight);
			edges.add(edge);
			return edge;
		}

	}

	static class Node {

		int id;
		Queue<Edge> allEdges = new PriorityQueue<Edge>() {
			public boolean addAll(Collection<? extends Edge> c) {
				for (Edge e : c)
					if (!this.contains(e))
						return this.offer(e);
				return false;
			};
		};
		Queue<Edge> resultEdges = new PriorityQueue<Edge>() {
			public boolean addAll(Collection<? extends Edge> c) {
				for (Edge e : c)
					if (!this.contains(e))
						return this.offer(e);
				return false;
			};
		};

		String group;

		Node(int id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return String.valueOf(this.id + 1);
		}

	}

	static class DOut extends PrintStream {

		public DOut() {
			super(System.out);
		}

		@Override
		public void println(String o) {
			if (Prim.DEBUG) {
				super.println(o);
			}
		}

		@Override
		public void print(String o) {
			if (Prim.DEBUG) {
				super.print(o);
			}
		}

		@Override
		public void println() {
			if (Prim.DEBUG)
				super.println();
		}
	}

}
