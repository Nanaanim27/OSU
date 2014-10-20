package edu.osu.cse._2331.hw6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 * @author Matt Weiss
 * @email weiss.357@osu.edu
 *
 */
public class Prim {

	private static final String INPUT_FILE_NAME = "input.txt";
	private static final String OUTPUT_FILE_NAME = "output.txt";

	private static class Node {

		public final int id;
		public final Edge[] edges;
		public boolean visited = false;
		
		public Node(final int id, final int numEdges) {
			this.id = id;
			this.edges = new Edge[numEdges];
		}

	}

	private static class Edge {

		public final Node n1, n2;
		public final int weight;

		public Edge(final Node n1, final Node n2, final int weight) {
			this.n1 = n1;
			this.n2 = n2;
			this.weight = weight;
		}

	}

	public static void main(String[] args) {
		final String[] fileContents = readFileIn().split("\n");
		String[] temp;
		if (fileContents.length > 0) {
			//If we get to this point, we read input.txt successfully.
			int numNodes = -1, numTotalEdges = -1;
			temp = fileContents[0].trim().split(" "); //First line
			if (temp.length == 2) {
				try {
					numNodes = Integer.parseInt(temp[0]);
					numTotalEdges = Integer.parseInt(temp[1]);
				} catch (NumberFormatException e) {
					numNodes = numTotalEdges = -1; //Error of any kind reading the data, return to default values.
				}
				if (numNodes > 0 && numTotalEdges > 0) {
					
					final Node[] nodes = new Node[fileContents.length - 1];
					String line;
					for (int i = 1; i < fileContents.length; i++) {
						line = fileContents[i];
						temp = line.split(" ");
						if (temp.length % 2 == 0) {
							//This line is properly formatted (node weight node weight ....)
							
							final int numEdges = temp.length / 2;
							nodes[i] = new Node(i, numEdges);
							for (int j = 0; j < numEdges;) {
								nodes[i].edges[j] = new Edge(nodes[i], nodes[j++], j++);
							}
							
						}
						else {
							System.err.println("Line " + i + " does not contain proper values.");
							return;
						}
					}
					
					
					//Initialize data structures
					final List<Node> visitedNodes = new ArrayList<Node>(nodes.length) {{
						add(nodes[0]);
					}};
					
					final Comparator<Edge> comp = new Comparator<Edge>() {
						@Override
						public int compare(Edge o1, Edge o2) {
							return o1.weight - o2.weight;
						}
					};
					
					final PriorityQueue<Edge> potentialEdges = new PriorityQueue<Edge>(numTotalEdges, comp) {{
						for (final Edge e : visitedNodes.get(0).edges) {
							offer(e);
						}
					}};
					
					final List<Edge> minimalTree = new ArrayList<Edge>(numTotalEdges); //size will be <= numTotalEdges
					while (visitedNodes.size() != nodes.length) {
						final Edge minEdge = potentialEdges.poll();
						
					}
					
					
				}
			}

		}
	}

	/**
	 * Reads the input data file into an array of Strings.
	 * 
	 * @return The contents of {@code INPUT_FILE_NAME}, or an empty String in the event of an error.
	 */
	private static String readFileIn() {
		String fileData = "";
		try (final FileInputStream fIn = new FileInputStream(INPUT_FILE_NAME);
				final BufferedReader readIn = new BufferedReader(new InputStreamReader(fIn))) {

			String line;
			while ((line = readIn.readLine()) != null) {
				fileData += line.trim() + "\n";
			}
			fileData = fileData.trim();
		} catch (final IOException e) {
			e.printStackTrace();
			fileData = "";
		}
		return fileData;
	}
}
