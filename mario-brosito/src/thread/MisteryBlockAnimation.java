package thread;

import ui.GameController;

public class MisteryBlockAnimation extends Thread{

	private GameController gameC;
	private boolean active;
	
	public MisteryBlockAnimation(GameController gui) {
		gameC = gui;
		active = true;
	}
	@Override
	public void run() {
		while(active) {
			gameC.setFill1();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gameC.setFill2();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gameC.setFill0();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		}
		gameC.setFill2();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void deactivate() {
		active = false;
	}
} 