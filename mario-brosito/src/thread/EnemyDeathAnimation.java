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
    		int counter = 0;
    		/*double z = controller.getMainGame().getLevelOne().getMario().getPosY();//enemy.getPosY()+enemy.getHeight();
    		while(counter<=8) {
    			double jump = z+3*counter*counter-24*counter;
    			controller.moveImage(2, jump);
    			try {
					sleep(60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    			counter++;
    		}
    		*/
    		/*while(counter<7) {
    			controller.moveImage(3, 0);
    			try {
					sleep(60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			counter++;
    		}
    		counter=0;
    		while(controller.getMainGame().getLevelOne().getMario().getPosY()<=enemy.getPosY()+enemy.getHeight()) {
    			controller.moveImage(4, 0);
    			try {
					sleep(60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			counter++;
    		}*/
	    	
	    	//controller.moveImage(2);
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
  	     // controller.moveImage(2);
    	}
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