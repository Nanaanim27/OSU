package edu.osu.cse._2221.project10;

import java.awt.Color;
import java.io.File;

import components.set.Set;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

import edu.osu.cse._2221.project10.html.HtmlComponent;
import edu.osu.cse._2221.project10.html.HtmlString;
import edu.osu.cse._2221.project10.html.HtmlTag;

public class GlossaryEntry implements Comparable<GlossaryEntry> {

	private String word, definition;

	public GlossaryEntry(String word, String definition) {
		this.word = word;
		this.definition = definition;
	}

	public String getWord() {
		return this.word;
	}

	public String getDefinition() {
		return this.definition;
	}

	/**
	 * Generates the .html file for this entry as follows:<pre>
	 * <font color="red" size=5><b><i>Word</i></b></font>
	 * <blockquote>Definition</blockquote>
	 * <hr />
	 * Return to index</pre>
	 * 
	 * @param targetDirectory The target directory for the generated files. i.e. "out" to use the local out directory for the workspace.
	 * @param otherWords A Set of Strings that contains the words of the Glossary.
	 * @return The File generated by this function call.
	 */
	public File generateHtmlFile(String targetDirectory, Set<GlossaryEntry> otherWords) {
		String fPath = this.getWord() + ".html";
		try (SimpleWriter fWrite = new SimpleWriter1L(targetDirectory + "/" + fPath)) {
			HtmlComponent wordTitle = new HtmlString(this.getWord()).color(Color.red).bold(true).italicized(true).size(5);

			String[] definitionWords = this.definition.split("[ ]");
			this.definition = "";
			for (int i = 0; i < definitionWords.length; i++) {
				for (GlossaryEntry w : otherWords) {
					if (definitionWords[i].replaceAll(",", "").equalsIgnoreCase(w.getWord())) {
						if (definitionWords[i].contains(","))
							definitionWords[i] = w.generateHtmlHref() + ",";
						else
							definitionWords[i] = w.generateHtmlHref();
					}
				}
				this.definition += definitionWords[i] + " ";
			}
			this.definition.trim();

			HtmlComponent definition = new HtmlString(this.getDefinition()).withTag(new HtmlTag("blockquote"));
			HtmlComponent backToIndex = new HtmlString("Return to Index").withTag(new HtmlTag("a").withAttribute("href", "index.html"));

			fWrite.println("<html>");
			/////////////////////////
			fWrite.println("<head><title>");
			fWrite.println(this.getWord());
			fWrite.println("</title></head>");
			/////////////////////////
			fWrite.println("<body>");
			fWrite.println(wordTitle.toString());
			fWrite.println(definition.toString());
			fWrite.println("<hr />");
			fWrite.println(backToIndex.toString());
			fWrite.println("</body>");
			/////////////////////////
			fWrite.println("</html>");
		}
		return new File(targetDirectory + "/" + fPath);
	}

	/**
	 * @return the href html String for this word.
	 */
	public String generateHtmlHref() {
		return "<a href=\"" + this.getWord() + ".html\">" + this.getWord() + "</a>";
	}

	@Override
	public int compareTo(GlossaryEntry o) {
		return this.getWord().compareTo(o.getWord());
	}

}
