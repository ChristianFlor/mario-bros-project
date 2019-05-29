package model;

import javafx.geometry.Rectangle2D;

public class Mario extends Figure {

	public final static String IMAGE = "src/uiImg/player.png";
	public final static String BIGMARIO = "src/uiImg/BigPlayer.png";
	public final static String FIREMARIO = "src/uiImg/FirePlayer.png";
	public final static String SMALLSTARMARIO = "src/uiImg/SmallBlackMario.png";
	public final static String BIGSTARMARIO = "src/uiImg/BigBlackMario.png";
	public final static String ISMOVINGRIGHT = "Right";
	public final static String ISMOVINGLEFT = "Left";
	public final static String ISMOVINGUP = "Up";
	public final static String ISMOVINGDOWN = "Down";
	public final static String ISSTANDINGSTILL = "Still";
	public final static String ISDIAGONALRIGHT = "DiagonalRight";
	public final static String ISDIAGONALLEFT = "DiagonalLeft";
	public final static double GROUNDPOSITION = 384;
	public final static double MAXHEIGHT = 17;
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
	
	public String isColliding(double x2, double y2, double w2, double h2) {
		
		String collide = "";
		
	
		if((!(this.getPosX() > x2+w2) && !(this.getPosX()+this.getWidth() < x2)) && (!(this.getPosY() > y2+h2) && !(this.getPosY() + this.getHeight() < y2))){
			String ground = isGrounded(x2,y2,w2,y2);
			
			if(!ground.isEmpty() && (this.getPosX()+this.getWidth() >x2 || this.getPosX()<x2+w2)){
				collide = ISMOVINGDOWN;
				
			}
			 
			else if( this.getPosX()<=x2 && this.getPosY()+this.getHeight() > y2 && this.getPosY()+this.getHeight()<=y2+h2) {
			
				collide = ISMOVINGRIGHT;
				
			}
			
			else if(this.getPosX() >= x2 && this.getPosY()+this.getHeight() >= y2 && this.getPosY()+this.getHeight()<=y2+h2) {
				collide = ISMOVINGLEFT;
			}else if(this.getPosY() >=y2+h2 && this.getPosX()+this.getWidth() > x2 && this.getPosX() < x2+w2) {//&& this.getPosX()+16 >= x2 && this.getPosX()+16 <= x2+w2
				collide = ISMOVINGUP;
			}

		}
		return collide;
	}
	
	public String isGrounded(double x2, double y2, double w2, double h2) {
		String grounded = "";
		
		
		if((!(this.getPosX() > x2+w2) && !(this.getPosX()+this.getWidth() < x2)) && (!(this.getPosY() > y2+h2) && !(this.getPosY() + this.getHeight() < y2))){
			if(y2==416 || y2 == 448) {
				grounded = "floor";
			}
			
			else if(this.getPosY()+this.getHeight() <= y2) {
				grounded = "intersect";
			}
		}
		return grounded;
	}
	
	public boolean isThereSomethingInBetween(double x2, double y2, double w2, double h2, double yFinal, double yActual) {
		boolean isBetween = false;
		
		if((y2>=yFinal && y2+h2<= yActual) ) {//|| (y2>=this.getPosY()+this.getHeight() && y2+h2<=yFinal)
			
			if( this.getPosX()+this.getWidth() >= x2 && this.getPosX() <= x2+w2) {
				isBetween = true;
				
			}
		}
		
		return isBetween;
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

	 public boolean isEnemyNear(double posX) {
	    	boolean near = false;
	    	double distance = 700;
	    	double actualDistance =	Math.abs(this.getPosX() - posX);
	    		if(actualDistance <= distance) {
	    			near = true;
	    		}
	    	return near;
	 }
}
