package thread;

import ui.GameController;

public class MisteryBlockAnimation extends Thread{

	private GameController gameC;

	public MisteryBlockAnimation(GameController gui) {
		gameC = gui;
	}
	@Override
	public void run() {
		int cont=0;
		while(cont<10000) {
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
		cont++;
	}
} 