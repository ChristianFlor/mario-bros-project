package thread;

import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import model.Enemy;
import ui.GameController;

public class EnemyThread extends Thread{
    
    private GameController controller;
    private Rectangle enemyRec;
    private Enemy enemy;
    private boolean active;
    private int changer;
    
    public EnemyThread(GameController controller, Rectangle enemyRec, Enemy enemy) {
        this.controller = controller;
        this.enemyRec = enemyRec;
        this.active = true;
        this.enemy = enemy;
        changer = 0;
    }

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
        		sleep(150);
        	} catch(InterruptedException e) {
        		e.printStackTrace();
        	}
            
        }
    }

	public Enemy getEnemy() {
		return enemy;
	}

	public void deactivate() {
		active = false;
	}
}