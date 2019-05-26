package thread;

import ui.GameController;

public class CoinAnimation extends Thread{

	private GameController gameC;
	private boolean active;
	
	public CoinAnimation(GameController gui) {
		gameC = gui;
		active = true;
	}
	@Override
	public void run() {
		while(active) {
			gameC.setFillCoin1();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gameC.setFillCoin2();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gameC.setFillCoin0();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		}
		gameC.setFillCoin2();
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