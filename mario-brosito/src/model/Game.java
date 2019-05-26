package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
	
	
	private Level levelOne;
	private Level levelTwo;
	private Level levelThree;
	
	private ImagesLoader images;
	private SoundsLoader sounds;
	
	private Score root;
	private double width;
	private double height;
	public static final double INCREASEMENT = 90.0;
	public Game() throws IOException {
		levelOne = new Level();
		levelOne.loadLevel(Level.LEVEL_ONE_PATH);
		levelTwo = new Level();
		levelTwo.loadLevel(Level.LEVEL_TWO_PATH);
	}
	
	public void initPlayers() {

		addScore("Carlos","Carlosches",90);
		addScore("Cesar","Sleeptight",100);
		addScore("Ana","Anamvgd",80);
		addScore("Christian","Rolfman",30);
		addScore("Alejandra","ale",58);
	}
	
	public void addScore(String name,String nick, int score) {
		Score s = new Score(name,nick, score);
		
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
	}public Score searchName(String id) {
		
		Score current = root;
		boolean exit = false;
		
		
		while(!exit && current != null) {
			
			if(current.getName().equalsIgnoreCase(id)) {
				exit = true;
			}else {
				if(current.getName().compareTo(id) >0) {
					current = current.getLeft();
				}
				else {
					current = current.getRight();
				}
			}
			
		}
		
		return current;
		
	}
	public Score searchNick(String id) {
		
		Score current = root;
		boolean exit = false;
		
		
		while(!exit && current != null) {
			
			if(current.getNickName().equalsIgnoreCase(id)) {
				exit = true;
			}else {
				if(current.getNickName().compareTo(id) >0) {
					current = current.getLeft();
				}
				else {
					current = current.getRight();
				}
			}
			
		}
		
		return current;
		
	}
	/*
	public Score searchNick(String nick) {
		return searchNick(nick, root);
	}
	
	private Score searchNick(String nick, Score current) {
		if(current != null) {
			if(current.getName().compareTo(nick) > 0) {
				return searchNick(nick, current.getLeft());
			} else if(current.getName().compareTo(nick) < 0) {
				return searchNick(nick, current.getRight());
			} else {
				return current;
			}
		}
		return null;
	}
	*/
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
	public void printTree(Score current) {
		if(current.getLeft() != null) {
			printTree(current.getLeft());
		}
		System.out.println(current);
		if(current.getRight() != null) {
			printTree(current.getRight());
		}
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
