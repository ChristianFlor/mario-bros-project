package thread;

import javafx.application.Platform;
import ui.GameController;

public class MovementAndGravityThread extends Thread {
	
	private GameController controller;
	private boolean active;
	
	/**
	 * @param controller
	 * @param active
	 */
	public MovementAndGravityThread(GameController controller) {
		this.controller = controller;
		this.active = true;
	}
	
	
	@Override
	public void run() {
		
				while(active) {
					
					
					try {
						if(controller.getPressed().contains("D")) {
						
							controller.changeMarioImage(3);
							sleep(100);
							controller.changeMarioImage(1);
							sleep(100);
							controller.changeMarioImage(2);
							
						}else if(controller.getPressed().contains("W")) {
							controller.changeMarioImage(4);
							sleep(1100);
							controller.changeMarioImage(0);
						}else if(controller.getPressed().contains("A")) {
							controller.changeMarioImage(5);
							sleep(100);
							controller.changeMarioImage(6);
							sleep(100);
							controller.changeMarioImage(7);
						}
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		
	


	public void desactive() {
		active = false;
	}
	
}
