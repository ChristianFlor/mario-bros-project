package model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class Mario extends Figure {
	

	public final static String IMAGE = "src/uiImg/player.png";
	
	public final static String ISMOVINGRIGHT = "Right";
	public final static String ISMOVINGLEFT = "Left";
	public final static String ISMOVINGUP = "Up";
	public final static String ISMOVINGDOWN = "Down";
	public final static String ISSTANDINGSTILL = "Still";
	
	public Mario(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
		powerState = null;
		state = ISSTANDINGSTILL;
	}
	
	private PowerUp powerState;
	
	private String state;
	
	

	public PowerUp getPowerState() {
		return powerState;
	}

	public void setPowerState(PowerUp powerState) {
		this.powerState = powerState;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public boolean isColliding(double x2, double y2, double w2, double h2) {
		boolean collide = false;
		if((!(this.getPosX() > x2+w2) && !(this.getPosX()+this.getWidth() < x2)) && (!(this.getPosY() > y2+h2) && !(this.getPosY() + this.getHeight() < y2))) {
			collide = true;
			System.out.println(7);
			Point2D p1 = new Point2D(x2, y2);
			Point2D p2 = new Point2D(x2+w2, y2);
			Point2D p3 = new Point2D(x2, y2+h2);
			Point2D p4 = new Point2D(x2+w2, y2+h2);
			Rectangle2D marioRec = new Rectangle2D(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
			System.out.println(state); System.out.println(marioRec.contains(p3)); System.out.println(marioRec.contains(p4));
			if(state.equals(ISMOVINGRIGHT) && marioRec.contains(p1) && marioRec.contains(p3)) {
				this.setPosX(this.getPosX());
			}
			else if(state.equals(ISMOVINGLEFT) && marioRec.contains(p2) && marioRec.contains(p4))
				this.setPosX(this.getPosX());
			else if(state.equals(ISMOVINGDOWN) && (marioRec.contains(p1) || marioRec.contains(p2)))
				this.setPosY(this.getPosY());
			else if(state.equals(ISMOVINGUP) && (marioRec.contains(p3) || marioRec.contains(p4))) {
				this.setPosY(this.getPosY());
			
			}
			else
				collide = false;
		}
		return collide;
	}

	
	public PowerUp nextPowerUp() {
		PowerUp nextPower;
		if(powerState == null) {
			nextPower = new Mushroom(this.getPosX(), this.getPosY(), this.getWidth(),  this.getWidth());
		}else{
			nextPower = new Flower(this.getPosX(), this.getPosY(), this.getWidth(),  this.getWidth());
		}
		
		return nextPower;
	}
}
