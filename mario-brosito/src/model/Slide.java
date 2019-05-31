package model;

public class Slide extends Obstacle{

	/**
	 * The image of the small sized tube.
	 */
	public static final String SMALLTUBE = "uiImg/smallTube.png";
	
	/**
	 * The image of the medium sized tube.
	 */
	public static final String MEDIUMTUBE = "uiImg/mediumTube.png";
	
	/**
	 * The image of the big sized tube.
	 */
	public static final String BIGTUBE = "uiImg/bigTube.png";
	
	/**
	 * <b>Description:</b>
     * This function initializes a new slide.
	 * @param posX the position in the X axis of the slide in the level
	 * @param posY the position in the Y axis of the slide in the level
	 * @param width the width of the slide in pixels
	 * @param height the height of the slide in pixels
	 */
	public Slide(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}

}
