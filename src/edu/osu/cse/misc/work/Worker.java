package edu.osu.cse.misc.work;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Worker {

	private static ExecutorService executor = Executors.newSingleThreadExecutor();
	
	public static void executeRunnable(Runnable r) {
		executor.submit(r);
	}
	
}
