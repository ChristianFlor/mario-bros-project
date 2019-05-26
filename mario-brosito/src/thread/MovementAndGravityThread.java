package thread;

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
						
					/*	if(controller.getPressed().isEmpty()) {
							controller.getMainGame().getLevelOne().getMario().setState(Mario.ISSTANDINGSTILL);
						}*/
						
						boolean enter = false;
						while( controller.isFalling().isEmpty() && !controller.getJumping().isAlive()) {
								controller.moveImage(3);
								if(controller.getPressed().contains("D")) {
									controller.moveImage(1);

									controller.getMainGame().getLevelOne().getMario().setState(Mario.ISDIAGONALRIGHT);
								}else if(controller.getPressed().contains("A")) {
									controller.moveImage(-1);

									controller.getMainGame().getLevelOne().getMario().setState(Mario.ISDIAGONALLEFT);
								}else {
									controller.getMainGame().getLevelOne().getMario().setState(Mario.ISMOVINGDOWN);
								}
							try {
	
								sleep(40);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
	
								
								
								enter = true;
						}
						if(enter)
							controller.getMainGame().getLevelOne().getMario().setState(Mario.ISSTANDINGSTILL);
						
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		
	public void deactivate() {
		active = false;
	}
	
}
