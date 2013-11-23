package edu.osu.cse._2231.project1;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

import edu.osu.cse._2231.project1.html.HtmlTable;

public class CountWords {

	
	public static void main(String[] args) {
		try(SimpleWriter out = new SimpleWriter1L(); 
				SimpleReader in = new SimpleReader1L()) {

			out.print("Enter the name of an input file: ");
			String inputFile = in.nextLine();

			out.print("Enter the name  of the out file: ");
			String outputFile = in.nextLine();

			try (SimpleReader fileIn = new SimpleReader1L(inputFile)) {
				String line;
				CountMap<String, Integer> wordCounts = new CountMap<>();
				while (!fileIn.atEOS()) {
					line = fileIn.nextLine();
					String[] words = line.split("[^A-Za-z0-9]+");
					for (String word : words) {
						if (!word.equals(""))
							wordCounts.put(word, 1);
					}
				}
				for (String key : wordCounts.keySet()) {
					System.out.println(key + ": " + wordCounts.get(key));
				}
				HtmlTable table = new HtmlTable(1, wordCounts.size(), 2);
				
				table.setColumn(0, wordCounts.keySet());
				table.setColumn(1, wordCounts.values());
				table.setColumnHeader(0, "Word");
				table.setColumnHeader(1, "Counts");
				
				try(SimpleWriter fileOut = new SimpleWriter1L(outputFile)) {
					fileOut.println("<html>");
					fileOut.println("<body>");
					fileOut.println("<h2>Word counts in " + inputFile + "</h2>");
					fileOut.println(table.toString());
					fileOut.println("</body>");
					fileOut.println("</html>");
					File f = new File(outputFile);
					if (f.exists()) {
						Desktop.getDesktop().open(f);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
