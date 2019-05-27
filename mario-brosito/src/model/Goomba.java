package model;

public class Goomba extends Enemy{

	public final static String IMAGE = "src/uiImg/enemy.png";
	public final static String DEADGOOMBA = "src/uiImg/DeadMushroom.png"; // 32x32
	
	public Goomba(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}


}
