package model;

/**
 * This class represents the flower power up of the Mario game.
 * @author sleep
 *
 */
public class Flower extends PowerUp {
	
	/**
	 * The image of the flower in the level.
	 */
	public final static String IMAGE = "src/uiImg/Flower.png";
	
	/**
	 * <b>Description:</b>
     * This function initializes a new flower.
	 * @param posX the position in the X axis of the enemy in the level
	 * @param posY the position in the Y axis of the enemy in the level
	 * @param width the width of the enemy in pixels
	 * @param height the height of the enemy in pixels
	 */
	public Flower(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}


}
