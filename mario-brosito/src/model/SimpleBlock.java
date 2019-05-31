package model;

public class SimpleBlock extends Obstacle implements DestructibleBrick {

	/**
	 * The default image of the brick in the first level.
	 */
	public static final String BRICK1 = "uiImg/brick.png";
	
	/**
	 * The default image of the brick in the second level.
	 */
	public static final String BRICK2 = "uiImg/brick_UnderGround.png";
	
	/**
	 * The default image of the spinning fire in the third level.
	 */
	public static final String SPINNINGFIRE = "src/uiImg/FireBall.png";
	
	/**
	 * <b>Description:</b>
     * This function initializes a new simple block.
	 * @param posX the position in the X axis of the simple block in the level
	 * @param posY the position in the Y axis of the simple block in the level
	 * @param width the width of the simple block in pixels
	 * @param height the height of the simple block in pixels
	 */
	public SimpleBlock(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}

	
}
