package thread;

import java.util.List;

import javafx.application.Platform;
import model.Enemy;
import model.Mario;
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
						
						/*double rest = Mario.GROUNDPOSITION-controller.getMainGame().getLevelOne().getMario().getPosY();
						double ecuation = (42 + Math.sqrt((42*42)-4*1.2*rest))/2*1.2;
						System.out.println(ecuation);*/
						int counter = 10;
						double z = Mario.GROUNDPOSITION;
						
						while(counter<=Mario.GROUNDPOSITION && controller.isFalling().isEmpty() && !controller.getJumping().isAlive()) {
							
							double jump =z+3*counter*counter-42*counter;
							controller.moveImage(2, jump);
							sleep(100);
							counter++;
							
						}
						if(controller.getPressed().contains("D") && !controller.getJumping().isAlive()) {
							
						 	if(controller.getMainGame().getLevelOne().getMario().getState().equals(Mario.ISMOVINGLEFT)) {
						 		controller.changeMarioImage(8);
						 		sleep(350);
						 		
						 	}
							controller.changeMarioImage(3);
							sleep(100);
							controller.changeMarioImage(1);
							sleep(100);
							controller.changeMarioImage(2);
							controller.getMainGame().getLevelOne().getMario().setState(Mario.ISMOVINGRIGHT);
							
							
						}else if(controller.getPressed().contains("W")) {
							controller.changeMarioImage(4);
							sleep(1100);
							controller.changeMarioImage(0);
						}else if(controller.getPressed().contains("A") && !controller.getJumping().isAlive() ) {
							
							if(controller.getMainGame().getLevelOne().getMario().getState().equals(Mario.ISMOVINGRIGHT)) {
						 		controller.changeMarioImage(9);
						 		sleep(350);
						 		//controller.getMainGame().getLevelOne().getMario().setState(Mario.ISMOVINGLEFT);
						 	}
							controller.changeMarioImage(5);
							sleep(100);
							controller.changeMarioImage(6);
							sleep(100);
							controller.changeMarioImage(7);
						
								controller.getMainGame().getLevelOne().getMario().setState(Mario.ISMOVINGLEFT);
							
							
						}
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
				
			}
		
	public void deactivate() {
		active = false;
	}
	
}
