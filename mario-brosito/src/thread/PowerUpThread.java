package thread;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import model.PowerUp;
import ui.GameController;

public class PowerUpThread extends Thread{

	private GameController controller;
	private Rectangle powerUpRectangle;
	private PowerUp powerUp;
	
	public PowerUpThread(GameController c, Rectangle pr, PowerUp pu) {
		this.controller = c;
		this.powerUpRectangle = pr;
		this.powerUp = pu;
	}

	@Override
	public void run() {
		
		int counter = 0;
		while(counter < 4) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					controller.exitPowerUp(powerUpRectangle, powerUp);
				}
			});
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
