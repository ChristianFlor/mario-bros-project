package model;

import java.io.IOException;

public class Game {
	
	
	private Level levelOne;
	private Level levelTwo;
	private Level levelThree;
	
	private ImagesLoader images;
	private SoundsLoader sounds;
	
	private Score root;
	
	public Game() throws IOException {
		levelOne = new Level();
		levelOne.loadLevel(Level.LEVEL_ONE_PATH);
	}
	

	public void addScore(String name, int score) {
		Score s = new Score(name, score);
		
		if(root == null) {
			root = s;
		} else {
			addScore(root, s);
		}
	}
				
	private void addScore(Score current, Score newOne) {
				if(current.getScore()<newOne.getScore()) {
					if(current.getRight()==null) {
					current.setRight(newOne);
					} else {
						addScore(current.getRight(), newOne);
					} 
				} else if(current.getScore()>newOne.getScore()){
					if(current.getLeft()==null) {
						current.setLeft(newOne);
					} else {
						addScore(current.getLeft(), newOne);
					}
				}
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
