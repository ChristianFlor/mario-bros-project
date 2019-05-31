package model;

import javafx.scene.shape.Rectangle;

public class Star extends PowerUp{

	/**
	 * The default image of the star in the level.
	 */
	public static final String STAR = "src/uiImg/Star.png";
	
	/**
	 * <b>Description:</b>
     * This function initializes a new star.
	 * @param posX the position in the X axis of the star in the level
	 * @param posY the position in the Y axis of the star in the level
	 * @param width the width of the star in pixels
	 * @param height the height of the star in pixels
	 */
	public Star(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}

	/**
	 * <b>Description:</b>
	 * This function determines whether or not this star is on something in order to make it fall or not.
	 * @param posX The other block's x coordinate.
	 * @param posY The other block's y coordinate.
	 * @param width The other block's width coordinate.
	 * @param height The other block's height coordinate.
	 * @return The grounded state of the star.
	 */
	public boolean starIsGrounded(double posX, double posY, double width, double height) {
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
