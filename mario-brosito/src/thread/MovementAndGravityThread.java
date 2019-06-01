package thread;

import javafx.application.Platform;
import model.Mario;
import ui.GameController;

public class MovementAndGravityThread extends Thread {
	
	/**
	 * The thread's game controller.
	 */
	private GameController controller;
	
	/**
	 * The attribute that tells whether or not this thread is active.
	 */
	private boolean active;
	
	/**
	 * <b>Description:</b>
	 * This function initializes a new movement and gravity thread.
	 * @param controller The thread's game controller.
	 */
	public MovementAndGravityThread(GameController controller) {
		this.controller = controller;
		this.active = true;
	}
	
	/**
	 *<b>Description:</b>
	 *The run method which starts this thread until it dies.
	 */
	@Override
	public void run() {
				while(active) {
					Mario m = null;
					if(controller.getCurrentLevel() == 1) {
			    		m = controller.getMainGame().getLevelOne().getMario();
			    	}else if(controller.getCurrentLevel() == 2) {
			    		m = controller.getMainGame().getLevelTwo().getMario();
			    	}else {
			    		m = controller.getMainGame().getLevelThree().getMario();
			    	}
					try {
						boolean enter = false;
						
						int counter=0;
						double z = Mario.GROUNDPOSITION;
							double pos = m.getPosY();
							
								double rest = Mario.GROUNDPOSITION-pos;
								double ecuation = (42 + Math.sqrt((42*42)-(4*1.2*rest)))/(2*1.2);
								ecuation = Math.round(ecuation);
								counter = (int) ecuation;
								
								while(controller.isFalling().isEmpty() && !controller.getJumping().isAlive()) {
									if(m.getPosY() +m.getHeight()*1.8 >= controller.getMainScene().getHeight()) {
										Platform.runLater(new Runnable() {

											@Override
											public void run() {
												controller.deadMario();
												
											}
											
										});
										
										
									}
									System.out.println(4);
									double jump = z+1.2*counter*counter-42*counter;
									jump = Math.round(jump);
									double jumpFinal = z+1.2*(counter+1)*(counter+1)-42*(counter+1);
									jumpFinal = Math.round(jumpFinal);
									m.setState(Mario.ISMOVINGDOWN);
									double aux = 0;
									while(!enter && m.getPosY() <= jumpFinal) {
									
										m.setPosY(m.getPosY()+1);
										
										if(controller.isTouching().equals(Mario.ISMOVINGDOWN)) {
										
											aux = m.getPosY();
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
									if(m.getState().equals(Mario.ISMOVINGLEFT)) {
								 		Platform.runLater(new Runnable() {
											@Override
											public void run() {
												
										 		controller.changeMarioImage(8);
											}
								 		});
								 		sleep(350);
								 		
									m.setState(Mario.ISDIAGONALRIGHT);
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
										m.setState(Mario.ISMOVINGRIGHT);
									}
								}else if(controller.getPressed().contains("A")) {
									if(m.getState().equals(Mario.ISMOVINGRIGHT)) {
										Platform.runLater(new Runnable() {
											@Override
											public void run() {
										 		controller.changeMarioImage(9);
											}
								 		});
								 		sleep(350);
								 		m.setState(Mario.ISMOVINGLEFT);
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
											m.setState(Mario.ISMOVINGLEFT);
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
	
	/**
	 * <b>Description:</b>
	 * This function deactivates this thread.
	 */
	public void deactivate() {
		active = false;
	}
	
	/**
	 * <b>Description:</b>
	 * This function reactivates this thread.
	 */
	public void activate() {
		active = true;
		new Thread(this).start();
	}
}
