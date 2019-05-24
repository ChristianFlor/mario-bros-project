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
	public Score searchScore(String name) {
		return searchScore(name, root);
	}
	
	private Score searchScore(String name, Score current) {
		if(current != null) {
			if(current.getName().compareTo(name) > 0) {
				return searchScore(name, current.getLeft());
			} else if(current.getName().compareTo(name) < 0) {
				return searchScore(name, current.getRight());
			} else {
				return current;
			}
		}
		return null;
	}
	public Score delete(Score current, Score esp) {
		if (current == null) {
			return null;
		} else if (current.compareTo(esp) > 0) { // find the node
			current.setLeft(delete(current.getLeft(), esp));
		} else if (current.compareTo(esp) < 0) {
			current.setRight(delete(current.getRight(), esp));
		} else {            
			if (current.getRight() == null) {
				return current.getLeft();    //replace with left child
			} else if (current.getLeft() == null) {
				return current.getRight();   // replace with right child
			} else {                // replace with min from right subtree
				current.replaceData(getMin(current.getRight()));
				current.setRight(delete(current.getRight(), current));
			}
		}
		return current; 
	}
	private Score getMin(Score current) {
		if(current.getLeft() != null) {
			return getMin(current.getLeft());
		}
		return current;
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
