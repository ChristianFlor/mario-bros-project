package thread;

import java.util.List;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import model.Figure;
import model.Mario;
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
		Mario m = null;
		if(controller.getCurrentLevel() == 1) {
			m = controller.getMainGame().getLevelOne().getMario();
		}else if(controller.getCurrentLevel() == 2) {
			m = controller.getMainGame().getLevelTwo().getMario();
		}else {
			m = controller.getMainGame().getLevelThree().getMario();
		}
		if(m.getPowerState() == null) {
			int counter = 0;
			while(counter < 8) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						controller.marioTouchSimpleBlock(rectangle, simpleBlock, 0);
					}
				});	
				counter++;
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			counter = 0;
			while(counter < 8) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						controller.marioTouchSimpleBlock(rectangle, simpleBlock, 1);
					}
				});	
				counter++;
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}else {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					controller.marioTouchSimpleBlock(rectangle, simpleBlock, 2);
				}
			});	
			List<Figure> sprites = null;
			if(controller.getCurrentLevel() == 1) {
				sprites = controller.getMainGame().getLevelOne().getFigures();
			}else if(controller.getCurrentLevel() == 2) {
				sprites = controller.getMainGame().getLevelTwo().getFigures();
			}else {
				sprites = controller.getMainGame().getLevelThree().getFigures();
			}
			sprites.remove(simpleBlock);
			boolean exit = false;
			for (int i = 0; i < sprites.size() && !exit; i++) {
				if(sprites.get(i) instanceof Mario) {
					if(controller.getCurrentLevel() == 1) {
						controller.getMainGame().getLevelOne().setMarioPosition(i);
						exit = true;
					}else if(controller.getCurrentLevel() == 2) {
						controller.getMainGame().getLevelTwo().setMarioPosition(i);
						exit = true;
					}else {
						controller.getMainGame().getLevelThree().setMarioPosition(i);
						exit = true;
					}
				}
			}
		}
	}
	
}
