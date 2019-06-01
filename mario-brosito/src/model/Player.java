package model;
import java.io.Serializable;
import java.security.SecureRandom;

public class Player implements Serializable{

	/**
	 * This function initializes a new Player.
	 */
	public Player() {
		
	}
	
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
	 * The nickname of the player.
	 */
	private String nickName;
	
	/**
	 * The score of the player.
	 */
	private int score;
	
	/**
	 * The unique id of the player.
	 */
	private String id;
	
	/**
	 * The photo of the player.
	 */
	private String photo;
	
	/**
	 * The next player in the linked list of players.
	 */
	private Player next;
	
	/**
	 * The previous player in the linked list of players.
	 */
	private Player prev;
	
	/**
	 * The position in the x axis of this player in respects to the linked list.
	 */
	private double x;
	
	/**
	 * The position in the x axis of this player in respects to the linked list.
	 */
	private double y;
	
	/**
	 * <b>Description:</b>
	 * This function initializes a new Player.
	 * @param n The name of the player.
	 * @param nick The nickname of the player.
	 * @param s The score of the player.
	 */
	public Player(String name, String nick, int score) {
		SecureRandom r = new SecureRandom();
		this.name = name;
		this.nickName=nick;
		this.score = score;
		String in= name.substring(0, 2);
		this.id= in+(r.nextInt(10000));
		selectImg(score);
		this.setX(x);
		this.setY(y);
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
	
	/* 
	 * <b>Description:</b>
	 * This function turns this player into a string.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name+score;
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
	 * This function obtains the nickname of this player.
	 * @return The nickname of this player.
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * <b>Description:</b>
	 * This function modifies the player's nickname.
	 * @param name The new nickname of the player.
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * <b>Description:</b>
	 * This function obtains the next player in the linked list of players.
	 * @return The next of this player.
	 */
	public Player getNext() {
		return next;
	}

	/**
	 * <b>Description:</b>
	 * This function modifies the next player in the linked list.
	 * @param name The new next player in the list.
	 */
	public void setNext(Player next) {
		this.next = next;
	}

	/**
	 * <b>Description:</b>
	 * This function obtains the previous player in the linked list of players.
	 * @return The previous of this player.
	 */
	public Player getPrev() {
		return prev;
	}

	/**
	 * <b>Description:</b>
	 * This function modifies the previous player in the linked list.
	 * @param name The new previous player in the list.
	 */
	public void setPrev(Player prev) {
		this.prev = prev;
	}

	/**
	 * <b>Description:</b>
	 * This function obtains the unique id of this player.
	 * @return The id of this player.
	 */
	public String getId() {
		return id;
	}

	/**
	 * <b>Description:</b>
	 * This function modifies the player's id.
	 * @param name The new id of the player.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * <b>Description:</b>
	 * This function obtains the size of the linked list.
	 * @return The size of the linked list.
	 */
	public int size() {
		int s = 1;
		if(next != null) {
			s+= next.size();
		}
		return s;
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
}
