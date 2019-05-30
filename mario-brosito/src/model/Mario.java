package model;

import javafx.geometry.Rectangle2D;

public class Mario extends Figure {

	/**
	 * The default mario image sprite sheet.
	 */
	public final static String IMAGE = "src/uiImg/player.png";
	
	/**
	 * The image of mario when he's in big form.
	 */
	public final static String BIGMARIO = "src/uiImg/BigPlayer.png";
	
	/**
	 * The image of mario when he's in fire form.
	 */
	public final static String FIREMARIO = "src/uiImg/FirePlayer.png";
	
	/**
	 * The image of mario when he's small and in star form.
	 */
	public final static String SMALLSTARMARIO = "src/uiImg/SmallBlackMario.png";
	
	/**
	 * The image of mario when he's big and in star form.
	 */
	public final static String BIGSTARMARIO = "src/uiImg/BigBlackMario.png";
	
	/**
	 * The image of mario when he dies.
	 */
	public final static String SMALLDEADMARIO = "uiImg/SmallDeadMario.png";
	
	/**
	 * The state of mario when he's moving right.
	 */
	public final static String ISMOVINGRIGHT = "Right";
	
	/**
	 * The state of mario when he's moving left.
	 */
	public final static String ISMOVINGLEFT = "Left";
	
	/**
	 * The state of mario when he's moving up.
	 */
	public final static String ISMOVINGUP = "Up";
	
	/**
	 * The state of mario when he's moving down.
	 */
	public final static String ISMOVINGDOWN = "Down";
	
	/**
	 * The state of mario when he's standing still.
	 */
	public final static String ISSTANDINGSTILL = "Still";
	
	/**
	 * The state of mario when he's moving diagonally right.
	 */
	public final static String ISDIAGONALRIGHT = "DiagonalRight";
	
	/**
	 * The state of mario when he's moving diagonally left.
	 */
	public final static String ISDIAGONALLEFT = "DiagonalLeft";
	
	/**
	 * The ground position of mario.
	 */
	public final static double GROUNDPOSITION = 384;
	
	/**
	 * The max height of mario..
	 */
	public final static double MAXHEIGHT = 17;
	
	 /**
	  * <b>Description:</b>
		 * This function allows to initialize a new Mario.
		 * @param posX the position in the X axis of the enemy in the level
		 * @param posY the position in the Y axis of the enemy in the level
		 * @param width the width of the enemy in pixels
		 * @param height the height of the enemy in pixels
		 */
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
