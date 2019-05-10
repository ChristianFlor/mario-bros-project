package thread;

import javafx.application.Platform;
import ui.brositoController;

public class MarioMovement extends Thread {
	
	private brositoController bc;
	
	public MarioMovement(brositoController controller) {
		bc = controller;
	}
	@Override
	public void run() {
		
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
			
				try {
					bc.moveImage(2);
					sleep(10000);
					bc.moveImage(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}

}
