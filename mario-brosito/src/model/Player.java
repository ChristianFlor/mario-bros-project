package model;
import java.security.SecureRandom;

public class Player {

	public Player() {
		
	}
	public static final String MARIO ="/player-interface/src/uiGif/mario.gif";
	public static final String LUIGI ="/player-interface/src/uiGif/luigi.gif";
	public static final String KOOPAS ="/player-interface/src/uiGif/Koopas.gif";
	public static final String BOWSER ="/player-interface/src/uiGif/bowser.gif";
	public static final String TOAD ="/player-interface/src/uiGif/toad.gif";
	
	private String name;
	private String nickName;
	private double score;
	private String id;
	private String photo;
	
	private Player next;
	private Player prev;
	private double x;
	private double y;
	public Player(String n, String nick, double s) {
		SecureRandom r = new SecureRandom();
		this.name = n;
		this.nickName=nick;
		this.score = s;
		String in= name.substring(0, 2);
		this.id= in+(r.nextInt(10000));
		this.photo = "https://robohash.org/numquamquaeratunde.bmp";
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
	public String toString() {
		return name+score;
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


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public Player getNext() {
		return next;
	}

	public void setNext(Player next) {
		this.next = next;
	}

	public Player getPrev() {
		return prev;
	}

	public void setPrev(Player prev) {
		this.prev = prev;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public int size() {
		int s = 1;
		if(next != null) {
			s+= next.size();
		}
		return s;
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
