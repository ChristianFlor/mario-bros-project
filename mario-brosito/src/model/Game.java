package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import customExceptions.IllegalInputException;
import customExceptions.IntegerValuesException;

public class Game {
	// -----------------------------------------------------------------
    // Constant
    // -----------------------------------------------------------------
	public static final double INCREASEMENT = 90.0;
	// -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

	/**
	 * The first level of the game.
	 */
	private Level levelOne;
	
	/**
	 * The second level of the game.
	 */
	private Level levelTwo;
	
	/**
	 * The third level of the game.
	 */
	private Level levelThree;
	
	/**
	 * The root of the score tree.
	 */
	private Score root;
	
	/**
	 * The first player of the player linked list.
	 */
	private Player first;
		
	/**
	 * This attribute is used to show the ranking of players.
	 */
	private double width;
	
	/**
	 * This attribute is used to show the ranking of players.
	 */
	private double height;
	
	/**
	 * represents the place of the serialized file of the score
	 */
	public static final String PATH_FILE_SCORE = "data/Score.sco";
	
	/**
	 * represents the place of the serialized file of the players
	 */
	public static final String PATH_FILE_PLAYER = "data/Player.pla";

	/**
	 * <b>Description:</b>
	 * This function initializes a new Game.
	 * @throws IOException When The file is not found.
	 * @throws IllegalInputException The exception thrown when the input doesn't match the standards.
	 * @throws IntegerValuesException The exception thrown when the values entered are not integers.
	 */
	public Game() throws IOException, IllegalInputException,IntegerValuesException {
		levelOne = new Level();
		levelOne.loadLevel(Level.LEVEL_ONE_PATH);
		levelTwo = new Level();
		levelTwo.loadLevel(Level.LEVEL_TWO_PATH);
		levelThree = new Level();
		levelThree.loadLevel(Level.LEVEL_THREE_PATH);
	}
	
	
	
	
	public void saveScore(String path) throws IOException {
		File file = new File(path);
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(root);
		
		oos.close();
			
	}
	
