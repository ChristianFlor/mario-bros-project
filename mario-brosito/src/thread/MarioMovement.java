package thread;

import javafx.application.Platform;
import model.Mario;
import ui.brositoController;

public class MarioMovement extends Thread {
	
	private brositoController controller;
	private int keys;
	private boolean active;
	
	public MarioMovement(brositoController controller, int keys ){
		this.controller = controller;
		this.keys = keys;
		active = true;
	}
	@Override
	public void run() {
		double layoutY = controller.getMainMario().getY();
		int counter = 0;
		
			while(counter <= 130 && !controller.isTouching()) {
				
				Platform.runLater(new Runnable() {

						@Override
						public void run() {
							if(keys == 1) {
								controller.moveImage(2);
								controller.moveImage(1);
							}else if(keys == 0) {
								controller.moveImage(2);
							}else if(keys == -1) {
								controller.moveImage(2);
								controller.moveImage(-1);
							}
					}
				});
				try {

					sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				layoutY = controller.getMainMario().getY();
				counter +=10;
			}
			while(!controller.isTouching()){
				if(keys == 1) {
					controller.moveImage(3);
					controller.moveImage(1);
				}else if(keys == 0) {
					controller.moveImage(3);
				}else if(keys == -1) {
					controller.moveImage(3);
					controller.moveImage(-1);
				}

				try {

					sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				layoutY = controller.getMainMario().getY();
				counter -=10;
			}

			controller.getMainGame().getLevelOne().getMario().setState(Mario.ISMOVINGDOWN);
			
		
	}
	
	public void desactive() {
		active = false;
	}
	
	
}
