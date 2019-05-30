package model;

public class Coin extends Figure {

	/**
	 * The image of the static coin in the level.
	 */
	public final static String COIN = "src/uiImg/Coin.png";
	
	/**
	 * The image of the coin animation when this coin is in a mistery block.
	 */
	public final static String COININBLOCK = "src/uiImg/CoinAnim.png";
	
	/**
	 * <b>Description:</b>
     * This function initializes a new coin.
	 * @param posX the position in the X axis of the enemy in the level
	 * @param posY the position in the Y axis of the enemy in the level
	 * @param width the width of the enemy in pixels
	 * @param height the height of the enemy in pixels
	 */
	public Coin(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}

	

}
