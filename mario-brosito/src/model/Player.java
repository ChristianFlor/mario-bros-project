package model;
import java.security.SecureRandom;

public class Player {

	public Player() {
		
	}
	public static final String MARIO ="https://www.smashbros.com/wiiu-3ds/images/character/mario/main.png";
	public static final String LUIGI ="https://freepngimg.com/thumb/luigi/21913-5-luigi.png";
	public static final String KOOPAS ="https://www.mariowiki.com/images/5/5c/SuperMarioParty_KoopaTroopa.png";
	public static final String BOWSER ="https://vignette.wikia.nocookie.net/mario/images/e/e1/Bowser-0.png/revision/latest?cb=20161119195258&path-prefix=es";
	public static final String TOAD ="http://clipart-library.com/images/Bcgopdxc8.png";
	
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
		//this.photo = "https://robohash.org/numquamquaeratunde.bmp";
		selectImg(s);
		this.setX(x);
		this.setY(y);
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
