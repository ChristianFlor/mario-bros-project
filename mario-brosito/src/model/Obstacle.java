package model;

public class Obstacle extends Figure {

	/**
	  * <b>Description:</b>
		 * This function allows to initialize a new obstacle.
		 * @param posX the position in the X axis of the obstacle in the level
		 * @param posY the position in the Y axis of the obstacle in the level
		 * @param width the width of the obstacle in pixels
		 * @param height the height of the obstacle in pixels
		 */
	public Obstacle(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}

}
