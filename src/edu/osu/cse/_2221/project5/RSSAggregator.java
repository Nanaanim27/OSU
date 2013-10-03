package edu.osu.cse._2221.project5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;


public class RSSAggregator {

	private String url, title;
	private ArrayList<RSSFeed> feeds = new ArrayList<>();

	public RSSAggregator(String url) {
		this.url = url;
	}

	public void process() {
		XMLTree root = new XMLTree1(this.url);
		if (root.isTag() && root.hasAttribute("title")) {
			this.title = root.attributeValue("title");
		}
		
		for (XMLTree feed : this.filterFeeds(root)) {
			if (feed.hasAttribute("url") && feed.hasAttribute("name") && feed.hasAttribute("file")) {
				this.feeds.add(new RSSFeed(feed.attributeValue("url"), feed.attributeValue("name"), feed.attributeValue("file")));
			}
		}
		this.writeToFile();
	}
	
	private XMLTree[] filterFeeds(XMLTree root) {
		ArrayList<XMLTree> tempFeeds = new ArrayList<>();
		for (int i = 0; i < root.numberOfChildren(); i++) {
			XMLTree child = root.child(i);
			if (child.label().equals("feed")) {
				tempFeeds.add(child);
			}
		}
		return tempFeeds.toArray(new XMLTree[tempFeeds.size()]);
	}
	
	private void writeToFile() {
		File feedHtml = new File("./index.html");
		try (FileOutputStream fOut = new FileOutputStream(feedHtml)) {

			byte[] contents = this.toString().getBytes();
			fOut.write(contents);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		String html = "";
		html += "<strong>" + this.title + "</strong>";
		html += "<ul>";
		for (RSSFeed feed : this.feeds) {
			html += "<li><a href=\"" + feed.getUrl() + "\">" + feed.getTitle() + "</li>";
		}
		html += "</ul>";
		return html;
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 *            the command line arguments; unused here
	 */
	public static void main(String[] args) {
		try (SimpleReader in = new SimpleReader1L();
				SimpleWriter out = new SimpleWriter1L()) {
			out.println("Enter the URL of the target XML input file: ");
			String targetUrl = in.nextLine();
			new RSSAggregator(targetUrl).process();
		}
	}

}
