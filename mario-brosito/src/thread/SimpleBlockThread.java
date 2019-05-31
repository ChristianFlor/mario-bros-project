package thread;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import model.SimpleBlock;
import ui.GameController;

public class SimpleBlockThread extends Thread{
	
	/**
	 * The thread's game controller.
	 */
	private GameController controller;
	
	/**
	 * The thread's simple block that is to be animated.
	 */
	private SimpleBlock simpleBlock;
	
	/**
	 * The thread's rectangle from the user interface.
	 */
	private Rectangle rectangle;
	
	/**
     * <b>Description:</b>
     * This function initializes a new platform thread.
     * @param c The thread's game controller.
     * @param r The thread's rectangle from the user interface.
     * @param sb The thread's simple block that is to be animated.
     */
	public SimpleBlockThread(GameController c, SimpleBlock sb, Rectangle r) {
		this.controller = c;
		this.simpleBlock = sb;
		this.rectangle = r;
	}
	
	/**
	 *<b>Description:</b>
	 *The run method which starts this thread until it dies.
	 */
	@Override
	public void run() {
		
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						controller.marioTouchSimpleBlock(rectangle, simpleBlock);
					}
				});	
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		
	}
	
}
