package thread;

import javafx.scene.shape.Rectangle;
import model.MovingPlatform;
import ui.GameController;
import javafx.application.Platform;

public class PlatformThread extends Thread{

	/**
	 * The thread's game controller.
	 */
	private GameController gameC;
	
	/**
	 * The thread's platform that is to be animated.
	 */
	private MovingPlatform platform;
	
	/**
	 * The thread's rectangle from the user interface.
	 */
	private Rectangle platformRectangle;
	
	/**
	 * The attribute that tells whether or not this thread is active.
	 */
	private boolean active;
	
	/**
     * <b>Description:</b>
     * This function initializes a new platform thread.
     * @param gameC The thread's game controller.
     * @param platformRectangle The thread's rectangle from the user interface.
     * @param platform The thread's platform that is to be animated.
     */
	public PlatformThread(GameController gameC, MovingPlatform platform, Rectangle platformRectangle) {
		this.gameC = gameC;
		this.platform = platform;
		this.platformRectangle = platformRectangle;
		active = true;
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
					gameC.movePlatform(platformRectangle, platform);
				}
				
			});
			try {
				sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * <b>Description:</b>
	 * This function deactivates this thread.
	 */
	public void deactivate() {
		active = false;
		
	}
	/**
	 * <b>Description:</b>
	 * This function deactivates this thread.
	 */
	public void activate() {
		active = true;
		new Thread(this).start();
	}
}
