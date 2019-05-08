package model;

public class Mario extends Figure {

	public Mario(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
		powerState = null;
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
	
	
	public PowerUp nextPowerUp() {
		PowerUp nextPower;
		if(powerState == null) {
			nextPower = new Mushroom(this.getPosX(), this.getPosY(), this.getWidth(),  this.getWidth(), "/src/uiImg/Mushroom.png");
		}else{
			nextPower = new Flower(this.getPosX(), this.getPosY(), this.getWidth(),  this.getWidth(), "/src/uiImg/Flower.png");
		}
		
		return nextPower;
	}
	
	
	
	
	
	

}
