package model;

import javafx.scene.shape.Rectangle;

public class OneUp extends PowerUp {

	/**
	 * The default image of the one up power up.
	 */
	public final static String ONEUP = "src/uiImg/1UP.png";
	
	/**
	  * <b>Description:</b>
		 * This function allows to initialize a new one up.
		 * @param posX the position in the X axis of the one up in the level
		 * @param posY the position in the Y axis of the one up in the level
		 * @param width the width of the one up in pixels
		 * @param height the height of the one up in pixels
		 */
	public OneUp(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);

	}

	/**
	 * <b>Description:</b>
	 * This function determines whether or not this one up is on something in order to make it fall or not.
	 * @param posX The other block's x coordinate.
	 * @param posY The other block's y coordinate.
	 * @param width The other block's width coordinate.
	 * @param height The other block's height coordinate.
	 * @return The grounded state of the one up.
	 */
	public boolean oneUpIsGrounded(double posX, double posY, double width, double height) {
		boolean grounded = false;
    	Rectangle enemyRec = new Rectangle(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    	
    	boolean intersect = enemyRec.intersects(posX, posY, width, height);
    	
    	if(intersect) {
    		if(posY == 416 || posY == 448) {
    			grounded = true;
    		}
    		else if(this.getPosY()+this.getHeight()<=posY) {
    			grounded = true;
    		}
    	}
    	
    	return grounded;
	}
	
}
