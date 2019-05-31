package model;

public abstract class PowerUp extends Figure{

	/**
	  * <b>Description:</b>
		 * This function allows to initialize a new power up.
		 * @param posX the position in the X axis of the power up in the level
		 * @param posY the position in the Y axis of the power up in the level
		 * @param width the width of the power up in pixels
		 * @param height the height of the power up in pixels
		 */
	public PowerUp(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}

	/**
	 * <b>Description:</b>
	 * This function determines whether or not the power up is colliding with another block.
	 * @param x2 The other block's x coordinate.
	 * @param y2 The other block's y coordinate.
	 * @param w2 The other block's width coordinate.
	 * @param h2 The other block's height coordinate.
	 * @return True if there is a collision, false otherwise.
	 */
	public boolean powerUpIsColliding(double x2, double y2, double width, double height) {
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
}
