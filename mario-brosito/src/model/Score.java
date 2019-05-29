package model;

public class Score {
	
	
	
	public static final String MARIO ="https://www.smashbros.com/wiiu-3ds/images/character/mario/main.png";
	public static final String LUIGI ="https://freepngimg.com/thumb/luigi/21913-5-luigi.png";
	public static final String KOOPAS ="https://www.mariowiki.com/images/5/5c/SuperMarioParty_KoopaTroopa.png";
	public static final String BOWSER ="https://vignette.wikia.nocookie.net/mario/images/e/e1/Bowser-0.png/revision/latest?cb=20161119195258&path-prefix=es";
	public static final String TOAD ="http://clipart-library.com/images/Bcgopdxc8.png";
	
	private String name;
	private double score;
	private String photo;
	
	private Score right;
	private Score left;
	
	private double x;
	private double y;
	
	public Score(String n, double s) {
		this.name = n;
		this.score = s;
		selectImg(s);
		this.setX(x);
		this.setY(y);
	}
	public String toString() {
		return name+score;
	}
	public void selectImg(double score) {
		if(score<=50) {
			this.setPhoto(BOWSER);
		}else if(score > 50 && score<=60) {
			this.setPhoto(TOAD);
		}else if(score > 60 && score<=80) {
			this.setPhoto(KOOPAS);
		}else if(score > 80 && score<=90) {
			this.setPhoto(LUIGI);
		}else if(score > 90 && score<=100) {
			this.setPhoto(MARIO);
		}
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getScore() {
		return score;
	}


	public void setScore(double score) {
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


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
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
