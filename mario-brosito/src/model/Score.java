package model;

public class Score {
	
	
	public static final String MARIO ="/player-interface/src/uiGif/mario.gif";
	public static final String LUIGI ="/player-interface/src/uiGif/luigi.gif";
	public static final String KOOPAS ="/player-interface/src/uiGif/Koopas.gif";
	public static final String BOWSER ="/player-interface/src/uiGif/bowser.gif";
	public static final String TOAD ="/player-interface/src/uiGif/toad.gif";
	
	
	
	private String name;
	private String nickName;
	private int score;
	private String photo;
	
	private Score right;
	private Score left;
	
	private double x;
	private double y;
	
	public Score(String n, String nick, int s) {
		this.name = n;
		this.nickName=nick;
		this.score = s;
		this.photo = "https://www.smashbros.com/wiiu-3ds/images/character/mario/main.png";
		//selectImg(score);
	}

	public void selectImg(int score) {
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


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
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


	
}
	
