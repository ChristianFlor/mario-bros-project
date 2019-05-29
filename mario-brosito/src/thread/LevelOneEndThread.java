package thread;

import javafx.application.Platform;
import ui.GameController;

public class LevelOneEndThread extends Thread{
	private GameController gameController;
	private int stage;
	private int condition;
	
	public LevelOneEndThread(GameController gameController) {
		super();
		this.gameController = gameController;
		stage = 0;
		condition = 0;
	}
	
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
				sleep(500);
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
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			condition = 2;
		}
		stage = 2;
		while(gameController.getMainMario().getX()<7200) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					gameController.endLevelOne(stage, condition);
				}
			});
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			condition = 3;
		}
	}
}
