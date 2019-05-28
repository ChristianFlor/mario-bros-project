package thread;

import javafx.application.Platform;
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
						
						boolean enter = false;
						while(controller.isFalling().isEmpty() && !controller.getJumping().isAlive()) {
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
								sleep(40);
								enter = true;
						}
						if(enter)
							controller.getMainGame().getLevelOne().getMario().setState(Mario.ISSTANDINGSTILL);
						
						if(controller.getPressed().contains("D") && !controller.getJumping().isAlive()) {
							
						 	if(controller.getMainGame().getLevelOne().getMario().getState().equals(Mario.ISMOVINGLEFT)) {
						 		Platform.runLater(new Runnable() {
									@Override
									public void run() {
								 		controller.changeMarioImage(8);
									}
						 		});
						 		sleep(350);
						 		
						 	}
						 	Platform.runLater(new Runnable() {
								@Override
								public void run() {
							 		controller.changeMarioImage(3);
								}
					 		});
							sleep(100);
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
							 		controller.changeMarioImage(1);
								}
					 		});
							sleep(100);
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
							 		controller.changeMarioImage(2);
								}
					 		});
							controller.getMainGame().getLevelOne().getMario().setState(Mario.ISMOVINGRIGHT);
							
							
						}else if(controller.getPressed().contains("W")) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
							 		controller.changeMarioImage(4);
								}
					 		});
							sleep(1100);
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
							 		controller.changeMarioImage(0);
								}
					 		});
						}else if(controller.getPressed().contains("A") && !controller.getJumping().isAlive() ) {
							
							if(controller.getMainGame().getLevelOne().getMario().getState().equals(Mario.ISMOVINGRIGHT)) {
								Platform.runLater(new Runnable() {
									@Override
									public void run() {
								 		controller.changeMarioImage(9);
									}
						 		});
						 		sleep(350);
						 		//controller.getMainGame().getLevelOne().getMario().setState(Mario.ISMOVINGLEFT);
						 	}
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
							 		controller.changeMarioImage(5);
								}
					 		});
							sleep(100);
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
							 		controller.changeMarioImage(6);
								}
					 		});
							sleep(100);
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
							 		controller.changeMarioImage(7);
								}
					 		});
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
