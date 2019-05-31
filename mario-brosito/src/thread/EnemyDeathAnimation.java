package thread;

import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import model.Enemy;
import model.Goomba;
import model.Koopa;
import ui.GameController;

public class EnemyDeathAnimation extends Thread{
    
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
     * <b>Description:</b>
     * This function initializes a new enemy death animation thread.
     * @param controller The thread's game controller.
     * @param enemyRec The thread's rectangle from the user interface.
     * @param enemy The thread's enemy that is to be animated.
     */
    public EnemyDeathAnimation(GameController controller, Rectangle enemyRec, Enemy enemy) {
        this.controller = controller;
        this.enemyRec = enemyRec;
        this.enemy = enemy;
    }

    /**
	 *<b>Description:</b>
	 *The run method which starts this thread until it dies.
	 */
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

	/**
	 * <b>Description:</b>
	 * This function obtains the thread's enemy.
	 * @return The thread's enemy.
	 */
	public Enemy getEnemy() {
		return enemy;
	}

}