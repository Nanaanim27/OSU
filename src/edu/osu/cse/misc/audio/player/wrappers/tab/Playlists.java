package edu.osu.cse.misc.audio.player.wrappers.tab;

import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.osu.cse.misc.audio.player.interfaces.Panel;
import edu.osu.cse.misc.audio.player.wrappers.Playlist;
import edu.osu.cse.misc.swing.CollapsableJPanel;

public class Playlists implements Panel {

	public JLabel selectedPlaylist;
	
	public static void main(String[] args) {
		System.out.println();
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a matrix with odd size (i.e, 7x7): ");
		
		try {
			String[] input = in.nextLine().toLowerCase().split("x");
			int rows = Integer.parseInt(input[0]);
			int columns = Integer.parseInt(input[1]);
			if (rows != columns || rows % 2 == 0) {
				System.err.println("Offset dimensions, rows and columns should be equal.");
				return;
			}
			
			int[][] mtx = new int[rows][columns];
			
			int lowerBound = 1; 
			int upperBound = columns - 2;
			int height = upperBound - lowerBound;
			
			for (int r1 = 0, r2 = mtx.length-1, dH = 0; dH < height ; r1++, r2--, dH++, lowerBound++, upperBound--) {
				int[] topRow = mtx[r1], bottomRow = mtx[r2];
				
				for (int column = lowerBound; column <= upperBound; column++) {
					topRow[column] = bottomRow[column] = 1;
				}
			}
			print(mtx);
			
		} catch (Exception badInput) {
			badInput.printStackTrace();
		}
	}
	
	private static void print(int[][] mtx) {
		for (int r = 0; r < mtx.length; r++) {
			for (int c = 0; c < mtx[r].length; c++) {
				System.out.print(mtx[r][c] + ",\t");
			}
			System.out.print("\n");
		}
	}
	
	public Playlists() {
		
	}
	
	@Override
	public JPanel getPanel() {
		CollapsableJPanel panel = new CollapsableJPanel("Playlists");
		
		return panel;
	}
	
	public void addPlaylist(Playlist pl) {
		JLabel comp = pl.getLabel(this);
	}
	
	
}
