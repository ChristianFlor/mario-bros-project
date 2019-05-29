package model;

import javafx.scene.shape.Rectangle;

public abstract class Enemy extends Figure {

    private String state;
    
    private boolean dead;
    
    public Enemy(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        state = Mario.ISSTANDINGSTILL;
        dead = false;
    }

    private Enemy nextEnemy;
    
    /**
     * @return the nextEnemy
     */
    public Enemy getNextEnemy() {
        return nextEnemy;
    }

    /**
     * @param nextEnemy the nextEnemy to set
     */
    public void setNextEnemy(Enemy nextEnemy) {
        this.nextEnemy = nextEnemy;
    }
    
    public String getState() {
        return state;
    }

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

    public boolean enemyIsColliding(double x2, double y2, double width, double height) {
    	boolean collide = false;
    	if((!(this.getPosX() > x2+width) && !(this.getPosX()+this.getWidth() < x2)) && (!(this.getPosY() > y2+height) && !(this.getPosY() + this.getHeight() < y2))) {
    		if(this.getPosX()<=x2 && this.getPosY()+this.getHeight() > y2 && this.getPosY()+this.getHeight()<=y2+height) {
				collide = true;
			}else if(this.getPosX() >= x2 && this.getPosY()+this.getHeight() > y2 && this.getPosY()+this.getHeight()<=y2+height) {
				collide = true;
			}
    	}
    	return collide;
    }
    
    public boolean enemyIsGrounded(double x2, double y2, double width, double height) {
    	boolean grounded = false;
    	Rectangle enemyRec = new Rectangle(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    	
    	boolean intersect = enemyRec.intersects(x2, y2, width, height);
    	
    	if(intersect) {
    		if(y2==416 || y2 == 448) {
    			grounded = true;
    		}
    		else if(this.getPosY()+this.getHeight()<=y2) {
    			grounded = true;
    		}
    	}
    	
    	return grounded;
    }

	/**
	 * @return the dead
	 */
	public boolean isDead() {
		return dead;
	}

	/**
	 * @param dead the dead to set
	 */
	public void setDead(boolean dead) {
		this.dead = dead;
	}
    
}