package model;

public class MovingPlatform extends Figure{

	/**
	 * The default image of the moving platform in the level.
	 */
	public static final String PLATFORM = "uiImg/Lift.png";
	
	/**
	  * <b>Description:</b>
		 * This function allows to initialize a new moving platform.
		 * @param posX the position in the X axis of the moving platform in the level
		 * @param posY the position in the Y axis of the moving platform in the level
		 * @param width the width of the moving platform in pixels
		 * @param height the height of the moving platform in pixels
		 */
	public MovingPlatform(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}

	
	
}
