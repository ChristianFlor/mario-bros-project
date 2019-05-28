package thread;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import model.Figure;
import ui.GameController;

public class SpinningFireThread extends Thread{

	private GameController gameController;
	private Rectangle fireRec;
	private Figure fire;
	private int radius;
	private int centerX;
	private int centerY;
	private boolean active;
	private int degrees;
	
	public SpinningFireThread(GameController gameController, Rectangle fireRec, Figure fire, int radius, int centerX, int centerY) {
		this.gameController = gameController;
		this.fireRec = fireRec;
		this.fire = fire;
		this.radius = radius;
		this.centerX = centerX;
		this.centerY = centerY;
		active = true;
		degrees = 1;
	}

	@Override
	public void run() {
		while(active) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					gameController.spinFire(fireRec, fire, degrees, radius, centerX, centerY);
				}
			});
			
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			degrees++;
			if(degrees == 361)
				degrees = 0;
		}
	}
}
