package model;

import java.io.IOException;

public class Game {
	
	
	private Level levelOne;
	private Level levelTwo;
	private Level levelThree;
	
	private ImagesLoader images;
	private SoundsLoader sounds;
	
	public Game() throws IOException {
		levelOne = new Level();
		levelOne.loadLevel(Level.LEVEL_ONE_PATH);
	}
	
	/**
	 * @return the levelOne
	 */
	public Level getLevelOne() {
		return levelOne;
	}
	/**
	 * @return the levelTwo
	 */
	public Level getLevelTwo() {
		return levelTwo;
	}
	/**
	 * @return the levelThree
	 */
	public Level getLevelThree() {
		return levelThree;
	}
	
	
	
}
