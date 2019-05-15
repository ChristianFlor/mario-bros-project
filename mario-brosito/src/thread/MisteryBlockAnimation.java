package thread;

import ui.brositoController;

public class MisteryBlockAnimation extends Thread{

	private brositoController brosito;

	public MisteryBlockAnimation(brositoController gui) {
		brosito = gui;
	}
	@Override
	public void run() {
		int cont=0;
		while(cont<10000) {
			brosito.setFill1();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		brosito.setFill2();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		brosito.setFill0();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		}
		brosito.setFill2();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cont++;
	}
} 