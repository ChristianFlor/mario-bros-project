package thread;

import ui.GameController;

public class MisteryBlockAnimation extends Thread{

	/**
	 * The thread's game controller.
	 */
	private GameController gameC;

	/**
	 * The attribute that tells whether or not this thread is active.
	 */
	private boolean active;
	
	/**
	 * <b>Description:</b>
	 * This function initializes a new mystery block animation thread.
	 * @param controller The thread's game controller.
	 */
	public MisteryBlockAnimation(GameController gui) {
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
			gameC.setFill1();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gameC.setFill2();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gameC.setFill0();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		}
		gameC.setFill2();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <b>Description:</b>
	 * This function reactivates this thread.
	 */
	public void activate() {
		active = true;
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
	 * This function obtains the active status of this thread.
	 * @return The active status of this thread.
	 */
	public boolean getActive() {
		return active;
	}

} 