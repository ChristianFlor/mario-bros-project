package model;

import javafx.scene.shape.Rectangle;

public class Mushroom extends PowerUp{
	

	public final static String IMAGE = "uiImg/Mushroom.png";
	
	public Mushroom(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}

	public boolean mushroomIsGrounded(double posX, double posY, double width, double height) {
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
	
	public boolean mushroomIsColliding(double x2, double y2, double width, double height) {
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
