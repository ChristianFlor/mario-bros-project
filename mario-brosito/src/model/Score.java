package model;

public class Score implements Comparable<Score> {
	
	private String name;
	private int score;
	
	private Score right;
	private Score left;
	

	public Score(String n, int s) {
		this.name = n;
		this.score = s;
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public Score getRight() {
		return right;
	}


	public void setRight(Score right) {
		this.right = right;
	}


	public Score getLeft() {
		return left;
	}


	public void setLeft(Score left) {
		this.left = left;
	}

	@Override
	public int compareTo(Score esp) {
		return this.name.compareTo(esp.name);
	}
	public void replaceData(Score esp) {
		setName(esp.getName());
		setScore(esp.getScore());

	}
	

}
