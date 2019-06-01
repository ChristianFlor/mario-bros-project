package model;

import java.io.Serializable;

public class Score implements Serializable{

	/**
	 * An image used to paint the binary search tree.
	 */
	public static final String MARIO ="https://www.smashbros.com/wiiu-3ds/images/character/mario/main.png";
	
	/**
	 * An image used to paint the binary search tree.
	 */
	public static final String LUIGI ="https://freepngimg.com/thumb/luigi/21913-5-luigi.png";
	
	/**
	 * An image used to paint the binary search tree.
	 */
	public static final String KOOPAS ="https://www.mariowiki.com/images/5/5c/SuperMarioParty_KoopaTroopa.png";
	
	/**
	 * An image used to paint the binary search tree.
	 */
	public static final String BOWSER ="https://vignette.wikia.nocookie.net/mario/images/e/e1/Bowser-0.png/revision/latest?cb=20161119195258&path-prefix=es";
	
	/**
	 * An image used to paint the binary search tree.
	 */
	public static final String TOAD ="http://clipart-library.com/images/Bcgopdxc8.png";
	
	/**
	 * The name of the player.
	 */
	private String name;
	
	/**
	 * The score of the player.
	 */
	private int score;
	
	/**
	 * The photo of the player.
	 */
	private String photo;
	
	/**
	 * The right score in the binary search tree.
	 */
	private Score right;
	
	/**
	 * The left score in the binary search tree.
	 */
	private Score left;
	
	/**
	 * The position in the x axis of this player in respects to the binary search tree.
	 */
	private double x;
	
	/**
	 * The position in the x axis of this player in respects to the binary search tree.
	 */
	private double y;
	
	
	/**
	 * <b>Description:</b>
	 * This function initializes a new score.
	 * @param n The name of the player.
	 * @param s The score of the player.
	 */
	public Score(String n, int s) {
		this.name = n;
		this.score = s;
		selectImg(s);
		this.setX(x);
		this.setY(y);
	}
	
	/* 
	 * <b>Description:</b>
	 * This function turns this score into a string.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name+score;
	}
	
	/**
	 * <b>Description:</b>
	 * This function selects the photo of the player depending on the score obtained.
	 * @param score The score of the player.
	 */
	public void selectImg(int score) {
		if(score<=3240) {
			this.setPhoto(BOWSER);
		}else if(score > 3240 && score<=6480) {
			this.setPhoto(TOAD);
		}else if(score > 6480 && score<=9720) {
			this.setPhoto(KOOPAS);
		}else if(score > 9720 && score<=12960) {
			this.setPhoto(LUIGI);
		}else if(score > 12960 && score<=16500) {
			this.setPhoto(MARIO);
		}
	}
	
	/**
	 * <b>Description:</b>
	 * This function obtains the name of this player.
	 * @return The name of this player.
	 */
	public String getName() {
		return name;
	}

	/**
	 * <b>Description:</b>
	 * This function modifies the player's name.
	 * @param name The new name of the player.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <b>Description:</b>
	 * This function obtains the score of this player.
	 * @return The score of this player.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * <b>Description:</b>
	 * This function modifies the player's score.
	 * @param name The new score of the player.
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * <b>Description:</b>
	 * This function obtains the right score in the binary search tree.
	 * @return The right score.
	 */
	public Score getRight() {
		return right;
	}

	/**
	 * <b>Description:</b>
	 * This function modifies the right score in the binary search tree.
	 * @param name The new right score of this score.
	 */
	public void setRight(Score right) {
		this.right = right;
	}

	/**
	 * <b>Description:</b>
	 * This function obtains the left score in the binary search tree.
	 * @return The left score.
	 */
	public Score getLeft() {
		return left;
	}

	/**
	 * <b>Description:</b>
	 * This function modifies the left score in the binary search tree.
	 * @param name The new left score of this score.
	 */
	public void setLeft(Score left) {
		this.left = left;
	}

	/**
	 * <b>Description:</b>
	 * This function obtains the photo of this player.
	 * @return The photo of this player.
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * <b>Description:</b>
	 * This function modifies the player's photo.
	 * @param name The new photo of the player.
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * <b>Description:</b>
	 * This function obtains the x coordinate of this player.
	 * @return The x coordinate of this player.
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * <b>Description:</b>
	 * This function modifies the player's x coordinate.
	 * @param name The new x coordinate of the player.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * <b>Description:</b>
	 * This function obtains the y coordinate of this player.
	 * @return The y coordinate of this player.
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * <b>Description:</b>
	 * This function modifies the player's y coordinate.
	 * @param name The new y coordinate of the player.
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * <b>Description:</b>
	 * This function obtains the weight of the binary search tree.
	 * @return The weight of the tree, in other words, the number of nodes in the tree.
	 */
	public int getWeight() {
		int weight = 1;
		if(left != null) {
			weight += left.getWeight();
		}
		if(right != null) {
			weight += right.getWeight();
		}
		return weight;
	}	
}
