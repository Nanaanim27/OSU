package edu.osu.cse._2331.hw6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Prim2 {


	public static void main(String[] args) {
		final String INPUT_FILE = "input.txt", OUTPUT_FILE = "output.txt";


		String[] input = read(INPUT_FILE);
		String nodesAndEdges = input[0];

		Node[] nodes = new Node[Integer.parseInt(String.valueOf(nodesAndEdges.charAt(0)))];

		initNodes(input, nodes);

		Graph initialGraph = new Graph(nodes);
		

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

					nodes[i-1].edges.add(new Edge(nodes[i-1], nodes[nodeNum-1], edgeWeight));
				}
			}
		}
	}

	static Node minEdge(Graph in, Graph out) {
		Node winner = null;
		int winnerWeight = -1;
		
		for (Node n : in.nodes) {
			for (Edge e : n.edges) {
				if (!e.n2.visited) {
					if (winner == null || e.weight < winnerWeight) {
						winner = e.n2;
						winnerWeight = e.weight;
					}
				}
			}
		}
		System.out.println("IN contains winner? " + in.contains(winner));
		winner.visited = true;
		return winner;
	}






	static class Graph {

		List<Node> nodes;
		
		Graph() {
			this.nodes = new ArrayList<Node>();
		}
		
		/**
		 * Constructs a Graph
		 * 
		 * @param nodes The nodes of this Graph.
		 */
		Graph(Node[] nodes) {
			this.nodes = new ArrayList<Node>(Arrays.asList(nodes));
		}
		
		boolean contains(Node n) {
			return this.nodes.contains(n);
		}
		
		int numEdges() {
			int count = 0;
			for (Node n : this.nodes) {
				count += n.edges.size();
			}
			return count / 2; //Each edge goes both ways, but only should increment once.
		}

	}

	static class Edge implements Comparable<Edge> {

		Node n1, n2;
		int weight;

		Edge(Node n1, Node n2, int weight) {
			this.n1 = n1;
			this.n2 = n2;
			this.weight = weight;
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

	}

	static class Node {

		int id;
		Queue<Edge> edges = new PriorityQueue<Edge>() {
			public boolean addAll(Collection<? extends Edge> c) {
				for (Edge e : c)
					if (!this.contains(e))
						return this.offer(e);
				return false;
			};
		};
		boolean visited = false;

		Node(int id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return String.valueOf(this.id + 1);
		}

	}

}
