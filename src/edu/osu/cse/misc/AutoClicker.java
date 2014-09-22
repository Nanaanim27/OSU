package edu.osu.cse.misc;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class AutoClicker {

	public static void main(String[] args) throws Exception {

		final Robot robot = new Robot();
		final Point p1 = new Point(206, 299);
		final Point p2 = new Point(896, 251);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(true) {
						Thread.sleep(600);
						Point mouseLoc = MouseInfo.getPointerInfo().getLocation();
						if (mouseLoc.x <= 50) {
							System.out.println("Exiting.");
							System.exit(0);
						}
						robot.mouseMove(p1.x, p1.y);
						robot.mousePress(MouseButton.RIGHT.getMask());
						robot.mouseRelease(MouseButton.RIGHT.getMask());
						Thread.sleep(600);
						robot.mouseMove(p2.x, p2.y);
						robot.mousePress(MouseButton.LEFT.getMask());
						robot.mouseRelease(MouseButton.LEFT.getMask());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private enum MouseButton {
		LEFT(InputEvent.BUTTON1_DOWN_MASK),
		RIGHT(InputEvent.BUTTON3_DOWN_MASK),
		MIDDLE(InputEvent.BUTTON2_DOWN_MASK);

		private int mask;

		MouseButton(int mask) {
			this.mask = mask;
		}

		private int getMask() {
			return this.mask;
		}
	}

}
