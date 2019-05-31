package model;

public class Bowser extends Enemy{
	
	/**
	 * The image directory of the Bowser boss.
	 */
	public final static String BOWSER = "src/uiImg/Boss.png";
	
	/**
	 * <b>Description:</b>
     * This function initializes a new Bowser boss.
	 * @param posX the position in the X axis of the enemy in the level
	 * @param posY the position in the Y axis of the enemy in the level
	 * @param width the width of the enemy in pixels
	 * @param height the height of the enemy in pixels
	 */
	public Bowser(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}
	
	

}
