package model;

import javafx.scene.shape.Rectangle;

public abstract class Enemy extends Figure implements CollidingFigure, GroundedFigure{

    /**
     * The moving state of this enemy.
     */
    private String state;
    
    /**
     * This attribute tells wether or not this enemy is dead.
     */
    private boolean dead;
    
    /**
	 * <b>Description:</b>
	 * This function allows to initialize a new enemy.
	 * @param posX the position in the X axis of the enemy in the level
	 * @param posY the position in the Y axis of the enemy in the level
	 * @param width the width of the enemy in pixels
	 * @param height the height of the enemy in pixels
	 */
    public Enemy(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        state = Mario.ISSTANDINGSTILL;
        dead = false;
    }

    /**
     * The next enemy in the list of enemies.
     */
    private Enemy nextEnemy;
    
    /**
     * <b>Description:</b>
     * This function obtains the next enemy in the list of enemies.
     * @return the nextEnemy in the list of enemies.
     */
    public Enemy getNextEnemy() {
        return nextEnemy;
    }

    /**
     * <b>Description:</b>
     * This function modifies the next enemy in the list of enemies.
     * @param nextEnemy The new next enemy to set in the list.
     */
    public void setNextEnemy(Enemy nextEnemy) {
        this.nextEnemy = nextEnemy;
    }
    
    /**
     * <b>Description:</b>
     * This function obtains the movement state of this enemy.
     * @return The state of movement of this enemy.
     */
    public String getState() {
        return state;
    }

	/**
	 * <b>Description:</b>
     * This function modifies the moving state of this enemy.
	 * @param state the state to set to this enemy's state.
	 */
	public void setState(String state) {
		this.state = state;
	}

    /**
     * <b>Description:</b>
     * This function determines whether or not an enemy is colliding.
     * @param x2 The X coordinate of the other figure.
     * @param y2 The Y coordinate of the other figure.
     * @param width The width of the other figure.
     * @param height The height of the other figure.
     * @return A boolean that says whether or not the enemy is colliding.
     */
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
    
    /**
     * <b>Description:</b>
     * This function determines whether or not an enemy is on a block.
     * @param x2 The X coordinate of the other figure.
     * @param y2 The Y coordinate of the other figure.
     * @param width The width of the other figure.
     * @param height The height of the other figure.
     * @return A boolean that says whether or not the enemy is on a block.
     */
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
	 * <b>Description:</b>
     * This function tells the live state of the enemy.
	 * @return The boolean that dictates whether or not this enemy is dead.
	 */
	public boolean isDead() {
		return dead;
	}

	/**
	 * <b>Description:</b>
     * This function modifies the live state of the enemy.
	 * @param The boolean that dictates whether or not this enemy is dead.
	 */
	public void setDead(boolean dead) {
		this.dead = dead;
	}
    
}