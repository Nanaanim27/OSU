package edu.osu.cse.misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class AudioDocumentaryCharacters {

	public static void main(String[] args) {
		File ts = new File("C:\\Users\\Matt\\Desktop\\transcript.txt");
		if (ts.exists()) {
			try (Scanner in = new Scanner(ts)) {
				LinkedList<String> contents = new LinkedList<>();
				LinkedHashSet<String> characters = new LinkedHashSet<>();
				while (in.hasNextLine())
					contents.add(in.nextLine());
				
				
				for (String s : contents) {
					if (!s.trim().equals("") && s.contains(":")) {
						characters.add(s.split(":")[0]);
					}
				}
				
				System.out.println();
				for (String s : characters) {
					System.out.println(s);
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
