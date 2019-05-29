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
						
						int counter=0;
						double z = Mario.GROUNDPOSITION;
							double pos = controller.getMainGame().getLevelOne().getMario().getPosY();
							
								double rest = Mario.GROUNDPOSITION-pos;
								double ecuation = (42 + Math.sqrt((42*42)-(4*1.2*rest)))/(2*1.2);
								ecuation = Math.round(ecuation);
								counter = (int) ecuation;
								
								while(counter <= 35 && controller.isFalling().isEmpty() && !controller.getJumping().isAlive()) {
									
									double jump = z+1.2*counter*counter-42*counter;
									jump = Math.round(jump);
									double jumpFinal = z+1.2*(counter+1)*(counter+1)-42*(counter+1);
									jumpFinal = Math.round(jumpFinal);
									controller.getMainGame().getLevelOne().getMario().setState(Mario.ISMOVINGDOWN);
									double aux = 0;
									while(!enter && controller.getMainGame().getLevelOne().getMario().getPosY() <= jumpFinal) {
									
										controller.getMainGame().getLevelOne().getMario().setPosY(controller.getMainGame().getLevelOne().getMario().getPosY()+1);
										
										if(controller.isTouching().equals(Mario.ISMOVINGDOWN)) {
										
											aux = controller.getMainGame().getLevelOne().getMario().getPosY();
											enter = true;
										}
									}
									
									if(enter) {
										controller.moveImage(2, aux);
										//counter++;
										break;
									}
									controller.moveImage(2, jump);
									counter++;
									sleep(60);
									
							
								}
						
							if(controller.getPressed().contains("D")) {
									Platform.runLater(new Runnable() {

										@Override
										public void run() {
											controller.moveImage(1,0);
										}
										
									});
									if(controller.getMainGame().getLevelOne().getMario().getState().equals(Mario.ISMOVINGLEFT)) {
								 		Platform.runLater(new Runnable() {
											@Override
											public void run() {
												
										 		controller.changeMarioImage(8);
											}
								 		});
								 		sleep(350);
								 		
									controller.getMainGame().getLevelOne().getMario().setState(Mario.ISDIAGONALRIGHT);
									}else {
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
									}
								}else if(controller.getPressed().contains("A")) {
									if(controller.getMainGame().getLevelOne().getMario().getState().equals(Mario.ISMOVINGRIGHT)) {
										Platform.runLater(new Runnable() {
											@Override
											public void run() {
										 		controller.changeMarioImage(9);
											}
								 		});
								 		sleep(350);
								 		controller.getMainGame().getLevelOne().getMario().setState(Mario.ISMOVINGLEFT);
								 	}else {
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
								}
								else if(controller.getPressed().contains("W")) {
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
								
								}
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
				
				
			}
	}
	public void deactivate() {
		active = false;
	}
	public void activate() {
		active = true;
		new Thread(this).start();
	}
}
