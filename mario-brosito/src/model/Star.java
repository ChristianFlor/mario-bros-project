package model;

import javafx.scene.shape.Rectangle;

public class Star extends PowerUp{

	public static final String STAR = "src/uiImg/Star.png";
	
	public Star(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}

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
