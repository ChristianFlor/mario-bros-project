package thread;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import model.Flower;
import model.Mushroom;
import model.OneUp;
import model.PowerUp;
import model.Star;
import ui.GameController;

public class MisteryBlockHitThread extends Thread{

	/**
	 * The thread's game controller.
	 */
	private GameController controller;
	
	/**
	 * The thread's rectangle from the user interface.
	 */
	private Rectangle powerUpRectangle;
	
	/**
	 * The thread's power up that is to be animated.
	 */
	private PowerUp powerUp;
	
	/**
	 * The attribute that tells whether or not this thread is active.
	 */
	private boolean active;
		
	/**
	 * The counter that determines which stage of the animation is to be displayed.
	 */
	private int counter;
	
	/**
	 * The orientation of the power up.
	 */
	private int orientation;
	
	 /**
     * <b>Description:</b>
     * This function initializes a new mystery block hit thread.
     * @param c The thread's game controller.
     * @param pr The thread's rectangle from the user interface.
     * @param pu The thread's power up that is to be animated.
     */
	public MisteryBlockHitThread(GameController c, Rectangle pr, PowerUp pu) {
		this.controller = c;
		this.powerUpRectangle = pr;
		this.powerUp = pu;
		active = true;
		counter = 0;
		orientation = 0;
	}

	 /**
	 *<b>Description:</b>
	 *The run method which starts this thread until it dies.
	 */
	@Override
	public void run() {
		if(powerUp != null) {
		counter = 0;
		while(counter < 4) {
			if(counter == 0) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						controller.exitPowerUp(powerUpRectangle, powerUp, 0);
					}
				});	
			}else {
				controller.exitPowerUp(powerUpRectangle, powerUp, counter);
			}
			counter++;
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		counter = 0;
		if(powerUp instanceof Flower) {
			while(active) {
				counter++;
				controller.animateFlower(powerUpRectangle, counter);
				
				if(counter == 3)
					counter = -1;
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						controller.figureIsTouching(powerUp);
					}
				});
			}
		}else if(powerUp instanceof Mushroom || powerUp instanceof OneUp) {
			while(active) {
				controller.moveMushroom(powerUp, powerUpRectangle, counter);
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if(controller.figureIsTouching(powerUp)) {
							if(counter == 0)
								counter = 1;
							else
								counter = 0;
						}
					}
				});
			}
		}else if(powerUp instanceof Star){
			System.out.println(21);
			int moment = 0;
			while(active) {
				counter++;
				int o = 0;
				if(moment == 1) {
					controller.animateStar(powerUp, powerUpRectangle, counter, moment, o);
					counter = -1;
					moment++;
					try {
						sleep(2700);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					controller.animateStar(powerUp, powerUpRectangle, counter, moment, o);
					
					if(counter == 3) {
						counter = -1;
						moment++;
					}
					
						try {
							sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							if(controller.figureIsTouching(powerUp)) {
								if(orientation == 0)
									orientation = 1;
								else
									orientation = 0;
							}
						}
					});
				}
				
			}
		}
		}else {
			try {
				counter = -1;
				int exit = 0;
				while(active) {
					if(exit <= 5) {
						Platform.runLater(new Runnable() {
	
							@Override
							public void run() {
								controller.animateMisteryBlockCoin(powerUpRectangle, counter, 0);
							}
							
						});
					}else {
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								controller.animateMisteryBlockCoin(powerUpRectangle, counter, 1);
							}
							
						});
					}
					sleep(100);
					counter++;
					if(counter == 4) {
						counter = 0;
					}
					exit ++;
					if(exit == 10) {
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								controller.animateMisteryBlockCoin(powerUpRectangle, -2, 1);
							}
							
						});
						deactivate();
					}
				}
					
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * <b>Description:</b>
	 * This function obtains the power up of this thread.
	 * @return The thread's power up.
	 */
	public PowerUp getPowerUp() {
		return powerUp;
	}
	
	/**
	 * <b>Description:</b>
	 * This function deactivates this thread.
	 */
	public void deactivate() {
		active = false;
		if(powerUp instanceof Mushroom || powerUp instanceof OneUp) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					controller.moveMushroom(powerUp, powerUpRectangle, -1);
				}
			});
		}else if(powerUp instanceof Flower || powerUp == null) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					controller.animateFlower(powerUpRectangle, -1);
				}
			});
		}
	}
}
