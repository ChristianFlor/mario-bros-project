
package model;

public abstract class Figure {
	
	/**
	 * Represents the position in the X axis of the figure in the level
	 */
	private double posX;
	
	/**
	 * Represents the position in the Y axis of the figure in the level
	 */
	
	private double posY;
	
	/**
	 * Represents the width of the figure in pixels
	 */
	
	private double width;
	
	/**
	 * Represents the height of the figure in pixels
	 */
	
	private double height;
	
	/**
	 * string that represents the image of the figure
	 */
	private String image;

	
	/**
	 * <b>Description:</b>
	 * This function allows to initialize a new figure
	 * 
	 * @param posX the position in the X axis of the figure in the level
	 * @param posY the position in the Y axis of the figure in the level
	 * @param width the width of the figure in pixels
	 * @param height the height of the figure in pixels
	 */
	public Figure(double posX, double posY, double width, double height) {
		
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;

	}
	
	
	
	/**
	 * <b>Description:</b>
	 * This function allows to obtain the position of the figure in the X axis
	 * @return posX the position X value
	 */
	
	public double getPosX() {
		return posX;
	}
	
	/**
	 * <b>Description:</b>
	 * This function allows to change the position in the X axis of the figure
	 * @param posX the new position in the X axis
	 */
	public void setPosX(double posX) {
		this.posX = posX;
	}
	
	/**
	 * <b>Description:</b>
	 * This function allows to obtain the position of the figure in the Y axis
	 * @return posY the position Y value
	 */
	
	public double getPosY() {
		return posY;
	}
	
	/**
	 * <b>Description:</b>
	 * This function allows to change the position in the Y axis of the figure
	 * @param posY the new position in the Y axis
	 */
	
	public void setPosY(double posY) {
		this.posY = posY;
	}
	
	/**
	 * <b>Description:</b>
	 * This function allows to obtain the width of the figure
	 * @return width the width value
	 */
	
	public double getWidth() {
		return width;
	}
	
	/**
	 * <b>Description:</b>
	 * This function allows to change the width of the figure
	 * @param width the new width 
	 */
	
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * <b>Description:</b>
	 * This function allows to obtain the height of the figure
	 * @return height the height value
	 */
	
	public double getHeight() {
		return height;
	}
	
	/**
	 * <b>Description:</b>
	 * This function allows to change the height of the figure
	 * @param height the new height 
	 */

	public void setHeight(double height) {
		this.height = height;
	}
	
	/**
	 * <b>Description:</b>
	 * This function allows to obtain the image of the figure
	 * @param image the image of the figure 
	 */
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	

}
