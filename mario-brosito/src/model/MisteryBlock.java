package model;

public class MisteryBlock extends Obstacle {
	
	/**
	 * The default image of the mystery block.
	 */
	public static final String MISTERYBLOCK = "src/uiImg/QuestionMark.png";
	
	/**
	 * The coin held by this mystery block.
	 */
	private Coin coin;
	
	/**
	 * The power up held by this mystery block.
	 */
	private PowerUp power;

	/**
	  * <b>Description:</b>
		 * This function allows to initialize a new mystery block.
		 * @param posX the position in the X axis of the mystery block in the level
		 * @param posY the position in the Y axis of the mystery block in the level
		 * @param width the width of the mystery block in pixels
		 * @param height the height of the mystery block in pixels
		 * @param coin The coin held by this mystery block.
		 * @param power The power up held by this mystery block.
		 */
	public MisteryBlock(double posX, double posY, double width, double height, Coin coin, PowerUp power) {
		super(posX, posY, width, height);
		
		this.coin = coin;
		this.power = power;
	}

	/**
	 * <b>Description:</b>
	 * This function obtains the coin held by this mystery block.
	 * @return the coin held by this mystery block or null if it doesn't have one.
	 */
	public Coin getCoin() {
		return coin;
	}

	/**
	 * <b>Description:</b>
	 * This function obtains the power up held by this mystery block.
	 * @return the power up held by this mystery block or null if it doesn't have one.
	 */
	public PowerUp getPower() {
		return power;
	}
	
	

}