	public void loadScore(String path) throws IOException, ClassNotFoundException {
		File file = new File(path);
		
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			root = (Score) ois.readObject();
			
			ois.close();
 		}
		
			
	}
	
	public void savePlayers(String path) throws IOException {
		File file = new File(path);
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(first);
		
		oos.close();
			
	}
	
	public void loadPlayers(String path) throws IOException, ClassNotFoundException {
		File file = new File(path);
		
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			first = (Player) ois.readObject();
			
			ois.close();
 		}
		
			
	}
	
	
	
	
	
	
	// -----------------------------------------------------------------
    // Methods for add List
    // -----------------------------------------------------------------
	/**
	 * <b>Description:</b>
     * This function adds a new player to the linked list.
	 * @param n The name of the player to be added.
	 * @param nick The nickname of the player to be added.
	 * @param s The score of the player to be added.
	 * @throws IllegalInputException The exception thrown when the input doesn't match the standards.
	 * @throws IntegerValuesException The exception thrown when the values entered are not integers.
	 */
	public void addPlayer(String name, String nick, int score)throws  IllegalInputException,IntegerValuesException{
		if(name.isEmpty()|| name==null) {
			throw new IllegalInputException(name);
		}else if(isNumeric(name)) {
			throw new IntegerValuesException(name);
		}
		if(nick.isEmpty() || nick==null || isNumeric(nick)) {
			throw new IllegalInputException(nick);
		}else if(isNumeric(nick)) {
			throw new IntegerValuesException(nick);
		}
		Player p= new Player(name,nick,score);
		if(first == null){
			first =p;
		}else{
			Player current = first;
			while(current.getNext() != null){
				current = current.getNext();
			}
			current.setNext(p);
			current.getNext().setPrev(current);
		}
	}
	
	/**
	 * <b>Description:</b>
	 * This function tests whether or not a string is numeric.
	 * @param cadena The string to be tested.
	 * @return The boolean that dictates whether or not the condition is met.
	 */
	public boolean isNumeric(String cadena){
		boolean numeric = false;
		try {
			Integer.parseInt(cadena);
			numeric = true;
		} catch (NumberFormatException nfe){
			numeric = false;
		}
		
		return numeric;
	}
	// -----------------------------------------------------------------
    // Methods for add tree
    // -----------------------------------------------------------------
	
	/**
	 * <b>Description:</b>
	 * This function adds a new score to the Score binary search tree.
	 * @param p The new player to be added to the tree.
	 * @throws IllegalInputException The exception thrown when the input doesn't match the standards.
	 */
	public void addScore(Player p)throws  IllegalInputException {
		if(p.getName().isEmpty() || p.getName()==null) {
			throw new IllegalInputException(p.getName());
		}else if(p.getNickName().isEmpty() || p.getNickName()==null) {
			throw new IllegalInputException(p.getNickName());
		}
		Score s= new Score(p.getName(), p.getScore());
		if(root == null) {
			root= s;
		}else {
			addScore(root, s);
		}
	}
	
	/**
	 * <b>Description:</b>
	 * This function adds a new score recursively to the binary search tree.
	 * @param current The current score that the function is on.
	 * @param newOne The new Score to be added.
	 */
	private void addScore(Score current, Score newOne) {
		if(current.getScore()<newOne.getScore()) {
			if(current.getRight()==null) {
			current.setRight(newOne);
			} else {
				addScore(current.getRight(), newOne);
			} 
		} else if(current.getScore()>=newOne.getScore()){
			if(current.getLeft()==null) {
				current.setLeft(newOne);
			} else {
				addScore(current.getLeft(), newOne);
			}
		}
	}
	// -----------------------------------------------------------------
    // Methods for show tree
    // -----------------------------------------------------------------
	/**
	 * <b>Description:</b>
	 * This function prints the binary search tree.
	 * @param current The current score that the function is on.
	 */
	public void printTree(Score current) {
		if(current.getLeft() != null) {
			printTree(current.getLeft());
		}
		if(current.getRight() != null) {
			printTree(current.getRight());
		}
	}

	/**
	 * <b>Description:</b>
	 * This function increases the bounds, it's used for the binary search tree.
	 */
	public void increaseBounds() {
		double x = increaseBoundsX(root);
		if(x >= width) {
			this.width = x+20;
		}
	}
	
	/**
	 * <b>Description:</b>
	 * This function increases the bounds in X in order to paint the binary search tree.
	 * @param current The current score that the function is on.
	 * @return It returns the updated X position.
	 */
	private double increaseBoundsX(Score current) {
		if(current != null) {
			double l = increaseBoundsX(current.getLeft());
			double r = increaseBoundsX(current.getRight());
			if(l > current.getX()) {
				if(l < r) {
					return r;
				}
				return l;
			} else if (r > current.getX()){
				if(r < l) {
					return l;
				}
				return r;
			} else {
				return current.getX();
			}
		}
		return 0;
	}
	// -----------------------------------------------------------------
    // Methods for sort List
    // -----------------------------------------------------------------
	/**
	 * <b>Description:</b>
	 * This function sorts the linked list by name.
	 */
	public void sortByName() {
		// TODO Use of bubble sort
		if(first != null) {
			Player temp = first;
			int counter = 0;
			int size = sizeOfPlayer();
			while(temp != null) {
				Player current = first;
				int counter2 = 0;
				while(current.getNext() != null && counter2 < size-counter) {
					if(current.getName().compareTo(current.getNext().getName()) > 0) {
						if(first == current) first = current.getNext();
						Player next = current.getNext().getNext();
						Player prev = current.getPrev();
						if(next != null) next.setPrev(current);
						if(prev != null) prev.setNext(current.getNext());
						current.getNext().setNext(current);
						current.getNext().setPrev(prev);
						current.setPrev(current.getNext());
						current.setNext(next);
					} else {
						current = current.getNext();
					}
					counter2++;
				}
				counter++;
				temp = temp.getNext();
			}
		}
	}
	/**
	 * <b>Description:</b>
	 * This function sorts the linked list by nickname.
	 */
	public void sortByNick() {
		// TODO Use of bubble sort
		if(first != null) {
			Player temp = first;
			int counter = 0;
			int size = sizeOfPlayer();
			while(temp != null) {
				Player current = first;
				int counter2 = 0;
				while(current.getNext() != null && counter2 < size-counter) {
					if(current.getNickName().compareTo(current.getNext().getNickName()) > 0) {
						if(first == current) first = current.getNext();
						Player next = current.getNext().getNext();
						Player prev = current.getPrev();
						if(next != null) next.setPrev(current);
						if(prev != null) prev.setNext(current.getNext());
						current.getNext().setNext(current);
						current.getNext().setPrev(prev);
						current.setPrev(current.getNext());
						current.setNext(next);
					} else {
						current = current.getNext();
					}
					counter2++;
				}
				counter++;
				temp = temp.getNext();
			}
		}
	}
	/**
	 * <b>Description:</b>
	 * This function sorts the linked list by score.
	 */
	public void sortByScore() {
		// TODO Use of insertion sort
		if(first.getNext() != null) {
			Player current = first.getNext();
			while(current != null) {
				Player temp = current;
				while(temp.getPrev() != null) {
					
					if(temp.getScore() < temp.getPrev().getScore()) {
						if(temp.getPrev() == first) first = temp;
						Player next = temp.getNext();
						Player prev = temp.getPrev().getPrev();
						if(next != null) next.setPrev(temp.getPrev());
						if(prev != null) prev.setNext(temp);
						temp.setNext(temp.getPrev());
						temp.getPrev().setPrev(temp);
						temp.getPrev().setNext(next);
						temp.setPrev(prev);
					} else {
						temp = temp.getPrev();
					}
				}
				current = current.getNext();
			}
		}
		
	}
	/**
	 * <b>Description:</b>
	 * This function sorts the linked list by code.
	 */
	public void sortByCode() {
		// TODO use of selection sort
		Player current = first;
		while(current != null) {
			Player temp = current.getNext();
			Player min = current;
			while(temp != null) {
				if(temp.getId().compareTo(min.getId()) <= 0 ) {
					min = temp;
				}
				temp = temp.getNext();
			}
			boolean firstIt = false;
			if(min != current) {
				Player next1 = current.getNext();
				Player previous1 = current.getPrev();
				
				Player next2 = min.getNext();
				Player previous2 = min.getPrev();
				
				if(min == current.getNext()	) {
					if(previous1 != null) previous1.setNext(min);
					else {
						first = min;
						firstIt = true;
					}
					current.setNext(next2);
					current.setPrev(min);
					if(next2 != null) next2.setPrev(current);
					min.setNext(current);
					min.setPrev(previous1);
				} else {
					if(next1 != null) next1.setPrev(min);
					if(previous1 != null) previous1.setNext(min);
					else {
						first = min;
						firstIt = true;
					}
					
					min.setNext(next1);
					min.setPrev(previous1);
					
					if(next2 != null) next2.setPrev(current);
					if(previous2 != null) previous2.setNext(current);
					
					current.setNext(next2);
					current.setPrev(previous2);
				}
				current = min;
			}	
			if(firstIt) {
				current = first.getNext();
			} else {
				current = current.getNext();
			}
		}
	}
	
	// -----------------------------------------------------------------
    // Methods for search in List
    // -----------------------------------------------------------------
	/**
	 * <b>Description:</b>
	 * This function searches the specified name in the linked list.
	 * @param name The name to be searched.
	 * @return The player that was found or null if it wasn't found.
	 */
	public Player searchPlayer(String name) {
		return searchPlayer(name, first);
	}
	
	/**
	 * <b>Description:</b>
	 * This function searches the name of the player in the linked list of players.
	 * @param name The name to be searched.
	 * @param current The player that the function is on.
	 * @return The player that was found or null if it wasn't found.
	 */
	private Player searchPlayer(String name, Player current) {
		if(current != null) {
			if(current.getName().compareTo(name) == 0) {
				return current;
			} else {
				return searchPlayer(name, current.getNext());
			}
		}
		return null;
	}	
	
	/**
	 * <b>Description:</b>
	 * This function searches the code of the player in the linked list of players.
	 * @param n The code of the player to be searched.
	 * @return The player that was found or null if it wasn't found.
	 */
	public Player searchByCode(String n) {
		Player match = null;
		Player current = first;
		while(current!=null && match==null) {
			if(current.getId().equalsIgnoreCase(n)) {
				match = current;
			}
			current = current.getNext();
		}
		return match;
	}

	/**
	 * <b>Description:</b>
	 * This function searches the score of the player in the linked list of players.
	 * @param n The score of the player to be searched.
	 * @return The player that was found or null if it wasn't found.
	 */
	public Player searchByScore(String n) {
		int gate= Integer.parseInt(n);
		Player match = null;
		Player current = first;
		while(current!=null && match==null) {
			double currentGate=  current.getScore();
			if(currentGate==gate) {
				match = current;
			}
			current = current.getNext();
		}
		return match;
	}
	
	/**
	 * <b>Description:</b>
	 * This function binary searches a player in the array of players based on the name.
	 * @param p The array of players.
	 * @param name The name of the player to be searched.
	 * @return The player that was found or null if it wasn't found.
	 */
	public Player searchPlayerByName(Player[] p,String name) {
		
		sortByName();
		name.toUpperCase();
		int index = -1;
		int low = 0;
		int high = p.length-1;
		
		while(low <= high && index == -1) {
			int mid = (low+high)/2;
			int value = p[mid].getName().compareTo(name);
			if(value < 0) {
				low = mid+1;
			} else if(value > 0) {
				high = mid-1;
			} else {
				index = mid;
			}
		}
		Player f = null;
		if(index != -1) {
			f =  p[index];
		}
		return f;
	}
	/**
	 * <b>Description:</b>
	 * This function searches a player in the linked list of players based on the nickname.
	 * @param n The nickname to be searched.
	 * @return The player that was found or null if it wasn't found.
	 */
	public Player searchByNick(String n) {
		Player match = null;
		Player current = first;
		while(current!=null && match==null) {
			if(current.getNickName().equalsIgnoreCase(n)) {
				match = current;
			}
			current = current.getNext();
		}
		return match;
	}
	// -----------------------------------------------------------------
    // Methods of model solution
    // -----------------------------------------------------------------
	
	
	/**
	 * <b>Description:</b>
	 * This function obtains the size of the linked list of players.
	 * @return The size of the linked list.
	 */
	public int sizeOfPlayer(){
		int size=0;
		Player current = first;
		while(current != null){
			size++;
			current = current.getNext();
		}
		return size;
	}
	
	/**
	 * <b>Description:</b>
	 * This function assigns the positions of the binary search tree.
	 */
	public void assignePositions() {
		assignePositions(root, 0, this.getWidth(), 0, this.getHeight() / this.getTreeHeight());
	}
	
	/**
	 * <b>Description:</b>
	 * This function assigns the positions of the binary search tree.
	 * @param current The current score held by the function.
	 * @param xMin The minimum x axis position.
	 * @param xMax The maximum x axis position.
	 * @param yMin The minimum y axis position.
	 * @param yMax The maximum y axis position.
	 */
	private void assignePositions(Score current, double xMin, double xMax, double yMin, double yMax) {
		current.setX((xMin + xMax) / 2);
		current.setY(yMin + yMax / 2);
		if(current.getLeft() != null) {
			assignePositions(current.getLeft(), xMin, (xMin + xMax) / 2, yMin + yMax, yMax);
		}
		if(current.getRight() != null) {
			assignePositions(current.getRight(), (xMin + xMax) / 2, xMax, yMin + yMax, yMax);
		}
	}
	
	/**
	 * <b>Description:</b>
	 * This function assigns positions of the linked list.
	 */
	public void assignePositionsList() {
		double x = this.width/this.first.size();
		double y = this.height/2;
		assignePositionsList(first, x, y);
	}
	/**
	 * <b>Description:</b>
	 * This function assigns the position of the linked list.
	 * @param current The current player in the list.
	 * @param x The x coordinate to be assigned.
	 * @param y The y coordinate to be assigned.
	 */
	private void assignePositionsList(Player current, double x, double y) {
		if(current != null) {
			current.setX(x);
			current.setY(y);
			assignePositionsList(current.getNext(), x + 80, y);
		}
	}



	// -----------------------------------------------------------------
    // Methods Atributes
    // -----------------------------------------------------------------
	
	/**
	 * <b>Description:</b>
	 * This function obtains the array of players of the linked list.
	 * @return The array of players.
	 */
	public Player[] getPlayersToArray() {

		Player[] players = null;;
		if(first!=null)
			players = new Player[first.size()];
		int c = 0;
		Player current = first;
		 	while(current!=null) {
		 		players[c]= current;
		 		current = current.getNext();
		 		c++;
		 	}
		return players;

	}
	/**
	 * <b>Description:</b>
	 * This function obtains the height of the binary search tree.
	 * @return The height of the binary search tree.
	 */
	public int getTreeHeight() {
		return getTreeHeight(root);
	}
	/**
	 * <b>Description:</b>
	 * This function recursively obtains the height of the binary search tree.
	 * @param current The current node of the tree.
	 * @return The height of the binary search tree.
	 */
	private int getTreeHeight(Score current) {
		if(current != null) {
			return Math.max(getTreeHeight(current.getLeft()), getTreeHeight(current.getRight())) + 1;
		}
		return 0;
	}
	/**
	 * <b>Description:</b>
	 * This function obtains the root of the binary search tree.
	 * @return The root of the binary search tree.
	 */
	public Score getRoot() {
		return root;
	}

	/**
	 * <b>Description:</b>
	 * This function modifies the root of the binary search tree.
	 * @param root The root to set to the binary search tree.
	 */
	public void setRoot(Score root) {
		this.root = root;
	}
	/**
	 * <b>Description:</b>
	 * This function obtains the width of the game.
	 * @return The width of the game.
	 */
	public double getWidth() {
		return width;
	}
	/**
	 * <b>Description:</b>
	 * This function modifies the width of the game.
	 * @param width The width to set the game.
	 */
	public void setWidth(double width) {
		this.width = width;
	}
	/**
	 * <b>Description:</b>
	 * This function obtains the height of the game.
	 * @return The height of the game.
	 */
	public double getHeight() {
		return height;
	}
	/**
	 * <b>Description:</b>
	 * This function modifies the height of the game.
	 * @param width The height to set the game.
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	/**
	 * <b>Description:</b>
	 * This function obtains the first of the game.
	 * @return The first of the game.
	 */
	public Player getFirst() {
		return first;
	}
	/**
	 * <b>Description:</b>
	 * This function modifies the first player of the game.
	 * @param width The first player to set the game.
	 */
	public void setFirst(Player first) {
		this.first = first;
	}


	/**
	 * <b>Description:</b>
	 * This function obtains the first level of the game.
	 * @return the levelOne of the game.
	 */
	public Level getLevelOne() {
		return levelOne;
	}
	/**
	 * <b>Description:</b>
	 * This function obtains the second level of the game.
	 * @return the levelTwo of the game.
	 */
	public Level getLevelTwo() {
		return levelTwo;
	}
	/**
	 * <b>Description:</b>
	 * This function obtains the third level of the game.
	 * @return the levelThree of the game.
	 */
	public Level getLevelThree() {
		return levelThree;
	}
	
	
	
}
