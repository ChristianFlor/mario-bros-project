package thread;

import javafx.application.Platform;
import ui.GameController;

public class LevelTimeThread extends Thread {
	
	private GameController controller;
	private boolean active;
	private int time;
	
	public LevelTimeThread(GameController c) {
		controller = c;
		active = true;
		time = 400;
	}
	
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

