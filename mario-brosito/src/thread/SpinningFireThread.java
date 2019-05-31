package thread;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import model.Figure;
import ui.GameController;

public class SpinningFireThread extends Thread{

	/**
	 * The game controller of this thread.
	 */
	private GameController gameController;
	
	/**
	 * The rectangle that represents the fire in the fire wheel.
	 */
	private Rectangle fireRec;
	
	/**
	 * The figure that represents the fire.
	 */
	private Figure fire;
	
	/**
	 * The radius of the wheel.
	 */
	private int radius;
	
	/**
	 * The x center coordinate of the fire wheel.
	 */
	private int centerX;
	
	/**
	 * The y center coordinate of the fire wheel.
	 */
	private int centerY;
	
	/**
	 * The attribute that tells whether or not this thread is active.
	 */
	private boolean active;
	
	/**
	 * The number of degrees the wheel is spun.
	 */
	private int degrees;
	
	/**
     * <b>Description:</b>
     * This function initializes a new spinning fire thread.
     * @param gameController The game controller of this thread.
     * @param fireRec The rectangle that represents the fire in the fire wheel.
     * @param fire The figure that represents the fire.
     * @param radius The radius of the wheel.
     * @param centerX The x center coordinate of the fire wheel.
     * @param centerY The y center coordinate of the fire wheel.
     */
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
