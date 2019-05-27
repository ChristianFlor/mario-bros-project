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
	public final static String ISDIAGONALRIGHT = "DiagonalRight";
	public final static String ISDIAGONALLEFT = "DiagonalLeft";




	
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
		
		Rectangle2D marioRec = new Rectangle2D(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
		
		
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
			}else if(this.getPosY() >y2 && this.getPosX()+32 > x2 && this.getPosX() < x2+w2) {//&& this.getPosX()+16 >= x2 && this.getPosX()+16 <= x2+w2
				collide = ISMOVINGUP;
			}
			
			
			
			
		}
		/*if((!(this.getPosX() > x2+w2) && !(this.getPosX()+this.getWidth() < x2)) && (!(this.getPosY() > y2+h2) && !(this.getPosY() + this.getHeight() < y2))) {
		
			double pointsX = w2/3;
			
			Point2D p1 = new Point2D(x2+pointsX, y2);
			Point2D p2 = new Point2D(x2+pointsX*3, y2);
			Point2D p12 = new Point2D(x2+pointsX*2, y2);
		
			double pointsY = h2/3;
			Point2D p3 = new Point2D(x2, y2+pointsY);
			Point2D p4 = new Point2D(x2, y2+(pointsY*2));
			Point2D p5 = new Point2D(x2, y2+(pointsY*3));
		
			Point2D p6 = new Point2D(x2+w2, y2+(pointsY));
			Point2D p7 = new Point2D(x2+w2, y2+(pointsY*2));
			Point2D p8 = new Point2D(x2+w2, y2+(pointsY*3));
			
			
			Point2D p9 = new Point2D(x2+pointsX,y2+h2 );
			Point2D p10 = new Point2D(x2+(pointsX*2), y2+h2);
			Point2D p11 = new Point2D(x2+(pointsX*3), y2+h2);
			 
			
			
			//Rectangle2D marioRec = new Rectangle2D(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
			
			
			//System.out.println(state);
			
			if((marioRec.contains(p9) || marioRec.contains(p10)) || marioRec.contains(p11)) {

				collide = ISMOVINGUP;
			}
			else if((marioRec.contains(p3) || marioRec.contains(p4) || marioRec.contains(p5))) {
				
				collide = ISMOVINGRIGHT;
			}
			else if((marioRec.contains(p6) || marioRec.contains(p7) || marioRec.contains(p8) )) 
		
				collide = ISMOVINGLEFT;
			
			else if((state.equals(ISMOVINGDOWN) ||state.equals(ISSTANDINGSTILL)) && (marioRec.contains(p1) || marioRec.contains(p2) || marioRec.contains(p12)))

				collide = ISMOVINGDOWN;
		
			
			*/
			/*double halfOtherX = x2 + (w2/2);
			double halfMarioX = this.getPosX() +(this.getWidth()/2);
			double halfOtherY = y2 +(h2/2);
			double halfMarioY = this.getPosY() +(this.getHeight()/2);
			
			
			double x = (halfMarioX-halfOtherX)*(halfMarioX-halfOtherX);
			double y = (halfMarioY-halfOtherY)*(halfMarioY-halfOtherY);
			double distance = Math.sqrt(x+y);
			
			double distanceX = Math.abs(distance - this.getWidth()/2+w2/2);
			double distanceY = Math.abs(distance - this.getHeight()/2+h2/2);
			
			if( (distance< this.getWidth()/2+w2/2) ){
				
				if(state.equals(ISMOVINGRIGHT)) {
					collide = ISMOVINGRIGHT;
					System.out.println("r");
				}else if(state.equals(ISMOVINGLEFT)) {
					collide = ISMOVINGLEFT;
					System.out.println("l");
				}
			}
			 if(distance< this.getHeight()/2+h2/2) {
				System.out.println("d");
				if(state.equals(ISMOVINGDOWN)) {
					collide = ISMOVINGDOWN;
					System.out.println("d");
				}else if(state.equals(ISMOVINGUP)) {
					System.out.println("u");
					collide = ISMOVINGUP;
				}
			}*/

	//	}
		return collide;
	}
	
	public String isGrounded(double x2, double y2, double w2, double h2) {
		String grounded = "";
		
		
		if((!(this.getPosX() > x2+w2) && !(this.getPosX()+this.getWidth() < x2)) && (!(this.getPosY() > y2+h2) && !(this.getPosY() + this.getHeight() < y2))){
			/*Point2D p1 = new Point2D(x2, y2);
			Point2D p2 = new Point2D(x2+w2, y2);
			Point2D p12 = new Point2D(x2+w2/2, y2);
			
			Rectangle2D marioRec = new Rectangle2D(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
			
			
			if((marioRec.contains(p1) || marioRec.contains(p2) || marioRec.contains(p12)) && ((y2 == 416 || y2 == 448) || y2<=this.getPosY())){
				grounded = "floor";
			}else if((marioRec.contains(p1) || marioRec.contains(p2) || marioRec.contains(p12)) && this.getPosX()+this.getWidth() > x2 && this.getPosX()<x2+w2){
				grounded = "intersect";
			}*/
			
			if(y2==416 || y2 == 448) {
				grounded = "floor";
			}else if(this.getPosY()+this.getHeight() <= y2) {
				grounded = "intersect";
			}
		}
		
			
		
		return grounded;
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
	    	double distance = 350;
	    	double actualDistance =	Math.abs(this.getPosX() - posX);
	    		if(actualDistance <= distance) {
	    			near = true;
	    		}
	    	return near;
	    }


}
