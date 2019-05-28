package model;

public class SimpleBlock extends Obstacle implements DestructibleBrick {

	public static final String BRICK1 = "uiImg/brick.png";
	public static final String BRICK2 = "uiImg/brick_UnderGround.png";
	public static final String SPINNINGFIRE = "src/uiImg/FireBall.png";
	
	public SimpleBlock(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}

	
}
