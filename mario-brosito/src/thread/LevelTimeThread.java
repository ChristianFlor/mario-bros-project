package thread;

import javafx.application.Platform;
import ui.GameController;

public class LevelTimeThread extends Thread {
	
	private GameController controller;
	private boolean active;
	
	public LevelTimeThread(GameController c) {
		controller = c;
		active = true;
	}
	
	@Override
	public void run() {
		
		while(active) {
			Platform.runLater(new Runnable() {
				int counter = 401;
				@Override
				public void run() {
						while(counter>0) {
						counter--;
						controller.setTime(counter);
						}
						
				}
			});
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				
		}
		
	}
}
