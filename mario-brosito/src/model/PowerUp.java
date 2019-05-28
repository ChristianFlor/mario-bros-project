package model;

public abstract class PowerUp extends Figure{

	public PowerUp(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}

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
