package thread;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import model.Enemy;
import ui.GameController;

public class EnemyThread extends Thread{
    
    private GameController controller;
    private Rectangle enemyRec;
    private Enemy enemy;
    private boolean active;
    
    public EnemyThread(GameController controller, Rectangle enemyRec, Enemy enemy) {
        this.controller = controller;
        this.enemyRec = enemyRec;
        this.active = true;
        this.enemy = enemy;
    }

    public void run() {
        while(active) {
        	Platform.runLater(new Runnable() {
				@Override
				public void run() {
					controller.moveEnemy(enemyRec, enemy);
				}
        		
        	});
        	try {
        		sleep(500);
        	} catch(InterruptedException e) {
        		e.printStackTrace();
        	}
            
        }
    }

	public Enemy getEnemy() {
		return enemy;
	}
}