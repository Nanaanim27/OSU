package edu.osu.cse._2221.project10.html;

import java.awt.Color;
import java.util.Iterator;

import components.map.Map.Pair;
import components.queue.Queue;
import components.queue.Queue1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.stack.Stack;
import components.stack.Stack1L;

/**
 * A string that holds different HTML properties.
 * {@link #toString()} will generate the proper HTML 
 * text to display this String as it is described via its chained methods. 
 */
public class HtmlString extends HtmlComponent {

	private boolean bold, underlined, italicized;
	private String text, color, size;

	private Sequence<HtmlTag> otherTags = new Sequence1L<>();

	public HtmlString(String text) {
		this.text = text;
	}

	public HtmlString bold(boolean bold) {
		this.bold = bold;
		return this;
	}

	public HtmlString underlined(boolean underlined) {
		this.underlined = underlined;
		return this;
	}

	public HtmlString italicized(boolean italicized) {
		this.italicized = italicized;
		return this;
	}

	public HtmlString size(int size) {
		this.size = ""+size;
		return this;
	}

	public HtmlString color(Color color) {
		this.color = "#" + Integer.toHexString(color.getRGB()).substring(2);
		return this;
	}

	public HtmlString withTag(HtmlTag tag) {
		this.otherTags.add(this.otherTags.length(), tag);
		return this;
	}

	@Override
	public String toString() {
		Queue<HtmlTag> tags = new Queue1L<>();
		Stack<HtmlTag> tags2 = new Stack1L<>();
		if (this.color != null || this.size != null) {
			HtmlTag fontTag = new HtmlTag("font");
			if (this.color != null)
				fontTag.withAttribute("color", this.color);
			if (this.size != null)
				fontTag.withAttribute("size", this.size);
			tags.enqueue(fontTag);
		}
		if (this.bold) {
			tags.enqueue(new HtmlTag("b"));
		}
		if (this.underlined) {
			tags.enqueue(new HtmlTag("u"));
		}
		if (this.italicized) {
			tags.enqueue(new HtmlTag("i"));
		}
		
		for (HtmlTag tag : this.otherTags)
			tags.enqueue(tag);

		String html = "";
		for (HtmlTag tag : tags) {
			tags2.push(tag);
			html += "<" + tag.getLabel(); //<font

			Iterator<Pair<String, String>> iter = tag.iterator();
			Pair<String, String> next;
			while (iter.hasNext()) {
				next = iter.next();
				html += " " + next.key() + "=\"" + next.value() + "\""; //<font color="#ffffff"
			}
			html += ">"; //<font color="#ffffff">
		}
		html += this.text;
		for (HtmlTag tag : tags2) {
			html += "</" + tag.getLabel() + ">";
		}

		return html;
	}

}
