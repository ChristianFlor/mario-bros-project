package thread;

import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import model.Enemy;
import ui.GameController;

public class EnemyThread extends Thread{
    
	/**
     * The thread's game controller.
     */
    private GameController controller;
    
    /**
     * The thread's rectangle from the user interface.
     */
    private Rectangle enemyRec;
    
    /**
     * The thread's enemy that is to be animated.
     */
    private Enemy enemy;
    
    /**
	 * The attribute that tells whether or not this thread is active.
	 */
    private boolean active;
        
    /**
     * The changer which is the image that is to be set.
     */
    private int changer;
    
    /**
     * <b>Description:</b>
     * This function initializes a new enemy thread.
     * @param controller The thread's game controller.
     * @param enemyRec The thread's rectangle from the user interface.
     * @param enemy The thread's enemy that is to be animated.
     */
    public EnemyThread(GameController controller, Rectangle enemyRec, Enemy enemy) {
        this.controller = controller;
        this.enemyRec = enemyRec;
        this.active = true;
        this.enemy = enemy;
        changer = 0;
    }

    /**
   	 *<b>Description:</b>
   	 *The run method which starts this thread until it dies.
   	 */	
    public void run() {
        while(active) {
        	Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {
						controller.moveEnemy(enemyRec, enemy, changer);
						if(changer == 0)
							changer = 1;
						else
							changer = 0;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
        		
        	});
        	try {
        		sleep(200);
        	} catch(InterruptedException e) {
        		e.printStackTrace();
        	}
            
        }
    }

    /**
	 * <b>Description:</b>
	 * This function obtains the thread's enemy.
	 * @return The thread's enemy.
	 */
	public Enemy getEnemy() {
		return enemy;
	}
	
	/**
	 * <b>Description:</b>
	 * This function reactivates this thread.
	 */
	public void activate() {
		active = true;
		new Thread(this).start();
	}
	
	/**
	 * <b>Description:</b>
	 * This function deactivates this thread.
	 */
	public void deactivate() {
		active = false;
	}
}