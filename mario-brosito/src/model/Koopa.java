package model;

public class Koopa extends Enemy{
	
	/**
	 * The normal image of the koopa.
	 */
	public final static String IMAGE = "src/uiImg/finalT.png";
	
	/**
	 * The image of the koopa when it's in the shell.
	 */
	public final static String KOOPASHELL = "uiImg/TurtelShell.png";
	
	 /**
	  * <b>Description:</b>
		 * This function allows to initialize a new Koopa.
		 * @param posX the position in the X axis of the enemy in the level
		 * @param posY the position in the Y axis of the enemy in the level
		 * @param width the width of the enemy in pixels
		 * @param height the height of the enemy in pixels
		 */
	public Koopa(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}



}
