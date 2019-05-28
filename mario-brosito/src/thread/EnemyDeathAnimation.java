package thread;

import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import model.Enemy;
import model.Goomba;
import model.Koopa;
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

    @Override
    public void run() {
    	if(enemy instanceof Goomba) {
	    	controller.moveImage(2,0);
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
	      controller.moveImage(2,0);
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
    	}else if(enemy instanceof Koopa) {
    		controller.moveImage(2,0);
  	      Platform.runLater(new Runnable() {
  			@Override
  			public void run() {
  				controller.changeEnemyImage(4, enemy, enemyRec);
  			}
  	      });
  	      try {
  			sleep(200);
  	      } catch (InterruptedException e) {
  			e.printStackTrace();
  	      }
  	      controller.moveImage(2,0);
    	}
    	controller.moveImage(2,0);
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
      controller.moveImage(2,0);
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