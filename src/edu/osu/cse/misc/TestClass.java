package edu.osu.cse.misc;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class TestClass {

	private static Robot robot;
	private static Random random = new Random();
	private static TimerTask task = new TimerTask() {
		@Override
		public void run() {
			//Wait a random amount for randomization. Any amount from 0s-60s
			try {
				Thread.sleep(random.nextInt(60000));
			} catch (InterruptedException n) {}

			if (robot != null) {
				int key = random.nextInt(2) == 1 ? KeyEvent.VK_D : KeyEvent.VK_A; //nextInt(2) == [0, 2)
				robot.keyPress(key);
				int hold = random.nextInt(300) + 100;
				try {
					//Hold the arrow key for a random amount of time, 100ms-399ms
					Thread.sleep(hold);
				} catch (InterruptedException n) {}
				robot.keyRelease(key);
				System.out.println("[" + new Date(System.currentTimeMillis()).toString() + "] Turned " + (key == KeyEvent.VK_D ? "right" : "left") + " for " + hold + "ms.");
			}
		}
	};
	private static Timer timer = new Timer();

	static {
		try {
			robot = new Robot();
		} catch (Exception ignored) {
			ignored.printStackTrace();
			System.exit(0);
		}
	}

	public static void main(String[] args) throws Exception {
		//Every 10 minutes, execute the task.
		timer.scheduleAtFixedRate(task, new Date(System.currentTimeMillis()), 600000L);
	}
	
}
