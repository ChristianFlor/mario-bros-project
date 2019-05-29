package model;

import javafx.scene.shape.Rectangle;

public class OneUp extends PowerUp {


	public final static String ONEUP = "src/uiImg/1UP.png";
	
	public OneUp(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);

	}

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
