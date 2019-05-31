package thread;

import javafx.application.Platform;
import ui.GameController;

public class LevelTimeThread extends Thread {
	
	/**
	 * The thread's game controller.
	 */
	private GameController controller;
	
	/**
	 * The attribute that tells whether or not this thread is active.
	 */
	private boolean active;
	
	/**
	 * The current time in the thread.
	 */
	private int time;
	
	/**
	 * <b>Description:</b>
	 * This function initializes a new level time thread.
	 * @param controller The thread's game controller.
	 */
	public LevelTimeThread(GameController c) {
		controller = c;
		active = true;
		time = 400;
	}
	
	 /**
		 *<b>Description:</b>
		 *The run method which starts this thread until it dies.
		 */
	@Override
	public void run() {
		while(active) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {	
					controller.setTime(time);
				}
				});
			time--;
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void activate() {
		active = true;
		new Thread(this).start();
	}
	public void deactivate() {
		active = false;
	}
}

