package ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.sound.sampled.Clip;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
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
import model.SoundsLoader;
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
import thread.MisteryBlockHitThread;
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
	private Map<Figure, Rectangle> figureRectangles;
	private boolean pause;
	public boolean getPause() {
		return pause;
	}
	private Clip ground;
	public Clip getClip() {
		return ground;
	}
	private BufferedImage[] marioPictures;
	
	private BufferedImage[] bigMarioPictures;
	
	private BufferedImage[] fireMarioPictures;

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
	
	private SoundsLoader sound;
	
    @FXML
    public void initialize() {
    	sound = new SoundsLoader();
    	pause=false;
    	jumping = new JumpingThread(this);
    	pressed = new HashSet<String>();
    	try {
    		
			mainGame = new Game();
			imloMark= new ImagesLoader(32, 32, 1, 3,"src/uiImg/QuestionMark.png");
			imloCoin =new ImagesLoader(32, 32, 1, 3,"src/uiImg/Coin.png");
			rectan= new ArrayList<Rectangle>();
			figureRectangles = new HashMap<Figure, Rectangle>(); 
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
			sl = new ImagesLoader(32, 64, 7, 4, Mario.BIGMARIO);
			bigMarioPictures = sl.getSprites();
			sl = new ImagesLoader(32, 64, 7, 4, Mario.FIREMARIO);
			fireMarioPictures = sl.getSprites();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	Thread mv = new MovementAndGravityThread(this);
    	threads.add(mv);
    	mv.start();
    	
    	
    }
    public void closeWindow() {
    	pause=true;
    	ground.stop();
		for (int i = 0; i < threads.size(); i++) {
			Thread t = threads.get(i);
			if(t.isAlive()) {
				if(t instanceof CoinAnimation) {
					((CoinAnimation) t).deactivate();
				}else if(t instanceof EnemyThread) {
					((EnemyThread) t).deactivate();
				}else if(t instanceof LevelTimeThread) {
					((LevelTimeThread) t).deactivate();
				}else if(t instanceof MisteryBlockAnimation) {
					((MisteryBlockAnimation) t).deactivate();
				}else if(t instanceof MovementAndGravityThread) {
					((MovementAndGravityThread) t).deactivate();
				}else {
					((PlatformThread) t).deactivate();
				}
			}
		}
	}
    @SuppressWarnings("deprecation")
	public void pause() {
    	pause=true;
    	ground.stop();
		for (int i = 0; i < threads.size(); i++) {
			Thread t = threads.get(i);
			if(t.isAlive()) {
				if(t instanceof CoinAnimation) {
					((CoinAnimation) t).suspend();
				}else if(t instanceof EnemyThread) {
					((EnemyThread) t).suspend();
				}else if(t instanceof LevelTimeThread) {
					((LevelTimeThread) t).suspend();
				}else if(t instanceof MisteryBlockAnimation) {
					((MisteryBlockAnimation) t).suspend();
				}else if(t instanceof MovementAndGravityThread) {
					((MovementAndGravityThread) t).suspend();
				}else {
					((PlatformThread) t).suspend();
				}
			}
		}
	}
    @SuppressWarnings("deprecation")
	public void continues() {
    	pause=false;
    	ground.start();
		for (int i = 0; i < threads.size(); i++) {
			Thread t = threads.get(i);
			if(t.isAlive()) {
				if(t instanceof CoinAnimation) {
					((CoinAnimation) t).resume();
				}else if(t instanceof EnemyThread) {
					((EnemyThread) t).resume();
				}else if(t instanceof LevelTimeThread) {
					((LevelTimeThread) t).resume();
				}else if(t instanceof MisteryBlockAnimation) {
					((MisteryBlockAnimation) t).resume();
				}else if(t instanceof MovementAndGravityThread) {
					((MovementAndGravityThread) t).resume();
				}else {
					((PlatformThread) t).resume();
				}
			}
		}
	}
    public void configureScene() {
		mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				pressed.add(e.getCode().toString());
				
				if(!pause) {
					if(e.getCode().equals(KeyCode.D)) {
						moveImage(1,0);
					}
					if(e.getCode().equals(KeyCode.A)) {
						moveImage(-1,0);
					}if(e.getCode().equals(KeyCode.W) && !jumping.isAlive() ){
						Clip c;
						if(mainGame.getLevelOne().getMario().getHeight() == 64) {
							 c= sound.loadSounds(7);
						}else {
							 c= sound.loadSounds(6);
						}
						c.start();
						runThread(); 
					}if(e.getCode().equals(KeyCode.ESCAPE)) {
						Clip bang = sound.loadSounds(25);
				    	bang.start();
						pause();
					}
				}else {
					if(e.getCode().equals(KeyCode.Q)) {
						Clip bang = sound.loadSounds(25);
				    	bang.start();
				    	continues();
					}
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
    	threads.add(lv);
    	lv.start();

    	threads.add(lv);

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
			if(intersects.equals(Mario.ISMOVINGUP) && (f instanceof MisteryBlock))
			System.out.println(intersects.equals(Mario.ISMOVINGUP) + " " + (sprites.get(i) instanceof MisteryBlock));
			if(intersects.equals(Mario.ISMOVINGDOWN) && f instanceof Enemy) {
				if(!sprites.get(i).getImage().equals(Koopa.KOOPASHELL)) {
					EnemyDeathAnimation thread = new EnemyDeathAnimation(this, figureRectangles.get(sprites.get(i)), (Enemy) sprites.get(i));
					thread.start();
				}
			}else if(intersects.equals(Mario.ISMOVINGUP) && (f instanceof MisteryBlock)) {
				MisteryBlock mb =  (MisteryBlock) f;
				ImagesLoader sl = null;
				try {
					sl = new ImagesLoader(32, 32, 1, 4, StaticFigure.IRON);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				BufferedImage[] b = sl.getSprites();
				Image card = SwingFXUtils.toFXImage(b[0], null);
				figureRectangles.get(mb).setFill(new ImagePattern(card));
				rectan.remove(figureRectangles.get(mb));
				if(mb.getPower() == null && mb.getCoin() == null && !mb.getImage().equals(StaticFigure.IRON)) {
					mb.setImage(StaticFigure.IRON);
					PowerUp pu = ((Mario) mario).nextPowerUp();
					mainGame.getLevelOne().getFigures().add(pu);
					if(pu instanceof Mushroom) {
						Rectangle r = new Rectangle(mb.getPosX(), mb.getPosY(), 32, 32);
						pu.setPosX(mb.getPosX()); pu.setPosY(mb.getPosY());
						r.setFill(new ImagePattern(new Image(Mushroom.IMAGE)));
						MisteryBlockHitThread pw = new MisteryBlockHitThread(this, r, pu);
						pw.start();
						threads.add(pw);
					}else if(pu instanceof Flower) {
						Rectangle r = new Rectangle(mb.getPosX(), mb.getPosY(), 32, 32);
						pu.setPosX(mb.getPosX()); pu.setPosY(mb.getPosY());
						try {
							sl = new ImagesLoader(32, 32, 1, 4, Flower.IMAGE);
						} catch (IOException e) {
							e.printStackTrace();
						}
						BufferedImage[] blocks = sl.getSprites();
						Image cardd = SwingFXUtils.toFXImage(blocks[0], null);
						r.setFill(new ImagePattern(cardd));
						MisteryBlockHitThread pw = new MisteryBlockHitThread(this, r, pu);
						pw.start();
						threads.add(pw);
					}
				}else if(mb.getCoin()!=null && !mb.getImage().equals(StaticFigure.IRON)) {
					mb.setImage(StaticFigure.IRON);
					Rectangle r = new Rectangle(mb.getPosX(), mb.getPosY()-32, 32, 32);
					try {
						sl = new ImagesLoader(32, 32, 1, 4, Coin.COININBLOCK);
					} catch (IOException e) {
						e.printStackTrace();
					}
					BufferedImage[] blocks = sl.getSprites();
					Image cardd = SwingFXUtils.toFXImage(blocks[0], null);
					r.setFill(new ImagePattern(cardd));
					MisteryBlockHitThread pw = new MisteryBlockHitThread(this, r, null);
					pw.start();
				}
			}
		}
    	return intersects;
    }
    
    public void animateMisteryBlockCoin(Rectangle r, int iteration, int move) {
    	
    	if(iteration == -1) {
    		mainBackground.getChildren().add(r);
    	}else if(iteration == -2){
    		mainBackground.getChildren().remove(r);
    		int coins = Integer.parseInt(acumulatedCoins.getText().split(" ")[1]);
			coins++;
			if(coins < 10)
				acumulatedCoins.setText("X 0" + coins);
			else
				acumulatedCoins.setText("X " + coins);
    	}else {
    		ImagesLoader sl = null;
        	try {
    			sl = new ImagesLoader(32, 32, 1, 4, Coin.COININBLOCK);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        	if(move == 0) {
        		r.setY(r.getY()-8);
        	}else
        		r.setY(r.getY()+10);
    		BufferedImage[] blocks = sl.getSprites();
    		Image cardd = SwingFXUtils.toFXImage(blocks[iteration], null);
    		r.setFill(new ImagePattern(cardd));
    	}
    }
    public void exitPowerUp(Rectangle r, PowerUp p, int iteration) {
    	if(iteration == 0) {
    		mainBackground.getChildren().add(r);
    		r.toBack();
    	}
    	p.setPosY(p.getPosY()-8);
    	r.setY(p.getPosY());
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

    	List<Figure> sprites =mainGame.getLevelOne().getFigures();

    	for (int i = 0; i < mainGame.getLevelOne().getFigures().size() && intersects.isEmpty(); i++) {
			if(sprites.get(i) instanceof Mario )
				continue;
			f = sprites.get(i);
			
			Figure mario = mainGame.getLevelOne().getMario();
			intersects = ((Mario) mario).isGrounded(f.getPosX(), f.getPosY(), f.getWidth(), f.getHeight());
		}
    	
    	return intersects;
    }
    
    public boolean isFigureFalling(Figure figure, Rectangle figureRec) {
    	
    	boolean intersects = false;
    	Figure f = null;
    	List<Figure> sprites = mainGame.getLevelOne().getFigures();
    	for (int i = 0; i < sprites.size() && !intersects; i++) {
    		
			if(sprites.get(i) != figure) {
				f = sprites.get(i);
				if(figure instanceof Enemy)
					intersects = ((Enemy) figure).enemyIsGrounded(f.getPosX(), f.getPosY(), f.getWidth(), f.getHeight());
				else if(figure instanceof Mushroom) {
					intersects = ((Mushroom) figure).mushroomIsGrounded(f.getPosX(), f.getPosY(), f.getWidth(), f.getHeight());
				}
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
    			Thread thread = new EnemyThread(this, figureRectangles.get(enemies.get(i)), enemies.get(i));
    			threads.add(thread);
    			thread.start();
    		}		
		}
    	
    }
    
    public void spinFire(Rectangle fireRec, Figure fire, int f, int radius, int centerX, int centerY) {
    	double x = Math.sin(Math.toRadians((double)f)) * (radius);
        double y = Math.cos(Math.toRadians((double)f)) * (radius);
        fire.setPosX(radius - Math.pow(x-centerX, 2));
        fire.setPosY(radius - Math.pow(y-centerY, 2));
        fireRec.setX(fire.getPosX());
        fireRec.setY(fire.getPosY());
    }
    
 	public void movePlatform(Rectangle platformRectangle, MovingPlatform platform) {
 		platform.setPosY(platform.getPosY()+8);
			platformRectangle.setY(platform.getPosY());
 		if(platform.getPosY() > 550) {
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
    if(figureIsTouching(enemy)) {
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
			//mainGame.getLevelOne().getFigures().remove(((EnemyThread) threads.get(i)).getEnemy());
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
 	
 	public boolean figureIsTouching(Figure figure) {
 		boolean intersects = false;
 		List<Figure> sprites = mainGame.getLevelOne().getFigures();
 		for (int i = 0; i < sprites.size() && !intersects; i++) {
			if(figure != sprites.get(i)) {
				if(figure instanceof Enemy) {
					intersects = ((Enemy) figure).enemyIsColliding(sprites.get(i).getPosX(), sprites.get(i).getPosY(), sprites.get(i).getWidth(), sprites.get(i).getHeight());
					if(intersects && sprites.get(i) instanceof Enemy) {
						if(Math.abs(figure.getPosX() - sprites.get(i).getPosX()) < 32) {
							double diff = 32 - Math.abs(figure.getPosX() - sprites.get(i).getPosX());
							if(((Enemy) figure).getState().equals(Mario.ISMOVINGLEFT))
								figure.setPosX(figure.getPosX()+diff);
							else if(((Enemy) figure).getState().equals(Mario.ISMOVINGRIGHT))
								figure.setPosX(figure.getPosX()-diff);
						}
					}
				}else if(figure instanceof PowerUp) {
					intersects = ((PowerUp) figure).powerUpIsColliding(sprites.get(i).getPosX(), sprites.get(i).getPosY(), sprites.get(i).getWidth(), sprites.get(i).getHeight());
					if(intersects && sprites.get(i) instanceof Mario) {
						for (int j = 0; j < threads.size(); j++) {
							if( threads.get(j) instanceof MisteryBlockHitThread && ((MisteryBlockHitThread) threads.get(j)).getPowerUp() == figure) {
								((MisteryBlockHitThread) threads.get(j)).deactivate();
								threads.remove(j);
							}
						}
						mainGame.getLevelOne().getFigures().remove(figure);
						boolean exit = false;
						for (int j = 0; j < mainGame.getLevelOne().getFigures().size() && !exit; j++) {
							if(mainGame.getLevelOne().getFigures().get(j) instanceof Mario) {
								mainGame.getLevelOne().setMarioPosition(j);
								exit = true;
							}
						}
						Figure mario = mainGame.getLevelOne().getMario();
						
						if(figure instanceof Mushroom) {
							Clip clip = sound.loadSounds(12);
							clip.start();
							mainGame.getLevelOne().getMario().setPowerState((PowerUp) figure);
							mario.setImage(Mario.BIGMARIO);
							Image cardd = SwingFXUtils.toFXImage(bigMarioPictures[0], null);
							mainMario.setFill(new ImagePattern(cardd));
							mainMario.setHeight(64);
							mainGame.getLevelOne().getMario().setHeight(64);
							mainGame.getLevelOne().getMario().setPosY(mainGame.getLevelOne().getMario().getPosY()-32);
							mainMario.setY(mainGame.getLevelOne().getMario().getPosY());
						}
						if(figure instanceof Flower) {
							Clip clip = sound.loadSounds(12);
							clip.start();
							mainGame.getLevelOne().getMario().setPowerState((PowerUp) figure);
							mario.setImage(Mario.FIREMARIO);
							Image cardd = SwingFXUtils.toFXImage(fireMarioPictures[0], null);
							mainMario.setFill(new ImagePattern(cardd));
							mainMario.setHeight(64);
							mainGame.getLevelOne().getMario().setHeight(64);
							mainGame.getLevelOne().getMario().setPosY(mainGame.getLevelOne().getMario().getPosY()-32);
							mainMario.setY(mainGame.getLevelOne().getMario().getPosY());
						}
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
    		if(mainGame.getLevelOne().getMario().getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[0], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[0], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[0], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}
    	else if(key==1) {  // right movement
    		if(mainGame.getLevelOne().getMario().getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[4], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[4], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[4], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}
    	else if(key==2) {   // right movement2
    		if(mainGame.getLevelOne().getMario().getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[5], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[5], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[5], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}else if(key==3) {   // Right movement3
    		if(mainGame.getLevelOne().getMario().getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[6], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[6], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[6], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}else if(key ==4) {   // Right movement3
    		if(mainGame.getLevelOne().getMario().getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[2], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[2], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[2], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}else if(key ==5) {   // left movement1
    		if(mainGame.getLevelOne().getMario().getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[8], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[8], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[8], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}
    	else if(key ==6) {   // left movement2
    		if(mainGame.getLevelOne().getMario().getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[9], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[9], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[9], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}
    	else if(key ==7) {   // left movement3
    		if(mainGame.getLevelOne().getMario().getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[10], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[10], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[10], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}else if(key == 8) {   // change to right
    		if(mainGame.getLevelOne().getMario().getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[7], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[7], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[7], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}else if(key == 9) {  // change to left
    		if(mainGame.getLevelOne().getMario().getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[11], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[11], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(mainGame.getLevelOne().getMario().getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[11], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}
    	
    }
    
    public void loadWorld1() throws IOException {
    	
    	ground = sound.loadSounds(0);
    	ground.stop();
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
				figureRectangles.put(f, rec);
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
				figureRectangles.put((Enemy) f, rec);
			}else if(f instanceof Koopa){
				sl = new ImagesLoader(32, 48, 1, 4, f.getImage());
				BufferedImage[] koopas = sl.getSprites();
				Image card = SwingFXUtils.toFXImage(koopas[0], null);
				rec.setFill(new ImagePattern(card));
				mainBackground.getChildren().add(rec);
				figureRectangles.put((Enemy) f, rec);
			}
		}
    }
    
    public void loadWorld2() throws IOException {
    	ground = sound.loadSounds(23);
    	ground.stop();
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
    	ground = sound.loadSounds(24);
    	ground.stop();
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

	

	public void animateFlower(Rectangle powerUpRectangle, int counter) {
		if(counter == -1) {
			mainBackground.getChildren().remove(powerUpRectangle);
		}else {
			ImagesLoader sl = null;
			try {
				sl = new ImagesLoader(32, 32, 1, 4, Flower.IMAGE);
			} catch (IOException e) {
				e.printStackTrace();
			}
			BufferedImage[] blocks = sl.getSprites();
			Image card = SwingFXUtils.toFXImage(blocks[counter], null);
			powerUpRectangle.setFill(new ImagePattern(card));
		}
	}
	
	public void moveMushroom(PowerUp powerUp, Rectangle powerUpRectangle, int counter) {
		
		if(counter == 0) {
			powerUp.setPosX(powerUp.getPosX()+8);
			powerUpRectangle.setX(powerUp.getPosX());
			Gravity g = new Gravity(this, powerUpRectangle, powerUp);
			g.start();
		}else if(counter == 1) {
			powerUp.setPosX(powerUp.getPosX()-8);
			powerUpRectangle.setX(powerUp.getPosX());
			Gravity g = new Gravity(this, powerUpRectangle, powerUp);
			g.start();
		}else {
			mainBackground.getChildren().remove(powerUpRectangle);
		}
		
	}

}
