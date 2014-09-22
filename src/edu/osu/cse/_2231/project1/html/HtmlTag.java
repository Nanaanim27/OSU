package edu.osu.cse._2231.project1.html;

import components.map.Map1L;

/**
 * Much like XML tags, an HtmlTag represents a tag with any amount of attributes.
 * <pre>For example:
 * new HtmlTag("phone") would convert to HTML as <*phone>
 * new HtmlTag("phone").withAttribute("number", "555-555-5555") would
 * convert to HTML as <*phone number="555-555-5555"></pre>
 * 
 * <br /><br />*=Let JavaDoc display the html representation without parsing it in the IDE.
 * 
 * @author Matt Weiss
 */
public class HtmlTag extends Map1L<String, String> {

	private String label;
	
	public HtmlTag(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public HtmlTag withAttribute(String attr, String value) {
		this.add(attr, value);
		return this;
	}
	
	public String getAttribute(String attr) {
		return this.value(attr);
	}
	
	
}
