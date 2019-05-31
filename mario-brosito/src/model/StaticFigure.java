package model;

public class StaticFigure extends Figure {

	/**
	 * The default image of the base block in the first level.
	 */
	public static final String BASE1 = "uiImg/stone.png";
	
	/**
	 * The default image of the base block in the second level.
	 */
	public static final String BASE2 = "uiImg/stone_UnderGround.png";
	
	/**
	 * The default image of the base block in the third level.
	 */
	public static final String BASE3 = "uiImg/stone_Castle.png";
	
	/**
	 * The default image of the stair block in the first level.
	 */
	public static final String STAIR1 = "uiImg/chocolate.png";
	
	/**
	 * The default image of the stair block in the second level.
	 */
	public static final String STAIR2 = "uiImg/chocolate_UnderGround.png";
	
	/**
	 * The default image of the iron block in the level.
	 */
	public static final String IRON = "src/uiImg/Iron.png";
	
	/**
	 * The default image of the flag pole block in the level.
	 */
	public static final String FLAGPOLE = "uiImg/Flag.png";
	
	/**
	 * The default image of the flag top block in the level.
	 */
	public static final String FLAGTOP = "uiImg/FlagTop.png";
	
	/**
	 * The default image of the flag sphere block in the level.
	 */
	public static final String FLAGSPHERE = "uiImg/FlagSphere.png";
	
	/**
	 * The default image of the castle in the level.
	 */
	public static final String CASTLE = "uiImg/SmallCastle.png";
	
	/**
	 * The default image of the castle flag block in the level.
	 */
	public static final String CASTLEFLAG = "uiImg/FlagWin.png";
	
	/**
	 * The default image of the lava in the level.
	 */
	public static final String LAVA = "uiImg/Lava.png";
	
	/**
	 * The default image of the platform block in the level.
	 */
	public static final String PLATFORM = "uiImg/BridgeBloks.png";
	
	/**
	 * <b>Description:</b>
     * This function initializes a new static figure.
	 * @param posX the position in the X axis of the static figure in the level
	 * @param posY the position in the Y axis of the static figure in the level
	 * @param width the width of the static figure in pixels
	 * @param height the height of the static figure in pixels
	 */
	public StaticFigure(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}

	

}
