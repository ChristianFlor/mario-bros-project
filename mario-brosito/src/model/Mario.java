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
		 * @param posX the position in the X axis of Mario in the level
		 * @param posY the position in the Y axis of Mario in the level
		 * @param width the width of Mario in pixels
		 * @param height the height of Mario in pixels
		 */
	public Mario(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
		powerState = null;
		state = ISSTANDINGSTILL;
	}
	
	/**
	 * This attribute tells the power state of Mario. That is, whether or not Mario has a power up on him.
	 */
	private PowerUp powerState;
	
	/**
	 * The moving state of Mario at any given moment.
	 */
	private String state;

	/**
	 * <b>Description:</b>
	 * This function obtains the power state of Mario.
	 * @return The power state that Mario has at the moment of this function being called.
	 */
	public PowerUp getPowerState() {
		return powerState;
	}

	/**
	 * <b>Description:</b>
	 * This function modifies Mario's power state.
	 * @param powerState The new power state of Mario.
	 */
	public void setPowerState(PowerUp powerState) {
		this.powerState = powerState;
	}

	/**
	 * <b>Description:</b>
	 * This function obtains the moving state of Mario.
	 * @return The moving state of Mario at the moment.
	 */
	public String getState() {
		return state;
	}

	/**
	 * <b>Description:</b>
	 * This function modifies the moving state of Mario.
	 * @param state The new moving state of Mario.
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * <b>Description:</b>
	 * This function determines whether or not mario is colliding with another block and also in what way he's colliding.
	 * In other words, it tells if Mario is colliding from above, below, right or left of him.
	 * @param x2 The other block's x coordinate.
	 * @param y2 The other block's y coordinate.
	 * @param w2 The other block's width coordinate.
	 * @param h2 The other block's height coordinate.
	 * @return A string representing the direction in which Mario is colliding or an empty String is Mario is not colliding.
	 */
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
	
	/**
	 * <b>Description:</b>
	 * This function determines whether or not Mario is standing on something and if he is, it tells if it's with the floor or not.
	 * @param x2 The other block's x coordinate.
	 * @param y2 The other block's y coordinate.
	 * @param w2 The other block's width coordinate.
	 * @param h2 The other block's height coordinate.
	 * @return A string representing a collision with the floor or just a collision, in case of there being no collision it returns an empty string.
	 */
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
	
	/**
	 * <b>Description:</b>
	 * This function is used for Mario's jump to detect if Mario is in between something in every interval of the jump, this information is used for collision detection.
	 * @param x2 The other block's x coordinate.
	 * @param y2 The other block's y coordinate.
	 * @param w2 The other block's width coordinate.
	 * @param h2 The other block's height coordinate.
	 * @param yFinal The final y coordinate of Mario when he jumps.
	 * @param yActual The current y coordinate of Mario in the jump.
	 * @return True if Mario is actually in between something for collision detection or false if he isn't.
	 */
	public boolean isThereSomethingInBetween(double x2, double y2, double w2, double h2, double yFinal, double yActual) {
		boolean isBetween = false;
		
		if((y2>=yFinal && y2+h2<= yActual) ) {//|| (y2>=this.getPosY()+this.getHeight() && y2+h2<=yFinal)
			
			if( this.getPosX()+this.getWidth() >= x2 && this.getPosX() <= x2+w2) {
				isBetween = true;
				
			}
		}
		
		return isBetween;
	}

	/**
	 * <b>Description:</b>
	 * This function obtains Mario's next power up, if he's in small form, he'd get a Mushroom however, if he's in big form he'd get a flower power up.
	 * @return Mario's next power up when he collides with a mistery block.
	 */
	public PowerUp nextPowerUp() {
		PowerUp nextPower;
		if(powerState == null) {
			nextPower = new Mushroom(this.getPosX(), this.getPosY(), this.getWidth(),  this.getWidth());
		}else{
			nextPower = new Flower(this.getPosX(), this.getPosY(), this.getWidth(),  this.getWidth());
		}
		
		return nextPower;
	}

	 /**
	  * <b>Description:</b>
	  * This function determines whether or not an enemy is close enough to Mario in order for him to start walking.
	 * @param posX The enemy's position in the x axis.
	 * @return True if the enemy is beneath the distance threshold and false if it isn't.
	 */
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
