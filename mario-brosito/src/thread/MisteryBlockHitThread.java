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

	private GameController controller;
	private Rectangle powerUpRectangle;
	private PowerUp powerUp;
	private boolean active;
	private int counter;
	private int orientation;
	
	public MisteryBlockHitThread(GameController c, Rectangle pr, PowerUp pu) {
		this.controller = c;
		this.powerUpRectangle = pr;
		this.powerUp = pu;
		active = true;
		counter = 0;
		orientation = 0;
	}

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
	
	public PowerUp getPowerUp() {
		return powerUp;
	}
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
