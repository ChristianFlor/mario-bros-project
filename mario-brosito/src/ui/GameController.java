package ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Bowser;
import model.Coin;
import model.Enemy;
import model.Figure;
import model.Flower;
import model.Game;
import model.Goomba;
import model.ImagesLoader;
import model.Koopa;
import model.Mario;
import model.MisteryBlock;
import model.MovingPlatform;
import model.Mushroom;
import model.PowerUp;
import model.SimpleBlock;
import model.Slide;
import model.StaticFigure;
import thread.CoinAnimation;
import thread.EnemyDeathAnimation;
import thread.EnemyThread;
import thread.Gravity;
import thread.JumpingThread;
import thread.LevelTimeThread;
import thread.MisteryBlockAnimation;
import thread.MovementAndGravityThread;
import thread.PlatformThread;
import thread.PowerUpThread;
import thread.SpinningFireThread;

public class GameController {

	@FXML
    private Pane mainBackground;
	
	private Rectangle mainMario;
	
	private int maxRight;
	
	private int minLeft;
	
	private Game mainGame;
	
	private Scene mainScene;
	private List<Rectangle> rectan;
	private List<Rectangle> rectanCoin;
	private List<Thread> threads;
	private ImagesLoader imloMark;
	private ImagesLoader imloCoin;
	private Set<String> pressed;
	private Map<Enemy, Rectangle> enemyRectangles;

	private BufferedImage[] marioPictures;

	@FXML
	private Label timeLabel;

    @FXML
    private Label worldLabel;

    @FXML
    private Label marioLabel;

    @FXML
    private Label numberOfWorld;

    @FXML
    private Label scoreOfMario;

    @FXML
    private Label acumulatedCoins;
    
    @FXML
    private ImageView coinImage;

    @FXML
    private Label timeOfLevel;
	
	private JumpingThread jumping;
	
