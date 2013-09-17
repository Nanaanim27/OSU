package edu.osu.cse._2221.lab8;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

public final class XMLTreeExploration {

	private XMLTreeExploration(){}

	public static void main(String[] args) {
		try (SimpleWriter out = new SimpleWriter1L()) {
			XMLTree xml = new XMLTree1("http://xml.weather.yahoo.com/forecastrss/43210.xml");
			
			firstPart(out, xml);
			secondPart(out, xml);
			thirdPart(out, xml);
			fourthPart(out, xml);
			fifthPart(out, xml);
			
			
		}
	}

	private static void fifthPart(SimpleWriter out, XMLTree xml) {
		XMLTree channel = getChild(xml, "channel");
		printMiddleNode(channel, out);
	}

	private static void fourthPart(SimpleWriter out, XMLTree xml) {
		XMLTree channel = getChild(xml, "channel");
		if (channel != null) {
			XMLTree astronomy = getChild(channel, "yweather:astronomy");
			if (astronomy != null) {
				out.println("The 'astronomy' subtree " + (astronomy.hasAttribute("sunset") ? " has a sunset attribute" : " does not have a sunset attribute"));
				out.println("The 'astronomy' subtree " + (astronomy.hasAttribute("midday") ? " has a midday attribute" : " does not have a midday attribute"));
				if (astronomy.hasAttribute("sunrise")) {
					out.println("Sunrise: " + astronomy.attributeValue("sunrise"));
				}
				if (astronomy.hasAttribute("sunset")) {
					out.println("Sunset: " + astronomy.attributeValue("sunset"));
				}
				
			}
			else {
				out.println("The 'yweather:astronomy' subtree does not exist.");
			}
		}
	}

	private static void thirdPart(SimpleWriter out, XMLTree xml) {
		XMLTree channel = getChild(xml, "channel");
		if (channel != null) {
			out.println("The 'channel' subtree has " + channel.numberOfChildren() + " children.");
			XMLTree title = getChild(channel, "title");
			if (title != null) {
				if (title.numberOfChildren() > 0) {
					XMLTree titleText = getChild(title, null);
					if (titleText != null) {
						out.println("[\"Normal\"] The label of titleText is " + titleText.label());
					}
					else {
						out.println("The 'titleText' subtree does not exist");
					}
				}
			}
			else {
				out.println("The 'title' subtree does not exist");
			}
		}
		else {
			out.println("The 'channel' subtree does not exist.");
		}
		
		try {
			out.println("[\"Challenge\"] The label of titleText is " + getChild(getChild(getChild(xml, "channel"), "title"), null).label());
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	private static void secondPart(SimpleWriter out, XMLTree xml) {
		out.println("The root of this tree is " + (xml.isTag() ? "a tag." : "text."));
	}

	private static void firstPart(SimpleWriter out, XMLTree xml) {
		//out.print(xml.toString());
		xml.display();
	}
	
	/**
	 * Finds a child XMLTree of a given parent XMLTree.
	 * 
	 * @param parent The parent XMLTree
	 * @param child The name of the label to find in the parent.
	 * @return The subtree of the parent if it exists, <tt>null</tt> if it does not exist.
	 */
	private static XMLTree getChild(XMLTree parent, String child) {
		int numChildren = parent.numberOfChildren();
		if (numChildren > 0) {
			for (int i = 0; i < numChildren; i++) {
				XMLTree thisChild = parent.child(i);
				if (child == null || thisChild.label().equals(child)) {
					return thisChild;
				}
			}
		}
		return null;
	}

	/**
	 * Output information about the middle child of the given {@code XMLTree}.
	 * 
	 * @param xt
	 *            the {@code XMLTree} whose middle child is to be printed
	 * @param out
	 *            the output stream
	 * @updates {@code out.content}
	 * @requires <pre>
	 * {@code [the label of the root of xt is a tag]  and
	 * [xt has at least one child]  and  out.is_open}
	 * </pre>
	 * @ensures <pre>
	 * {@code out.content = #out.content * [the label of the middle child
	 *  of xt, whether the root of the middle child is a tag or text,
	 *  and if it is a tag, the number of children of the middle child]}
	 * </pre>
	 */
	private static void printMiddleNode(XMLTree xml, SimpleWriter out) {
		int numChildren = xml.numberOfChildren();
		XMLTree middleChild = xml.child(numChildren / 2);
		if (middleChild != null) {
			out.println("The middle child's label: " + middleChild.label());
			out.println("The root of this tree is " + (middleChild.isTag() ? "a tag." : "text."));
			if (middleChild.isTag()) {
				out.println("The middle child is a tag. It has " + middleChild.numberOfChildren() + " children.");
			}
		}
		
	}
	
}
