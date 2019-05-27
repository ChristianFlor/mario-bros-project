package thread;

import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import model.Enemy;
import ui.GameController;

public class EnemyDeathAnimation extends Thread{
    
    private GameController controller;
    private Rectangle enemyRec;
    private Enemy enemy;
    
    public EnemyDeathAnimation(GameController controller, Rectangle enemyRec, Enemy enemy) {
        this.controller = controller;
        this.enemyRec = enemyRec;
        this.enemy = enemy;
    }

    public void run() {
    	controller.moveImage(2);
      Platform.runLater(new Runnable() {
		@Override
		public void run() {
			controller.changeEnemyImage(1, enemy, enemyRec);
		}
      });
      try {
		sleep(200);
      } catch (InterruptedException e) {
		e.printStackTrace();
      }
      controller.moveImage(2);
      Platform.runLater(new Runnable() {
  		@Override
  		public void run() {
  			
  			controller.changeEnemyImage(2, enemy, enemyRec);
  		}
        });
      try {
		sleep(200);
      } catch (InterruptedException e) {
		e.printStackTrace();
      }
      
      Platform.runLater(new Runnable() {
  		@Override
  		public void run() {
  			controller.changeEnemyImage(3, enemy, enemyRec);
  		}
        });
      try {
    	  sleep(200);
      } catch (InterruptedException e) {
		e.printStackTrace();
      }
    }

	public Enemy getEnemy() {
		return enemy;
	}

}