    @FXML
    public void initialize() {
    	jumping = new JumpingThread(this);
    	pressed = new HashSet<String>();
    	try {
			mainGame = new Game();
			imloMark= new ImagesLoader(32, 32, 1, 3,"src/uiImg/QuestionMark.png");
			imloCoin =new ImagesLoader(32, 32, 1, 3,"src/uiImg/Coin.png");
			rectan= new ArrayList<Rectangle>();
			enemyRectangles = new HashMap<Enemy, Rectangle>(); 
			rectanCoin= new ArrayList<Rectangle>();
			threads = new ArrayList<Thread>();
			misteryBlockThread();
			timeThread();
			coinThread();
			loadWorld1();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	maxRight = 685/3;
    	minLeft = 0;
    	try {
			ImagesLoader sl = new ImagesLoader(32, 32, 7, 4, mainGame.getLevelOne().getMario().getImage());
			marioPictures = sl.getSprites();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	Thread mv = new MovementAndGravityThread(this);
    	threads.add(mv);
    	mv.start();
    }
    
    public void configureScene() {
		mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				pressed.add(e.getCode().toString());
					if(e.getCode().equals(KeyCode.D)) {
						moveImage(1,0);
					}
					if(e.getCode().equals(KeyCode.A)) {
						moveImage(-1,0);
					}if(e.getCode().equals(KeyCode.W) && !jumping.isAlive() ){
						runThread(); 
					}
				
			}
			});
		mainScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ev) {
				 pressed.remove(ev.getCode().toString());
			}
		});
    }
		
    public void timeThread() {
    	LevelTimeThread lv = new LevelTimeThread(this);
    	lv.start();
    }
    
    public String isTouching() {

    	String intersects = "";
    	List<Figure> sprites = mainGame.getLevelOne().getFigures();
    	for (int i = 0; i < sprites.size() && intersects.isEmpty(); i++) {
			if(sprites.get(i) instanceof Mario)
				continue;
			Figure f = sprites.get(i);
			Figure mario = mainGame.getLevelOne().getMario();
			intersects = ((Mario) mario).isColliding(f.getPosX(), f.getPosY(), f.getWidth(), f.getHeight());
			if(intersects.equals(Mario.ISMOVINGDOWN) && sprites.get(i) instanceof Enemy) {
				if(!sprites.get(i).getImage().equals(Koopa.KOOPASHELL)) {
					EnemyDeathAnimation thread = new EnemyDeathAnimation(this, enemyRectangles.get(sprites.get(i)), (Enemy) sprites.get(i));
					thread.start();
				}
			} else if(intersects.equals(Mario.ISMOVINGUP) && sprites.get(i) instanceof MisteryBlock) {
				MisteryBlock mb =  (MisteryBlock) sprites.get(i);
				if(mb.getPower() == null && mb.getCoin() == null) {
					PowerUp pu = ((Mario) mario).nextPowerUp();
					if(pu instanceof Mushroom) {
						Rectangle r = new Rectangle(pu.getPosX(), pu.getPosY(), pu.getWidth(), pu.getHeight());
						r.setFill(new ImagePattern(new Image(Mushroom.IMAGE)));
						PowerUpThread pw = new PowerUpThread(this, r, pu);
						pw.start();
					}else if(pu instanceof Flower) {
						Rectangle r = new Rectangle(pu.getPosX(), pu.getPosY(), pu.getWidth(), pu.getHeight());
						ImagesLoader sl = null;
						try {
							sl = new ImagesLoader(32, 32, 1, 4, Flower.IMAGE);
						} catch (IOException e) {
							e.printStackTrace();
						}
						BufferedImage[] blocks = sl.getSprites();
						Image card = SwingFXUtils.toFXImage(blocks[0], null);
						r.setFill(new ImagePattern(card));
						PowerUpThread pw = new PowerUpThread(this, r, pu);
						pw.start();
					}
				}
			}
		}
    	return intersects;
    }
    
    public void exitPowerUp(Rectangle r, PowerUp p) {
    	r.setY(r.getY()-8);
    	p.setPosY(p.getPosY()-8);
	
    }
    
    public void changeEnemyImage(int a, Enemy e, Rectangle gRec) {
    	ImagesLoader sl = null;
		try {
			sl = new ImagesLoader(32, 16, 2, 1, Goomba.DEADGOOMBA);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
     	BufferedImage[] goombas = sl.getSprites();
		if(a == 1) {
			Image card = SwingFXUtils.toFXImage(goombas[0], null);
			gRec.setFill(new ImagePattern(card));
			gRec.setY(gRec.getY()+16);
			gRec.setHeight(16);
		}else if(a == 2) {
			Image card = SwingFXUtils.toFXImage(goombas[1], null);
			gRec.setFill(new ImagePattern(card));
		}else if(a == 3) {
			mainBackground.getChildren().remove(gRec);
			boolean exit = false;
			for (int i = 0; i < threads.size() && !exit; i++) {
				if(threads.get(i) instanceof EnemyThread && ((EnemyThread) threads.get(i)).getEnemy() == e) {
					((EnemyThread) threads.get(i)).deactivate();
					exit = true;
				}
			}
			mainGame.getLevelOne().getFigures().remove(e);
			exit = false;
			for (int i = 0; i < mainGame.getLevelOne().getFigures().size() && !exit; i++) {
				if(mainGame.getLevelOne().getFigures().get(i) instanceof Mario) {
					mainGame.getLevelOne().setMarioPosition(i);
					exit = true;
				}
			}
		}else if(a == 4) {
			gRec.setFill(new ImagePattern(new Image(Koopa.KOOPASHELL)));
			e.setPosY(e.getPosY()+16);
			e.setHeight(32);
			gRec.setY(gRec.getY()+16);
			gRec.setHeight(32);
			e.setImage(Koopa.KOOPASHELL);
			boolean exit = false;
			for (int i = 0; i < threads.size() && !exit; i++) {
				if(threads.get(i) instanceof EnemyThread && ((EnemyThread) threads.get(i)).getEnemy() == e) {
					((EnemyThread) threads.get(i)).deactivate();
					exit = true;
				}
			}
		}
	}
    
    
    public Figure isBetween(double yFinal, double yActual) {
    	boolean intersects = false;
    	Figure f = null;
    	List<Figure> sprites = mainGame.getLevelOne().getFigures();
    	
    	for (int i = 0; i < sprites.size() && !intersects; i++) {
    		
			if(sprites.get(i) instanceof Mario )
				continue;
			f = sprites.get(i);
			
			Figure mario = mainGame.getLevelOne().getMario();
			intersects = ((Mario) mario).isThereSomethingInBetween(f.getPosX(), f.getPosY(), f.getWidth(), f.getHeight(), yFinal, yActual);
		}
    	
    	if(!intersects) {
    		f = null;
    	}
    	
    	return f;
    }
    
    
    public String isFalling() {
    	String intersects = "";
    	Figure f = null;
    	List<Figure> sprites = mainGame.getLevelOne().getFigures();
    	
    	for (int i = 0; i < sprites.size() && intersects.isEmpty(); i++) {
    		
			if(sprites.get(i) instanceof Mario )
				continue;
			f = sprites.get(i);
			
			Figure mario = mainGame.getLevelOne().getMario();
			intersects = ((Mario) mario).isGrounded(f.getPosX(), f.getPosY(), f.getWidth(), f.getHeight());
		}
    	
    	return intersects;
    }
    
    public boolean isEnemyFalling(Figure figure, Rectangle figureRec) {
    	
    	boolean intersects = false;
    	Figure f = null;
    	List<Figure> sprites = mainGame.getLevelOne().getFigures();
    	for (int i = 0; i < sprites.size() && !intersects; i++) {
    		
			if(sprites.get(i) != figure) {
				f = sprites.get(i);
				intersects = ((Enemy) figure).enemyIsGrounded(f.getPosX(), f.getPosY(), f.getWidth(), f.getHeight());				
			}
		}
    	return intersects;
    }
    
    public void runThread(){
    	jumping = new JumpingThread(this);
    	jumping.start();
    }

	public void misteryBlockThread() {
    	Thread mba = new MisteryBlockAnimation(this);
    	threads.add(mba);
		mba.start();
    }
    public void coinThread() {
    	Thread ca = new CoinAnimation(this);
    	threads.add(ca);
    	ca.start();
    }
    public void setFill0() {
    	BufferedImage[] blocks = imloMark.getSprites();
    	
		Image card = SwingFXUtils.toFXImage(blocks[0], null);
		
		for (int i = 0; i < rectan.size(); i++) {
			rectan.get(i).setFill(new ImagePattern(card));
			
		}	
    }
    public void setFillCoin0() {
    	
    	BufferedImage[] blocks1 = imloCoin.getSprites();
		
		Image card1 = SwingFXUtils.toFXImage(blocks1[0], null);
		for (int i = 0; i < rectanCoin.size(); i++) {
			
			rectanCoin.get(i).setFill(new ImagePattern(card1));
		}	
    }
    public void setFill1() {
    	BufferedImage[] blocks = imloMark.getSprites();
    	
		Image card = SwingFXUtils.toFXImage(blocks[1], null);
		
		for (int i = 0; i < rectan.size(); i++) {
			rectan.get(i).setFill(new ImagePattern(card));
		}	
    }
    public void setFillCoin1() {
    	
    	BufferedImage[] blocks1 = imloCoin.getSprites();
		
		Image card1 = SwingFXUtils.toFXImage(blocks1[1], null);
		for (int i = 0; i < rectanCoin.size(); i++) {
			
			rectanCoin.get(i).setFill(new ImagePattern(card1));
		}	
    }
    public void setFill2() {
    	BufferedImage[] blocks = imloMark.getSprites();
    	
		Image card = SwingFXUtils.toFXImage(blocks[2], null);
		
		for (int i = 0; i < rectan.size(); i++) {
			rectan.get(i).setFill(new ImagePattern(card));
		
		}
    }
    public void setFillCoin2() {

    	BufferedImage[] blocks1 = imloCoin.getSprites();
		Image card1 = SwingFXUtils.toFXImage(blocks1[2], null);
		for (int i = 0; i < rectanCoin.size(); i++) {
		
			rectanCoin.get(i).setFill(new ImagePattern(card1));
		}
    }
    
    public void setTime(int time) {
    	timeOfLevel.setText(time+"");
    }
    
    public void distanceToEnemies() {
    	
    	Mario m = (Mario) mainGame.getLevelOne().getMario();
    	List<Enemy> enemies = mainGame.getLevelOne().getEnemies();
    	for (int i = 0; i < enemies.size(); i++) {
    		boolean near = m.isEnemyNear(enemies.get(i).getPosX());
    		boolean exit = false;
    		for (int j = 0; j < threads.size() && !exit; j++) {
    			if(threads.get(j) instanceof EnemyThread && ((EnemyThread) threads.get(j)).getEnemy() == enemies.get(i) ) {
    				exit = true;
    			}
				
			}
    		if(near && !exit) {
    			enemies.get(i).setState(Mario.ISMOVINGLEFT);
    			Thread thread = new EnemyThread(this, enemyRectangles.get(enemies.get(i)), enemies.get(i));
    			threads.add(thread);
    			thread.start();
    		}		
		}
    	
    }
    
    public void spinFire(Rectangle fireRec, Figure fire, int f, int radius, int centerX, int centerY) {
    	double x = Math.sin(Math.toRadians((double)f)) * (radius);
        double y = Math.cos(Math.toRadians((double)f)) * (radius);
        System.out.println((Math.pow(x-centerX, 2) + Math.pow(y-centerY, 2)));
        fire.setPosX(radius - Math.pow(x-centerX, 2));
        fire.setPosY(radius - Math.pow(y-centerY, 2));
        fireRec.setX(fire.getPosX());
        fireRec.setY(fire.getPosY());
    }
    
 	public void movePlatform(Rectangle platformRectangle, MovingPlatform platform) {
 		platform.setPosY(platform.getPosY()+8);
			platformRectangle.setY(platform.getPosY());
 		if(platform.getPosY() > 480) {
 			platform.setPosY(0);
 			platformRectangle.setY(platform.getPosY());
 		}
 	}
 	
 	public void makeFigureFall(Rectangle figureRec, Figure figure) {
 		figure.setPosY(figure.getPosY()+8);
 		figureRec.setY(figure.getPosY());
 	}
 	
 	public void moveEnemy(Rectangle enemyRec, Enemy enemy, int changer) throws IOException {
 	ImagesLoader sl;
	ImagesLoader sLoader;
     if(enemy.getState().equals(Mario.ISMOVINGLEFT)) {
         enemy.setPosX(enemy.getPosX()-8);
         enemyRec.setX(enemy.getPosX());
         if(enemy instanceof Koopa) {
        	sLoader = new ImagesLoader(32, 48, 1, 4, enemy.getImage());
        	BufferedImage[] koopas = sLoader.getSprites();
        	Image card = SwingFXUtils.toFXImage(koopas[0], null);
 			Image card2 = SwingFXUtils.toFXImage(koopas[1], null);
 			if(changer == 0)
				enemyRec.setFill(new ImagePattern(card2));
			else
				enemyRec.setFill(new ImagePattern(card));
         }
     }else if(enemy.getState().equals(Mario.ISMOVINGRIGHT)) {
         enemy.setPosX(enemy.getPosX()+8);
         enemyRec.setX(enemy.getPosX());
         if(enemy instanceof Koopa) {
         	sLoader = new ImagesLoader(32, 48, 1, 4, enemy.getImage());
         	BufferedImage[] koopas = sLoader.getSprites();
         	Image card = SwingFXUtils.toFXImage(koopas[2], null);
  			Image card2 = SwingFXUtils.toFXImage(koopas[3], null);
  			if(changer == 0)
 				enemyRec.setFill(new ImagePattern(card2));
 			else
 				enemyRec.setFill(new ImagePattern(card));
          }
     }
    if(enemy instanceof Goomba) {
     	sl = new ImagesLoader(32, 32, 4, 2, enemy.getImage());
     	BufferedImage[] goombas = sl.getSprites();
			Image card = SwingFXUtils.toFXImage(goombas[0], null);
			Image card2 = SwingFXUtils.toFXImage(goombas[1], null);
			if(changer == 0)
				enemyRec.setFill(new ImagePattern(card2));
			else
				enemyRec.setFill(new ImagePattern(card));
    }
    if(enemyIsTouching(enemy)) {
    	 if(enemy.getState().equals(Mario.ISMOVINGLEFT)) {
    		 enemy.setState(Mario.ISMOVINGRIGHT);
    	 }else if(enemy.getState().equals(Mario.ISMOVINGRIGHT)) {
    		 enemy.setState(Mario.ISMOVINGLEFT);
    	 }
    }
    Gravity thread = new Gravity(this, enemyRec, enemy);
	thread.start();
	for (int i = 0; i < threads.size(); i++) {
		if(threads.get(i) instanceof EnemyThread && ((EnemyThread) threads.get(i)).getEnemy().getPosY() > 480) {
			((EnemyThread) threads.get(i)).deactivate();
			mainGame.getLevelOne().getFigures().remove(((EnemyThread) threads.get(i)).getEnemy());
			boolean exit = false;
			for (int j = 0; j < mainGame.getLevelOne().getFigures().size() && !exit; j++) {
				if(mainGame.getLevelOne().getFigures().get(j) instanceof Mario) {
					mainGame.getLevelOne().setMarioPosition(j);
					exit = true;
				}
			}
		}
	}
 	}
 	
 	public boolean enemyIsTouching(Enemy enemy) {
 		boolean intersects = false;
 		List<Figure> sprites = mainGame.getLevelOne().getFigures();
 		for (int i = 0; i < sprites.size() && !intersects; i++) {
			if(enemy != sprites.get(i)) {
				intersects = enemy.enemyIsColliding(sprites.get(i).getPosX(), sprites.get(i).getPosY(), sprites.get(i).getWidth(), sprites.get(i).getHeight());
				if(intersects && sprites.get(i) instanceof Enemy) {
					if(Math.abs(enemy.getPosX() - sprites.get(i).getPosX()) < 32) {
						double diff = 32 - Math.abs(enemy.getPosX() - sprites.get(i).getPosX());
						if(enemy.getState().equals(Mario.ISMOVINGLEFT))
							enemy.setPosX(enemy.getPosX()+diff);
						else if(enemy.getState().equals(Mario.ISMOVINGRIGHT))
							enemy.setPosX(enemy.getPosX()-diff);
					}
				}
			}
		}
 		return intersects;
 	}
 
    public void moveImage(int a, double jump){
		Mario m = (Mario) mainGame.getLevelOne().getMario();

		String touch = isTouching();
			if(mainMario.getX() >= maxRight && a==1 && !touch.equals(Mario.ISMOVINGRIGHT) ) {
	    		mainMario.setX(mainMario.getX()+8);
	    		maxRight +=8;
	    		minLeft += 8;
	    		mainBackground.setTranslateX(mainBackground.getTranslateX()-8);
		    	
	    		timeLabel.setTranslateX(timeLabel.getTranslateX()+8);
	    		worldLabel.setTranslateX(worldLabel.getTranslateX()+8);
	    		marioLabel.setTranslateX(marioLabel.getTranslateX()+8);
	    		numberOfWorld.setTranslateX(numberOfWorld.getTranslateX()+8);
	    		scoreOfMario.setTranslateX(scoreOfMario.getTranslateX()+8);
	    		acumulatedCoins.setTranslateX(acumulatedCoins.getTranslateX()+8);
	    		coinImage.setTranslateX(coinImage .getTranslateX()+8);
	    		timeOfLevel.setTranslateX(timeOfLevel.getTranslateX()+8);
	    		m.setPosX(mainMario.getX());
	    	}
	    	/*else if(mainMario.getX() <= minLeft && a==-1) {
	    	}*/
	    	else if(a==1  && (!touch.equals(Mario.ISMOVINGRIGHT))) {
	    		mainMario.setX(mainMario.getX()+8);
	    		m.setPosX(mainMario.getX());
	    		//m.setState(Mario.ISMOVINGRIGHT);
	    	}else if(a==-1 && (!touch.equals(Mario.ISMOVINGLEFT))){
	        	mainMario.setX(mainMario.getX()-8);
	        	m.setPosX(mainMario.getX());
	        	//m.setState(Mario.ISMOVINGLEFT);
	        }else if(a==2){ //&& !touch.equals(Mario.ISMOVINGUP
	        	mainMario.setY(jump);
	        	m.setPosY(mainMario.getY());
	        }else if(a==3 && !touch.equals(Mario.ISMOVINGDOWN)){
	        	mainMario.setY(mainMario.getY()+8);
	        	m.setPosY(mainMario.getY());
	        }
			distanceToEnemies();
    	}
    
    public void changeMarioImage(int key) {
    	Image changed = null;
    	if(key ==0 ) {    // normal position
    		changed = SwingFXUtils.toFXImage(marioPictures[0], null);
    		mainMario.setFill(new ImagePattern(changed));
    	}
    	else if(key==1) {  // right movement
    		changed = SwingFXUtils.toFXImage(marioPictures[4], null);
    			mainMario.setFill(new ImagePattern(changed));
    	}
    	else if(key==2) {   // right movement2
    		changed = SwingFXUtils.toFXImage(marioPictures[5], null);
    		mainMario.setFill(new ImagePattern(changed));
    	}else if(key==3) {   // Right movement3
    		changed = SwingFXUtils.toFXImage(marioPictures[6], null);
    		mainMario.setFill(new ImagePattern(changed));
    	}else if(key ==4) {   // Right movement3
    		changed = SwingFXUtils.toFXImage(marioPictures[2], null);
    		mainMario.setFill(new ImagePattern(changed));
    	}else if(key ==5) {   // left movement1
    		changed = SwingFXUtils.toFXImage(marioPictures[8], null);
    		mainMario.setFill(new ImagePattern(changed));
    	}
    	else if(key ==6) {   // left movement2
    		changed = SwingFXUtils.toFXImage(marioPictures[9], null);
    		mainMario.setFill(new ImagePattern(changed));
    	}
    	else if(key ==7) {   // left movement3
    		changed = SwingFXUtils.toFXImage(marioPictures[10], null);
    		mainMario.setFill(new ImagePattern(changed));
    	}
    	
    }
    
    public void loadWorld1() throws IOException {
    	List<Figure> sprites = mainGame.getLevelOne().getFigures();
    	ImagesLoader sl = null;
    	
    	for (int i = 0; i < sprites.size(); i++) {
			Figure f = sprites.get(i);
			Rectangle rec = new Rectangle(f.getPosX(), f.getPosY(), f.getWidth(), f.getHeight());
			if(f instanceof Mario) {
				sl = new ImagesLoader(32, 32, 7, 4, f.getImage());
				BufferedImage[] marios = sl.getSprites();
				Image card = SwingFXUtils.toFXImage(marios[0], null);
				rec.setFill(new ImagePattern(card));
				mainBackground.getChildren().add(rec);
				mainMario = rec;
			}else if(f instanceof StaticFigure){
				rec.setFill(new ImagePattern(new Image(f.getImage())));
				mainBackground.getChildren().add(rec);
			}else if(f instanceof MisteryBlock) {
				sl = new ImagesLoader(32, 32, 1, 3, f.getImage());
				BufferedImage[] blocks = sl.getSprites();
				Image card = SwingFXUtils.toFXImage(blocks[0], null);
				rec.setFill(new ImagePattern(card));
				rectan.add(rec);
				mainBackground.getChildren().add(rec);
			}else if(f instanceof SimpleBlock) {
				rec.setFill(new ImagePattern(new Image(f.getImage())));
				mainBackground.getChildren().add(rec);
			}else if(f instanceof Slide) {
				if(f.getImage().equals(Slide.BIGTUBE)) {
					rec.setWidth(64); 
					rec.setHeight(160);
				}else if(f.getImage().equals(Slide.MEDIUMTUBE)) {
					rec.setWidth(64); 
					rec.setHeight(128);
				}else {
					rec.setWidth(64); 
					rec.setHeight(96);
				}
				rec.setFill(new ImagePattern(new Image(f.getImage())));
				mainBackground.getChildren().add(rec);
			}else if(f instanceof Goomba){
				sl = new ImagesLoader(32, 32, 4, 2, f.getImage());
				BufferedImage[] goombas = sl.getSprites();
				Image card = SwingFXUtils.toFXImage(goombas[0], null);
				rec.setFill(new ImagePattern(card));
				mainBackground.getChildren().add(rec);
				enemyRectangles.put((Enemy) f, rec);
			}else if(f instanceof Koopa){
				sl = new ImagesLoader(32, 48, 1, 4, f.getImage());
				BufferedImage[] koopas = sl.getSprites();
				Image card = SwingFXUtils.toFXImage(koopas[0], null);
				rec.setFill(new ImagePattern(card));
				mainBackground.getChildren().add(rec);
				enemyRectangles.put((Enemy) f, rec);
			}
		}
    }
    
    public void loadWorld2() throws IOException {
    	List<Figure> sprites = mainGame.getLevelTwo().getFigures();
    	ImagesLoader sl = null;
    	
    	for (int i = 0; i < sprites.size(); i++) {
			Figure f = sprites.get(i);
			Rectangle rec = new Rectangle(f.getPosX(), f.getPosY(), f.getWidth(), f.getHeight());
			if(f instanceof Mario) {
				sl = new ImagesLoader(32, 32, 7, 4, f.getImage());
				BufferedImage[] marios = sl.getSprites();
				Image card = SwingFXUtils.toFXImage(marios[0], null);
				rec.setFill(new ImagePattern(card));
				mainBackground.getChildren().add(rec);
				mainMario = rec;
			}else if(f instanceof StaticFigure){
				rec.setFill(new ImagePattern(new Image(f.getImage())));
				mainBackground.getChildren().add(rec);
			}else if(f instanceof MisteryBlock) {
				sl = new ImagesLoader(32, 32, 1, 3, f.getImage());
				BufferedImage[] blocks = sl.getSprites();
				Image card = SwingFXUtils.toFXImage(blocks[0], null);
				rec.setFill(new ImagePattern(card));
				rectan.add(rec);
				mainBackground.getChildren().add(rec);
			}else if(f instanceof SimpleBlock) {
				rec.setFill(new ImagePattern(new Image(f.getImage())));
				mainBackground.getChildren().add(rec);
			}else if(f instanceof Slide) {
				rec.setFill(new ImagePattern(new Image(f.getImage())));
				mainBackground.getChildren().add(rec);
			}else if(f instanceof Goomba){
				sl = new ImagesLoader(32, 32, 4, 2, f.getImage());
				BufferedImage[] goombas = sl.getSprites();
				Image card = SwingFXUtils.toFXImage(goombas[0], null);
				rec.setFill(new ImagePattern(card));
				mainBackground.getChildren().add(rec);
			}else if(f instanceof Koopa){
				sl = new ImagesLoader(32, 32, 9, 15, f.getImage());
				BufferedImage[] koopas = sl.getSprites();
				Image card = SwingFXUtils.toFXImage(koopas[5], null);
				rec.setFill(new ImagePattern(card));
				mainBackground.getChildren().add(rec);
			}else if(f instanceof MovingPlatform){
				rec.setFill(new ImagePattern(new Image(f.getImage())));
				mainBackground.getChildren().add(rec);
				Thread thread = new PlatformThread(this, (MovingPlatform) f, rec);
				threads.add(thread);
				thread.start();
			}else if(f instanceof Coin){
				sl = new ImagesLoader(32, 32, 1, 3, f.getImage());
				BufferedImage[] coins = sl.getSprites();
				Image card = SwingFXUtils.toFXImage(coins[0], null);
				rec.setFill(new ImagePattern(card));
				rectanCoin.add(rec);
				mainBackground.getChildren().add(rec);
			}
    	}
    }

    public void loadWorld3() throws IOException {
    	List<Figure> sprites = mainGame.getLevelThree().getFigures();
    	ImagesLoader sl = null;
    	int counter = 1;
    	int centerX = 0;
    	int centerY = 0;
    	for (int i = 0; i < sprites.size(); i++) {
			Figure f = sprites.get(i);
			Rectangle rec = new Rectangle(f.getPosX(), f.getPosY(), f.getWidth(), f.getHeight());
			if(f instanceof Mario) {
				sl = new ImagesLoader(32, 32, 7, 4, f.getImage());
				BufferedImage[] marios = sl.getSprites();
				Image card = SwingFXUtils.toFXImage(marios[0], null);
				rec.setFill(new ImagePattern(card));
				mainBackground.getChildren().add(rec);
				mainMario = rec;
			}else if(f instanceof StaticFigure){
				if(f.getImage().equals(StaticFigure.IRON)) {
					sl = new ImagesLoader(32, 32, 1, 4, f.getImage());
					BufferedImage[] irons = sl.getSprites();
					Image card = SwingFXUtils.toFXImage(irons[0], null);
					rec.setFill(new ImagePattern(card));
					mainBackground.getChildren().add(rec);
				}else {
					rec.setFill(new ImagePattern(new Image(f.getImage())));
					mainBackground.getChildren().add(rec);
				}
			}else if(f instanceof MisteryBlock) {
				sl = new ImagesLoader(32, 32, 1, 3, f.getImage());
				BufferedImage[] blocks = sl.getSprites();
				Image card = SwingFXUtils.toFXImage(blocks[0], null);
				rec.setFill(new ImagePattern(card));
				rectan.add(rec);
				mainBackground.getChildren().add(rec);
			}else if(f instanceof SimpleBlock) {
				sl = new ImagesLoader(16, 16, 1, 4, f.getImage());
				BufferedImage[] fires = sl.getSprites();
				Image card = SwingFXUtils.toFXImage(fires[0], null);
				rec.setFill(new ImagePattern(card));
				mainBackground.getChildren().add(rec);
				counter++;
				if(counter == 1) {
					centerX = (int) f.getPosX();
					centerY = (int) f.getPosY();
				}
				SpinningFireThread thread = new SpinningFireThread(this, rec, f, 16*counter, centerX, centerY);
				thread.start();
				if(counter == 7)
					counter = 1;
			}else if(f instanceof Bowser){
				sl = new ImagesLoader(64, 64, 2, 3, f.getImage());
				BufferedImage[] boss = sl.getSprites();
				Image card = SwingFXUtils.toFXImage(boss[0], null);
				rec.setFill(new ImagePattern(card));
				mainBackground.getChildren().add(rec);
			}
		}
    }
    
	public Scene getMainScene() {
		return mainScene;
	}

	public void setMainScene(Scene mainScene) {
		this.mainScene = mainScene;
	}

	/**
	 * @return the mainMario
	 */
	public Rectangle getMainMario() {
		return mainMario;
	}

	/**
	 * @return the mainGame
	 */
	public Game getMainGame() {
		return mainGame;
	}

	/**
	 * @return the pressed
	 */
	public Set<String> getPressed() {
		return pressed;
	}
	
	public GameController getController() {
		return this;
	}
	
	 /**
		 * @return the jumping
		 */
		public JumpingThread getJumping() {
			return jumping;
		}

	public void closeWindow() {
		for (int i = 0; i < threads.size(); i++) {
			Thread t = threads.get(i);
			if(t.isAlive()) {
				if(t instanceof CoinAnimation)
					((CoinAnimation) t).deactivate();
				else if(t instanceof EnemyThread)
					((EnemyThread) t).deactivate();
				else if(t instanceof LevelTimeThread)
					((LevelTimeThread) t).deactivate();
				else if(t instanceof MisteryBlockAnimation)
					((MisteryBlockAnimation) t).deactivate();
				else if(t instanceof MovementAndGravityThread)
					((MovementAndGravityThread) t).deactivate();
				else
					((PlatformThread) t).deactivate();
			}
		}
	}

}
