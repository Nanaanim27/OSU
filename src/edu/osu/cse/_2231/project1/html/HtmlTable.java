package edu.osu.cse._2231.project1.html;

import java.util.Collection;
import java.util.Iterator;


public class HtmlTable extends HtmlComponent {

	private final int borderThickness;

	private String[][] data; //Excludes headers
	private final String[] headers;

	public HtmlTable(int borderThickness, int rows, int columns) {
		this.borderThickness = borderThickness;
		this.data = new String[rows][columns];
		this.headers = new String[columns];
	}

	public void setColumnHeader(int column, String header) {
		if (column >= this.headers.length) {
			System.err.println("Invalid column number: " + column + ". Max column number is: " + (this.headers.length-1));
			return;
		}
		this.headers[column] = header;
	}

	public void setColumn(int column, Collection<?> data) {
		Iterator<?> iter = data.iterator();
		for (int i = 0; i < this.data.length; i++) {
			this.data[i][column] = iter.next().toString();
		}
	}

	@Override
	public String toString() {
		String html = "";

		html += "<table border=\"" + this.borderThickness + "\">\n";
		
		html += "<tr>\n";
		for (String header : this.headers)
			html += "<th>" + header + "</th>\n";
		html += "</tr>\n";
		
		for (int row = 0; row < this.data.length; row++) {
			html += "<tr>\n";
			for (int column = 0; column < this.data[row].length; column++) {
				html += "<td>" + this.data[row][column] + "</td>\n";
			}
			html += "</tr>\n";
		}
		
		html += "</table>";
		
		return html;
	}
	



}
