package thread;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import model.PowerUp;
import ui.GameController;

public class PowerUpThread extends Thread{

	private GameController controller;
	private Rectangle powerUpRectangle;
	private PowerUp powerUp;
	private int counter;
	
	public PowerUpThread(GameController c, Rectangle pr, PowerUp pu) {
		this.controller = c;
		this.powerUpRectangle = pr;
		this.powerUp = pu;
		counter = 0;
	}

	@Override
	public void run() {
		
		while(counter < 4) {
			
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						controller.exitPowerUp(powerUpRectangle, powerUp, counter);
					}
				});	
				counter++;
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
