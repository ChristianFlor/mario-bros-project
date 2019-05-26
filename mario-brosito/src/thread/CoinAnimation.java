package thread;

import ui.GameController;

public class CoinAnimation extends Thread{

	private GameController gameC;

	public CoinAnimation(GameController gui) {
		gameC = gui;
	}
	@Override
	public void run() {
		int cont=0;
		while(cont<10000) {
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
		cont++;
	}
} 