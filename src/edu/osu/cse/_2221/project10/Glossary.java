package edu.osu.cse._2221.project10;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

import edu.osu.cse._2221.project10.html.HtmlList;
import edu.osu.cse._2221.project10.html.HtmlString;

/**
 * This program will generate an index that links to multiple definitions.
 * Any definition that includes other words found in the index should also be linked.
 */
public class Glossary {

	static String folder;
	static String root;
	
	private File rootFile;
	private Set<GlossaryEntry> words;
	
	public Glossary() {
		this(new Set1L<GlossaryEntry>());
	}
	
	public Glossary(Set<GlossaryEntry> words) {
		this.words = words;
	}
	
	/**
	 * Creates and writes the index.html file <i>and</i> each definition within it.
	 * 
	 * @return This Glossary object. Return used for chaining.
	 */
	private Glossary generate(String targetDirectory) {
		if (this.generateHtmlFile(targetDirectory, this.words).exists()) {
			for (GlossaryEntry w : this.words) {
				if (!w.generateHtmlFile(targetDirectory, this.words).exists()) {
					System.err.println("Error creating HTML file for: " + w.getWord());
				}
			}
			this.open();
		}
		else {
			System.err.println("Target directory does not exist: " + targetDirectory);
			System.exit(0);
		}
		return this;
	}
	
	/**
	 * Generates and returns the index file for this Glossary 
	 */
	private File generateHtmlFile(String targetDirectory, Set<GlossaryEntry> otherWords) {
		try (SimpleWriter fWrite = new SimpleWriter1L(root)) {
			fWrite.println("<html>");
			/////////////////////////
			fWrite.println("<head><title>");
			fWrite.println("Glossary");
			fWrite.println("</title></head>");
			/////////////////////////
			fWrite.println("<body>");
			fWrite.println(new HtmlString("Glossary").bold(true).size(6));
			fWrite.println("<hr />");
			fWrite.println(new HtmlString("Index").bold(true).size(4));
			fWrite.println(new HtmlList(this.words).alphabatize());
			fWrite.println("</body>");
			/////////////////////////
			fWrite.println("</html>");
		}
		return (this.rootFile = new File(root));
	}
	
	/**
	 * Attempts to open the index file.
	 */
	private void open() {
		if (this.rootFile == null) {
			System.err.println("A call to generateHtmlFile(Set<Word>) must be made before opening the file.");
			return;
		}
		try {
			Desktop.getDesktop().open(this.rootFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		try (SimpleReader in = new SimpleReader1L(); SimpleWriter out = new SimpleWriter1L()) {
			out.print("Enter the name of the input file: ");
			try (SimpleReader termsFileStream = new SimpleReader1L(in.nextLine())) {
				Set<GlossaryEntry> words = new Set1L<>();
				String line, word, definition = "";
				while (!termsFileStream.atEOS()) {
					word = termsFileStream.nextLine();
					while (!termsFileStream.atEOS() && !(line = termsFileStream.nextLine()).equals("")) {
						definition += line + " ";
					}
					words.add(new GlossaryEntry(word, definition.trim()));
					definition = "";
				}
				out.print("Enter the target directory for the generated files: ");
				folder = in.nextLine();
				root = folder + "/" + "index.html";
				new Glossary(words).generate(folder).open();
			}
		}
		
	}
	
}
