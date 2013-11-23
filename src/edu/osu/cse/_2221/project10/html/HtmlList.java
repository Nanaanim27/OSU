package edu.osu.cse._2221.project10.html;

import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;

import edu.osu.cse._2221.project10.GlossaryEntry;

/**
 * A basic unordered list that {@link #toString()} represents the HTML version of. 
 */
public class HtmlList extends HtmlComponent {

	private Sequence<GlossaryEntry> bullets = new Sequence1L<>();
	
	public HtmlList(GlossaryEntry...words) {
		for (GlossaryEntry word : words) {
			this.bullets.add(this.bullets.length(), word);
		}
	}
	
	public HtmlList(Set<GlossaryEntry> words) {
		for (GlossaryEntry word : words) {
			this.bullets.add(this.bullets.length(), word);
		}
	}
	
	public HtmlList alphabatize() {
		GlossaryEntry left, right;
		for (int i = 0; i < this.bullets.length(); i++) {
			for (int j = 1; j < this.bullets.length(); j++) {
				left = this.bullets.entry(j-1);
				right = this.bullets.entry(j);
				if (left.compareTo(right) > 0) {
					this.bullets.replaceEntry(j-1, right);
					this.bullets.replaceEntry(j, left);
				}
			}
		}
		return this;
	}
	
	
	@Override
	public String toString() {
		String html = "";
		
		html += "<ul>\n";
		for (GlossaryEntry w : this.bullets) {
			html += "<li>" + w.generateHtmlHref() + "</li>\n";
		}
		html += "</ul>";
		
		return html;
	}

}
