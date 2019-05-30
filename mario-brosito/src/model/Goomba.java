package model;

public class Goomba extends Enemy{

	/**
	 * The normal image of the goomba.
	 */
	public final static String IMAGE = "src/uiImg/enemy.png";
	/**
	 * The image of the goomba when it dies.
	 */
	public final static String DEADGOOMBA = "src/uiImg/DeadMushroom.png"; // 32x32
	
	 /**
	  * <b>Description:</b>
		 * This function allows to initialize a new goomba.
		 * @param posX the position in the X axis of the enemy in the level
		 * @param posY the position in the Y axis of the enemy in the level
		 * @param width the width of the enemy in pixels
		 * @param height the height of the enemy in pixels
		 */
	public Goomba(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}


}
