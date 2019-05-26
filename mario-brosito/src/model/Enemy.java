package model;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public abstract class Enemy extends Figure {

    private String state;
    
    public Enemy(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        state = Mario.ISSTANDINGSTILL;
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
    		Rectangle enemyRec = new Rectangle(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    		Point2D p1 = new Point2D(x2, y2+(height/2));
    		Point2D p2 = new Point2D(x2+width, y2+(height/2));
    		if(enemyRec.contains(p1) || enemyRec.contains(p2)) {
    			collide = true;
    		}
    	}
    	return collide;
    }
    
}