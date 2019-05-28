package thread;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import model.Flower;
import model.Mushroom;
import model.PowerUp;
import ui.GameController;

public class PowerUpThread extends Thread{

	private GameController controller;
	private Rectangle powerUpRectangle;
	private PowerUp powerUp;
	private boolean active;
	private int counter;
	
	public PowerUpThread(GameController c, Rectangle pr, PowerUp pu) {
		this.controller = c;
		this.powerUpRectangle = pr;
		this.powerUp = pu;
		active = true;
		counter = 0;
	}

	@Override
	public void run() {
		counter = 0;
		while(counter < 4) {
			if(counter == 0) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						controller.exitPowerUp(powerUpRectangle, powerUp, 0);
					}
				});	
			}else {
				controller.exitPowerUp(powerUpRectangle, powerUp, counter);
			}
			counter++;
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		counter = 0;
		if(powerUp instanceof Flower) {
			while(active) {
				counter++;
				controller.animateFlower(powerUpRectangle, counter);
				
				if(counter == 3)
					counter = -1;
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}else if(powerUp instanceof Mushroom) {
			while(active) {
				controller.moveMushroom(powerUp, powerUpRectangle, counter);
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						if(controller.figureIsTouching(powerUp)) {
							if(counter == 0)
								counter = 1;
							else
								counter = 0;
						}
					}
					
				});
				
			}
		}
	}
	
	public PowerUp getPowerUp() {
		return powerUp;
	}
	public void deactivate() {
		active = false;
		if(powerUp instanceof Mushroom) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					controller.moveMushroom(powerUp, powerUpRectangle, -1);
				}
			});
		}else if(powerUp instanceof Flower) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					controller.animateFlower(powerUpRectangle, -1);
				}
			});
		}
	}
}
