package edu.osu.cse.misc.game.snake;

import java.applet.Applet;

import edu.osu.cse.misc.game.snake.wrappers.field.GameField;

public class Snake extends Applet {

	public Snake(int width, int height) {
		GameField field = new GameField(width, height);
	}
	
	public static void main(String[] args) {
		Snake game = new Snake(30, 30);
	}
	
	
}
