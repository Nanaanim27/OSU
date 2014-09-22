package edu.osu.cse.misc.audio;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import com.mpatric.mp3agic.BaseException;
import com.mpatric.mp3agic.ID3v1Tag;
import com.mpatric.mp3agic.Mp3File;

public class MP3Modifier {

	public static void main5(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			String input;
			do {
				System.out.print("[d or f] Modify an entire directory or a single file? ");
				input = in.nextLine().trim().toLowerCase();
			} while (!input.matches("[df]"));

			boolean dir = input.equalsIgnoreCase("d");

			boolean cont;
			File root;
			do {
				System.out.print("Enter the full path to the " + (dir ? "directory" : "file") + " you want to modify: ");
				input = in.nextLine().trim();
				cont = !(root = new File(input)).exists() || !root.isDirectory();
				if (cont) {
					System.err.println("The path you entered does not exist and/or is not a valid file selection.");
				}
			} while (cont);

			File[] files = dir ? getAllMP3Files(root) : new File[] { root };

			for (File f : files) {
				System.out.println(f.getAbsolutePath());
				//TODO: Do whatever you want on the raw file. Excludes mp3 properties.
				
				try {
					Mp3File mp3 = new Mp3File(f.getAbsolutePath());
					//TODO: Do whatever you want on the mp3.

				} catch (BaseException | IOException e) {
					System.err.println(f.getName() + " is not a valid .mp3 file or has invalid tags.");
				}
			}
		}
	}

	private static File[] getAllMP3Files(File root) {
		LinkedList<File> files = new LinkedList<>();
		if (root.isDirectory()) {
			for (File f : root.listFiles()) {
				files.addAll(Arrays.asList(getAllMP3Files(f)));
			}
		}
		else if (root.getName().toLowerCase().endsWith(".mp3")) {
			files.add(root);
		}

		return files.toArray(new File[files.size()]);
	}


	/**
	 * File modification methods
	 */

	private static void setTag(Mp3File f, Tag tag, Object value) {
		ensureId3v1Tag(f);
		switch(tag) {
			case ALBUM:
				f.getId3v1Tag().setAlbum(value.toString());
				break;
			case ARTIST:
				f.getId3v1Tag().setArtist(value.toString());
				break;
			case COMMENT:
				f.getId3v1Tag().setComment(value.toString());
				break;
			case GENRE:
				f.getId3v1Tag().setGenre(Integer.valueOf(value.toString()));
				break;
			case TITLE:
				f.getId3v1Tag().setTitle(value.toString());
				break;
			case TRACK_NUMBER:
				f.getId3v1Tag().setTrack(value.toString());
				break;
			case YEAR:
				f.getId3v1Tag().setYear(value.toString());
				break;
		}
	}

	/**
	 * <pre>Changes the name of a .mp3 file matching
	 * Anything1(Anything2).mp3 to
	 * Anything1.mp3.
	 * 
	 * For example: Act a Fool (33andUp).mp3 to Act a Fool.mp3</pre>
	 * 
	 * 
	 * @param f The file to change the name of
	 * @return The new name of the file, or an empty string if the given file is not an mp3 file.
	 */
	private static String changeFileName(File f) {
		if (f.getAbsolutePath().endsWith(".mp3")) {
			File parent = f.getParentFile();
			String newName = f.getName().replaceAll("(\\(.*?\\)).mp3$", "").trim();
			
			f.renameTo(new File(parent, newName + ".mp3"));
			return newName;
		}
		return "";
	}

	/**
	 * Makes sure the .mp3 file contains an ID3 tag.
	 * 
	 * @param f An Mp3File
	 */
	private static void ensureId3v1Tag(Mp3File f) {
		if (!f.hasId3v1Tag()) {
			f.setId3v1Tag(new ID3v1Tag());
		}
	}


	private enum Tag {
		ALBUM,
		ARTIST,
		COMMENT,
		GENRE,
		TITLE,
		TRACK_NUMBER,
		YEAR
	}

}
