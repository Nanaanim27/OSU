package edu.osu.cse.misc.audio.player.wrappers;

import java.io.File;
import java.io.IOException;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

/**
 * A Song points to a file, namely a .mp3 file, which can be played through the MusicPlayer
 */
public class Song {

	/** The main file that holds the audio of this Song. */
	private File file;
	
	private int length;
	private String artist = "", songName = "";
	
	public Song(String path) {
		this(new File(path));
	}
	
	public Song(File file) {
		if (file != null && file.exists()) {
			this.file = file;
			this.resolveTags();
		}
	}
	
	/**
	 * The length of this song in seconds.
	 * 
	 * @return The length of this song in seconds.
	 */
	public int getLength() {
		return this.length;
	}
	
	/**
	 * The artist of this Song.
	 * 
	 * @return The artist of this Song.
	 */
	public String getArtist() {
		return this.artist;
	}
	
	/**
	 * The title of this Song.
	 * 
	 * @return The title of this Song.
	 */
	public String getSongName() {
		return this.songName;
	}
	
	/** Stores the mp3 tags of the file represented by this Song. */
	private void resolveTags() {
		try {
			Mp3File mp3 = new Mp3File(this.file.getAbsolutePath());
			if (mp3.hasId3v2Tag()) {
				ID3v2 tag = mp3.getId3v2Tag();
				this.artist = tag.getArtist();
				this.songName = tag.getTitle();
				this.length = tag.getLength();
			}
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
