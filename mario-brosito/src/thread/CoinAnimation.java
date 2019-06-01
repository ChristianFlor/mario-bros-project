package thread;

import ui.GameController;

public class CoinAnimation extends Thread{

	/**
	 * This thread's main game controller.
	 */
	private GameController gameC;
	
	/**
	 * The attribute that tells whether or not this thread is active.
	 */
	private boolean active;
	
	/**
	 * <b>Description:</b>
	 * This function initializes a new coin animation thread.
	 * @param gui The game controller.
	 */
	public CoinAnimation(GameController gui) {
		gameC = gui;
		active = true;
	}
	
	/**
	 *<b>Description:</b>
	 *The run method which starts this thread until it dies.
	 */
	@Override
	public void run() {
		while(active) {
			gameC.setFillCoin1();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gameC.setFillCoin2();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gameC.setFillCoin0();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		}
		gameC.setFillCoin2();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
	}

} 