package model;

public class SimpleBlock extends Obstacle implements DestructibleBrick {

	public static final String BRICK = "uiImg/brick.png";
	
	public SimpleBlock(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}

	
}
