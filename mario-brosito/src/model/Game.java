package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
	// -----------------------------------------------------------------
    // Constant
    // -----------------------------------------------------------------
	public static final double INCREASEMENT = 90.0;
	// -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

	private Level levelOne;
	private Level levelTwo;
	private Level levelThree;
	
	private ImagesLoader images;
	private SoundsLoader sounds;
	
	private Score root;
	private Player first;
	private double width;
	private double height;

	public Game() throws IOException {
		levelOne = new Level();
		levelOne.loadLevel(Level.LEVEL_ONE_PATH);
		levelTwo = new Level();
		levelTwo.loadLevel(Level.LEVEL_TWO_PATH);
		levelThree = new Level();
		levelThree.loadLevel(Level.LEVEL_THREE_PATH);
		initPlayers();
	}
	
	// -----------------------------------------------------------------
    // Methods for add List
    // -----------------------------------------------------------------
	public void addPlayer(String n, String nick, double s) {
		Player p= new Player(n,nick,s);
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
	// -----------------------------------------------------------------
    // Methods for add tree
    // -----------------------------------------------------------------
	
	public void addScore(Player p) {
		Score s= new Score(p.getName(), p.getScore());
		if(root == null) {
			root= s;
		}else {
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
	// -----------------------------------------------------------------
    // Methods for show tree
    // -----------------------------------------------------------------
	public void printTree(Score current) {
		if(current.getLeft() != null) {
			printTree(current.getLeft());
		}
		System.out.println(current);
		if(current.getRight() != null) {
			printTree(current.getRight());
		}
	}

	public void increaseBounds() {
		double x = increaseBoundsX(root);
		if(x >= width) {
			this.width = x+20;
		}
	}
	
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
	public Player searchPlayer(String name) {
		return searchPlayer(name, first);
	}
	
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
		Player f =  p[index];
		return f;
	}
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
	
	public void initPlayers() {
		addPlayer("Carlos","Carlosches",90.0);
		addScore(searchPlayer("Carlos"));
		addPlayer("Cesar","Sleeptight",100);
		addScore(searchPlayer("Cesar"));
		addPlayer("Ana","Anamvgd",80);
		addScore(searchPlayer("Ana"));
		addPlayer("Christian","Rolfman",30);
		addScore(searchPlayer("Christian"));
		addPlayer("Alejandra","ale",58);
		addScore(searchPlayer("Alejandra"));
	}
	public int sizeOfPlayer(){
		int size=0;
		Player current = first;
		while(current != null){
			size++;
			current = current.getNext();
		}
		return size;
	}
	
	public void assignePositions() {
		assignePositions(root, 0, this.getWidth(), 0, this.getHeight() / this.getTreeHeight());
	}
	
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
	
	public void assignePositionsList() {
		double x = this.width/this.first.size();
		double y = this.height/2;
		assignePositionsList(first, x, y);
	}
	private void assignePositionsList(Player current, double x, double y) {
		if(current != null) {
			current.setX(x);
			current.setY(y);
			assignePositionsList(current.getNext(), x + 80, y);
		}
	}

	public List<Score> inorderListOfScore() {
		return inorderListOfScore(root);
	}
	
	private List<Score> inorderListOfScore(Score current){
		List<Score> l = new ArrayList<Score>();
		if(current != null) {
			l.addAll(inorderListOfScore(current.getLeft()));
			l.add(current);
			l.addAll(inorderListOfScore(current.getRight()));
		}
		return l;
	}
	
	public List<Score> preorderListOfScore(){
		List<Score> lis= new ArrayList<>();
		preorderListOfScore(root,lis);
		return lis;
	}
	private void preorderListOfScore(Score current,List<Score> lis){
		if(current != null) {
			lis.add(current);
			preorderListOfScore(current.getLeft(),lis);
			preorderListOfScore(current.getRight(),lis);
			
		}
	}
	public List<Score> postorderListOfScore() {
		return postorderListOfScore(root);
	}
	
	private List<Score> postorderListOfScore(Score current){
		List<Score> l = new ArrayList<Score>();
		if(current != null) {
			l.addAll(postorderListOfScore(current.getLeft()));
			l.addAll(postorderListOfScore(current.getRight()));
			l.add(current);
		}
		return l;
	}
	

	// -----------------------------------------------------------------
    // Methods Atributes
    // -----------------------------------------------------------------
	public Player[] getPlayersToArray() {

		Player[] players;
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
	public int getTreeHeight() {
		return getTreeHeight(root);
	}
	public int getTreeHeight(Score current) {
		if(current != null) {
			return Math.max(getTreeHeight(current.getLeft()), getTreeHeight(current.getRight())) + 1;
		}
		return 0;
	}
	public Score getRoot() {
		return root;
	}

	public void setRoot(Score root) {
		this.root = root;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public Player getFirst() {
		return first;
	}
	public void setFirst(Player first) {
		this.first = first;
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
