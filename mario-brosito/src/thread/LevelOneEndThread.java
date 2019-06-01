package thread;

import javafx.application.Platform;
import ui.GameController;

public class LevelOneEndThread extends Thread{
	
	/**
	 * The thread's game controller.
	 */
	private GameController gameController;
	
	/**
	 * The stage in which the animation is at.
	 */
	private int stage;
	
	/**
	 * The conditional that determines which animation is to be shown.
	 */
	private int condition;
	
	/**
	 * <b>Description:</b>
	 * This function initializes a new level one end thread.
	 * @param controller The thread's game controller.
	 */
	public LevelOneEndThread(GameController gameController) {
		super();
		this.gameController = gameController;
		stage = 0;
		condition = 0;
	}
	
	 /**
	 *<b>Description:</b>
	 *The run method which starts this thread until it dies.
	 */
	@Override
	public void run() {
			while(gameController.getMainMario().getY()<352) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						gameController.endLevelOne(stage, condition);
					}
				});
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				condition = 1;
			}
			stage = 1;
			while(gameController.getMainMario().getX()<6528 && gameController.getMainMario().getY()<384) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						gameController.endLevelOne(stage, condition);
					}
				});
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				condition = 2;
			}
			stage = 2;
			while(gameController.getMainMario().getX()<=6800) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						gameController.endLevelOne(stage, condition);
					}
				});
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				condition = 3;
			}
			stage = 3;
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					gameController.endLevelOne(stage, condition);
				}
			});
		
	}
	
}
