package model;

public class PiranhaPlant extends Enemy{


	/**
	 * The default image of the piranha plant in the level.
	 */
	public final static String IMAGE = "/src/uiImg/plant.png";
	
	/**
	  * <b>Description:</b>
		 * This function allows to initialize a new piranha plant.
		 * @param posX the position in the X axis of the piranha plant in the level
		 * @param posY the position in the Y axis of the piranha plant in the level
		 * @param width the width of the piranha plant in pixels
		 * @param height the height of the piranha plant in pixels
		 */
	public PiranhaPlant(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}

	
}
