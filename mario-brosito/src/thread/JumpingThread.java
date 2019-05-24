package thread;

import javafx.application.Platform;
import model.Mario;
import ui.GameController;

public class JumpingThread extends Thread {
	
	private GameController controller;
	
	public JumpingThread(GameController controller){
		this.controller = controller;

	}
	@Override
	public void run() {
		
		int counter = 0;
		while(counter <= 130 && !controller.isTouching()) {
			controller.moveImage(2);
			
			if(controller.getPressed().contains("D")) {
				controller.moveImage(1);
			}else if(controller.getPressed().contains("A")) {
				controller.moveImage(-1);
			}
			try {

				sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				counter +=10;
				controller.getMainGame().getLevelOne().getMario().setState(Mario.ISMOVINGUP);
		}
		controller.getMainGame().getLevelOne().getMario().setState(Mario.ISMOVINGDOWN);
		
		while(!controller.isTouching() && counter!=0){
			controller.moveImage(3);
			if(controller.getPressed().contains("D")) {
				controller.moveImage(1);
			}else if(controller.getPressed().contains("A")) {
				controller.moveImage(-1);
			}
		try {

			sleep(40);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

			counter -=10;
			controller.getMainGame().getLevelOne().getMario().setState(Mario.ISMOVINGDOWN);
		}

		controller.getMainGame().getLevelOne().getMario().setState(Mario.ISSTANDINGSTILL);
	
	}
	
	
	
	
}
