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
import customExceptions.IllegalInputException;
import customExceptions.IntegerValuesException;
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
import model.OneUp;
import model.PowerUp;
import model.SimpleBlock;
import model.Slide;
import model.SoundsLoader;
import model.Star;
import model.StaticFigure;
import thread.CoinAnimation;
import thread.EnemyDeathAnimation;
import thread.EnemyThread;
import thread.Gravity;
import thread.JumpingThread;
import thread.LevelOneEndThread;
import thread.LevelTimeThread;
import thread.MisteryBlockAnimation;
import thread.MovementAndGravityThread;
import thread.PlatformThread;
import thread.SimpleBlockThread;
import thread.MisteryBlockHitThread;
import thread.SpinningFireThread;

public class GameController {

	/**
	 * The main background of the game, represented by a pane. 
	 */
	@FXML
    private Pane mainBackground;
	
	/**
	 * The main mario rectangle used to display Mario in the graphical interface.
	 */
	private Rectangle mainMario;
	
	/**
	 * The max displacement that Mario can have to the right direction.
	 */
	private int maxRight;
	
	/**
	 * The minimum displacement that Mario can have to the left direction.
	 */
	private int minLeft;
	
	/**
	 * The association with the model game class.
	 */
	private Game mainGame;
	
	/**
	 * <b>Description:</b>
	 * This function obtains the game that is being played.
	 * @return The game of the graphical interface.
	 */
	public Game getGame() {
		return mainGame;
	}
	
	/**
	 * The main scene that is being shown on screen.
	 */
	private Scene mainScene;
	
	/**
	 * The list of rectangles that represent the mystery blocks in the level.
	 */
	private List<Rectangle> rectan;
	
	/**
	 * The list of rectangles that represent the coins in the level.
	 */
	private List<Rectangle> rectanCoin;
	
	/**
	 * The list of threads currently running in the level.
	 */
	private List<Thread> threads;
	
	/**
	 * The images loader of the mystery block image.
	 */
	private ImagesLoader imloMark;
	
	/**
	 * The images loader of the coin block image.
	 */
	private ImagesLoader imloCoin;
	
	/**
	 * The set of keys that have been pressed.
	 */
	private Set<String> pressed;
	
	/**
	 * The map of the figures with their respective rectangles.
	 */
	private Map<Figure, Rectangle> figureRectangles;
	
	/**
	 * The current pause state of the game.
	 */
	private boolean pause;
	
	/**
	 * <b>Description:</b>
	 * This function obtains the pause state of the game.
	 * @return The pause state of the game.
	 */
	public boolean getPause() {
		return pause;
	}
	
	/**
	 * 
	 */
	private Clip ground;
	public Clip getClip() {
		return ground;
	}
	
	/**
	 * The pictures of the default Mario sprite sheet.
	 */
	private BufferedImage[] marioPictures;
	
	/**
	 * The pictures of the big Mario sprite sheet.
	 */
	private BufferedImage[] bigMarioPictures;
	
	/**
	 * The pictures of the fire Mario sprite sheet.
	 */
	private BufferedImage[] fireMarioPictures;
	
	/**
	 * The pictures of the small star Mario sprite sheet.
	 */
	private BufferedImage[] smallStarMarioPictures;
	
	/**
	 * The pictures of the big star Mario sprite sheet.
	 */
	private BufferedImage[] bigStarMarioPictures;

	/**
	 * The label that displays the time display message.
	 */
	@FXML
	private Label timeLabel;

    /**
     * The label that displays the information of the current world.
     */
    @FXML
    private Label worldLabel;

    /**
     * The label that displays the Mario name.
     */
    @FXML
    private Label marioLabel;

    /**
     * The label that displays the number of world Mario is in.
     */
    @FXML
    private Label numberOfWorld;

    /**
     * The label that displays the current score of Mario.
     */
    @FXML
    private Label scoreOfMario;
    
    /**
     * <b>Description:</b>
     * This function obtains Mario's current score.
     * @return The label of Mario's current score.
     */
    public Label getScoreOfMario() {
		return scoreOfMario;
	}

    /**
     * The label that shows how many coin Mario has.
     */
    @FXML
    private Label acumulatedCoins;
    
    /**
     * The image view that displays the image of the coin in the graphical interface.
     */
    @FXML
    private ImageView coinImage;

    /**
     * The label that displays the current time in the level.
     */
    @FXML
    private Label timeOfLevel;
	
	/**
	 * The jumping thread of the User interface.
	 */
	private JumpingThread jumping;
	
	/**
	 * The sound loader of the User interface.
	 */
	private SoundsLoader sound;
	
	/**
	 * The movement and gravity thread of the main game.
	 */
	private Thread mv;
	
	/**
	 * The attribute that holds the current level being played.
	 */
	private int currentLevel;
	
    /**
     * <b>Description:</b>
     * The initialize function of the fxml file, called as soon as the graphical interface is loaded.
     * @throws IllegalInputException Thrown when the input is illegal.
     * @throws IntegerValuesException Thrown when the values entered are not integers.
     */
    @FXML
    public void initialize() throws IllegalInputException, IntegerValuesException {
    	try {
    		mainGame = new Game();
    		loadUI();
			loadWorld1();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	currentLevel = 1;
    }
    
    /**
     * <b>Description:</b>
     * This function loads the User interface with all it's elements.
     */
    public void loadUI() {
    	sound = new SoundsLoader();
    	pause=false;
    	jumping = new JumpingThread(this);
    	pressed = new HashSet<String>();
    	try {
    	
			
			imloMark= new ImagesLoader(32, 32, 1, 3,"src/uiImg/QuestionMark.png");
			imloCoin =new ImagesLoader(32, 32, 1, 3,"src/uiImg/Coin.png");
			rectan= new ArrayList<Rectangle>();
			figureRectangles = new HashMap<Figure, Rectangle>(); 
			rectanCoin= new ArrayList<Rectangle>();
			threads = new ArrayList<Thread>();
			misteryBlockThread();
			timeThread();
			coinThread();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	maxRight = 685/3;
    	minLeft = 0;
    	try {
    		Mario m = null;
    		if(currentLevel == 1) {
        		m = mainGame.getLevelOne().getMario();
        	}else if(currentLevel == 2) {
        		m = mainGame.getLevelTwo().getMario();
        	}else {
        		m = mainGame.getLevelThree().getMario();
        	}
			ImagesLoader sl = new ImagesLoader(32, 32, 7, 4, m.getImage());
			marioPictures = sl.getSprites();
			sl = new ImagesLoader(32, 64, 7, 4, Mario.BIGMARIO);
			bigMarioPictures = sl.getSprites();
			sl = new ImagesLoader(32, 64, 7, 4, Mario.FIREMARIO);
			fireMarioPictures = sl.getSprites();
			sl = new ImagesLoader(32, 32, 7, 4, Mario.SMALLSTARMARIO);
			smallStarMarioPictures = sl.getSprites();
			sl = new ImagesLoader(32, 64, 7, 4, Mario.BIGSTARMARIO);
			bigStarMarioPictures = sl.getSprites();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	mv = new MovementAndGravityThread(this);
    	threads.add(mv);
    	mv.start();
    }
    
    /**
     * <b>Description:</b>
     * This function is utilized to end the level one once Mario reaches the end.
     * @param stage The stage of the animation in order to know which animation of the ending cinematic to play.
     * @param condition The condition to know which action to take in each stage.
     */
    public void endLevelOne(int stage, int condition) {
    	Mario m = null;
    	if(currentLevel == 1) {
    		m = mainGame.getLevelOne().getMario();
    	}else if(currentLevel == 2) {
    		m = mainGame.getLevelTwo().getMario();
    	}else {
    		m = mainGame.getLevelThree().getMario();
    	}
    	if(stage == 0) {
    		if(condition == 0) {
	    		if(m.getPowerState() == null) {
	    			Image card = SwingFXUtils.toFXImage(marioPictures[12], null);
	    			mainMario.setFill(new ImagePattern(card));
	    		}else if(m.getPowerState() instanceof Mushroom) {
	    			Image card = SwingFXUtils.toFXImage(bigMarioPictures[12], null);
	    			mainMario.setFill(new ImagePattern(card));
	    		}else {
	    			Image card = SwingFXUtils.toFXImage(fireMarioPictures[12], null);
	    			mainMario.setFill(new ImagePattern(card));
	    		}
    		}else {
    			if(m.getPowerState() == null) {
	    			Image card = SwingFXUtils.toFXImage(marioPictures[13], null);
	    			mainMario.setFill(new ImagePattern(card));
	    			m.setPosY(m.getPosY()+1);
	    			mainMario.setY(mainGame.getLevelOne().getMario().getPosY());
	    		}else if(m.getPowerState() instanceof Mushroom) {
	    			Image card = SwingFXUtils.toFXImage(bigMarioPictures[13], null);
	    			mainMario.setFill(new ImagePattern(card));
	    			m.setPosY(m.getPosY()+1);
	    			mainMario.setY(mainGame.getLevelOne().getMario().getPosY());
	    		}else {
	    			Image card = SwingFXUtils.toFXImage(fireMarioPictures[13], null);
	    			mainMario.setFill(new ImagePattern(card));
	    			m.setPosY(m.getPosY()+1);
	    			mainMario.setY(mainGame.getLevelOne().getMario().getPosY());
	    		}
    		}
    	}else if(stage == 1) {
    		if(condition == 1) {
    			if(m.getPowerState() == null) {
	    			Image card = SwingFXUtils.toFXImage(marioPictures[14], null);
	    			mainMario.setFill(new ImagePattern(card));
	    			if(m.getPosY() < 384)
	    				m.setPosY(m.getPosY()+1);
	    			if(m.getPosX() < 6528)
	    				m.setPosX(m.getPosX()+1);
	    				mainMario.setY(m.getPosY());
	    				mainMario.setX(m.getPosX());
	    		}else if(m.getPowerState() instanceof Mushroom) {
	    			Image card = SwingFXUtils.toFXImage(bigMarioPictures[14], null);
	    			mainMario.setFill(new ImagePattern(card));
	    			if(m.getPosY() < 384)
	    				m.setPosY(m.getPosY()+1);
	    			if(m.getPosX() < 6528)
	    				m.setPosX(m.getPosX()+1);
	    				mainMario.setY(m.getPosY());
	    				mainMario.setX(m.getPosX());
	    		}else {
	    			Image card = SwingFXUtils.toFXImage(fireMarioPictures[14], null);
	    			mainMario.setFill(new ImagePattern(card));
	    			if(m.getPosY() < 384)
	    				m.setPosY(m.getPosY()+1);
	    			if(m.getPosX() < 6528)
	    				m.setPosX(m.getPosX()+1);
	    			mainMario.setY(m.getPosY());
	    			mainMario.setX(m.getPosX());
	    		}
    		}else {
    			if(m.getPowerState() == null) {
	    			Image card = SwingFXUtils.toFXImage(marioPictures[14], null);
	    			mainMario.setFill(new ImagePattern(card));
	    			m.setPosX(m.getPosX()+1);
	    			mainMario.setX(m.getPosX());
	    		}else if(m.getPowerState() instanceof Mushroom) {
	    			Image card = SwingFXUtils.toFXImage(bigMarioPictures[14], null);
	    			mainMario.setFill(new ImagePattern(card));
	    			m.setPosX(m.getPosX()+1);
	    			mainMario.setX(m.getPosX());
	    		}else {
	    			Image card = SwingFXUtils.toFXImage(fireMarioPictures[14], null);
	    			mainMario.setFill(new ImagePattern(card));
	    			m.setPosX(m.getPosX()+1);
	    			mainMario.setX(m.getPosX());
	    		}
    		}
    	}else if(stage == 2){
    		if(condition == 2) {
    			mainMario.setVisible(false);
    		}else {
    			m.setPosX(m.getPosX()+1);
    			mainMario.setX(m.getPosX());
    		}
    	}else {
    		mainBackground.getChildren().clear();
    		mainBackground.setTranslateX(0);
    		timeLabel.setTranslateX(0);
    		worldLabel.setTranslateX(0);
    		marioLabel.setTranslateX(0);
    		numberOfWorld.setTranslateX(0);
    		scoreOfMario.setTranslateX(0);
    		acumulatedCoins.setTranslateX(0);
    		coinImage.setTranslateX(0);
    		timeOfLevel.setTranslateX(0);
    		try {
				loadWorld2();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		loadUI();
    		currentLevel = 2;
    	}
    }
    
    /**
     * <b>Description:</b>
     * This function deactivates all the threads that are currently running in the game.
     */
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
				}else if(t instanceof PlatformThread){
					((PlatformThread) t).deactivate();
				}else {
					((MisteryBlockHitThread) t).deactivate();
				}
			}
		}
	}
    
    /**
     * <b>Description:</b>
     * This function pauses the game once that pause key(Escape) has been pressed.
     */
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
				}else if(t instanceof PlatformThread){
					((PlatformThread) t).suspend();
				}else {
					((MisteryBlockHitThread) t).resume();
				}
			}
		}
	}
    
    /**
     * <b>Description:</b>
     * This function resumes the game if the game is paused and the resume key(Q) has been pressed.
     */
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
				}else if(t instanceof PlatformThread){
					((PlatformThread) t).resume();
				}else {
					((MisteryBlockHitThread) t).resume();
				}
			}
		}
	}
    
    /**
     * <b>Description:</b>
     * This function is responsible for the configuration of the key handling in the user interface.
     */
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
					if(e.getCode().equals(KeyCode.ESCAPE)) {
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
   
		
    /**
     * <b>Description:</b>
     * This function initializes a new time thread.
     */
    public void timeThread() {
    	LevelTimeThread lv = new LevelTimeThread(this);
    	threads.add(lv);
    	lv.start();
    	threads.add(lv);
    }
    
    /**
     * <b>Description:</b>
     * This function is responsible of animating Mario and restarting the level he's in once he dies.
     */
    @SuppressWarnings("deprecation")
	public void deadMario() {
    	Image changed = new Image(Mario.SMALLDEADMARIO);
		mainMario.setFill(new ImagePattern(changed));
		
		Clip bang = sound.loadSounds(9);
    	bang.start();
		pause();
		
    	jumping.suspend();
		closeWindow();
		mainBackground.getChildren().clear();
		try {
			mainGame = new Game();
			initialize();
			mainBackground.setTranslateX(0);
		} catch (IOException | IllegalInputException | IntegerValuesException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * <b>Description:</b>
     * This function is responsible of telling if Mario is touching any other figure in the game.
     * For every function he touches, there is a specific action that has to take place or a part of the game to be handled.
     * In other words, it's the function responsible of handling every interaction of Mario with other figures.
     * @return A string representing the side in which Mario collided with the other figure.
     */
    @SuppressWarnings("deprecation")
	public String isTouching() {
    	Mario m = null;
    	if(currentLevel == 1) {
    		m = mainGame.getLevelOne().getMario();
    	}else if(currentLevel == 2) {
    		m = mainGame.getLevelTwo().getMario();
    	}else {
    		m = mainGame.getLevelThree().getMario();
    	}
    	String intersects = "";
    	List<Figure> sprites = null;
		if(currentLevel == 1) {
    		sprites = mainGame.getLevelOne().getFigures();
    	}else if(currentLevel == 2) {
    		sprites = mainGame.getLevelTwo().getFigures();
    	}else {
    		sprites = mainGame.getLevelThree().getFigures();
    	}

    	for (int i = 0; i < sprites.size() && intersects.isEmpty(); i++) {
			if(sprites.get(i) instanceof Mario)
				continue;
			else if(sprites.get(i) instanceof Enemy) {
				Enemy ene = (Enemy) sprites.get(i);
				if(ene.isDead()) {
					continue;
				}
			}
			Figure f = sprites.get(i);
			Figure mario = m;
			
			intersects = ((Mario) mario).isColliding(f.getPosX(), f.getPosY(), f.getWidth(), f.getHeight());
			
			if(!intersects.equals(Mario.ISMOVINGDOWN) && !intersects.isEmpty() && f instanceof Enemy){
				
				deadMario();
				break;
			}
			
			if(intersects.equals(Mario.ISMOVINGDOWN) && f instanceof Enemy) {
				jumping.stop();
				if(f instanceof Goomba) { // !sprites.get(i).getImage().equals(Koopa.KOOPASHELL)
					
					EnemyDeathAnimation thread = new EnemyDeathAnimation(this, figureRectangles.get(sprites.get(i)), (Enemy) sprites.get(i));
					thread.start();
					
				}
			}else if(intersects.equals(Mario.ISMOVINGRIGHT) && (f.getImage().equals(StaticFigure.FLAGPOLE) || f.getImage().equals(StaticFigure.FLAGSPHERE) || f.getImage().equals(StaticFigure.FLAGTOP))) {
				pause = true;
				LevelOneEndThread thread = new LevelOneEndThread(this);
				thread.start();
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
					Clip bang = sound.loadSounds(13);
					bang.start();
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
					Clip bang = sound.loadSounds(3);
					bang.start();
					MisteryBlockHitThread pw = new MisteryBlockHitThread(this, r, null);
					pw.start();

				}else if(mb.getPower() != null && !mb.getImage().equals(StaticFigure.IRON)) {
					mb.setImage(StaticFigure.IRON);
					PowerUp pu = mb.getPower();
					sprites.add(pu);
					Clip bang = sound.loadSounds(13);
					bang.start();
					if(pu instanceof OneUp) {
						Rectangle r = new Rectangle(mb.getPosX(), mb.getPosY(), 32, 32);
						pu.setPosX(mb.getPosX()); pu.setPosY(mb.getPosY());
						try {
							sl = new ImagesLoader(32, 32, 1, 2, OneUp.ONEUP);
						} catch (IOException e) {
							e.printStackTrace();
						}
						BufferedImage[] blocks = sl.getSprites();
						Image cardd = SwingFXUtils.toFXImage(blocks[0], null);
						r.setFill(new ImagePattern(cardd));
						MisteryBlockHitThread pw = new MisteryBlockHitThread(this, r, pu);
						pw.start();
						threads.add(pw);
					}else if(pu instanceof Star) {
						Rectangle r = new Rectangle(mb.getPosX(), mb.getPosY(), 32, 32);
						pu.setPosX(mb.getPosX()); pu.setPosY(mb.getPosY());
						try {
							sl = new ImagesLoader(32, 32, 1, 2, Star.STAR);
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

				}
			} else if(intersects.equals(Mario.ISMOVINGUP) && (f instanceof SimpleBlock)) {
				SimpleBlock sb = (SimpleBlock) f;
				Rectangle r = new Rectangle(sb.getPosX(), sb.getPosY()-32, 32, 32);
				if(m.getPowerState() == null) {
					SimpleBlockThread sbt = new SimpleBlockThread(this, sb, r);
					sbt.start();
				} else if (m.getPowerState() != null) {
					mainBackground.getChildren().remove(r);
					rectan.remove(figureRectangles.get(sb));
			}
		} 
	} 
    	return intersects;
    }
    
    /**
     * <b>Description:</b>
     * This function is responsible of animating the mystery block when it has a coin.
     * @param r The rectangle of the coin animation.
     * @param iteration The iteration to know which animation to play.
     * @param move The movement that has to take place, if any at all.
     */
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
			int counter = Integer.parseInt(scoreOfMario.getText())+200;
			scoreOfMario.setText(counter + "");
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
    
    /**
     * <b>Description:</b>
     * This function does the animation of the power up when it comes out.
     * @param r The rectangle that represents the power up.
     * @param p The power up of this rectangle figure.
     * @param iteration The iteration to know which animation to play.
     */
    public void exitPowerUp(Rectangle r, PowerUp p, int iteration) {
    	if(iteration == 0) {
    		mainBackground.getChildren().add(r);
    		r.toBack();
    	}
    	p.setPosY(p.getPosY()-8);
    	r.setY(p.getPosY());
    }
    
    /**
     * <b>Description:</b>
     * This function changes the image of the enemy depending on which direction it's moving.
     * @param a It represents the animation that has to play.
     * @param e It's the enemy that has to have it's image changed.
     * @param gRec The rectangle that represents the enemy in the graphical interface.
     */
    @SuppressWarnings("deprecation")
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
					((EnemyThread) threads.get(i)).stop();
					exit = true;
				}
			}
			List<Figure> sprites = null;
			if(currentLevel == 1) {
	    		sprites = mainGame.getLevelOne().getFigures();
	    	}else if(currentLevel == 2) {
	    		sprites = mainGame.getLevelTwo().getFigures();
	    	}else {
	    		sprites = mainGame.getLevelThree().getFigures();
	    	}
			int pos = sprites.indexOf(e);
			Enemy goom = (Enemy) sprites.get(pos);
			goom.setDead(true);
			exit = false;
			
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
       
    /**
     * <b>Description:</b>
     * This function determines whether or not Mario is between any block.
     * @param yFinal The final y position in the jump.
     * @param yActual The actual y position in the jump.
     * @return The figure that Mario is colliding with or null if there isn't any.
     */
    public Figure isBetween(double yFinal, double yActual) {
    	boolean intersects = false;
    	Figure f = null;
    	Mario m = null;
    	if(currentLevel == 1) {
    		m = mainGame.getLevelOne().getMario();
    	}else if(currentLevel == 2) {
    		m = mainGame.getLevelTwo().getMario();
    	}else {
    		m = mainGame.getLevelThree().getMario();
    	}
    	List<Figure> sprites = null;
		if(currentLevel == 1) {
    		sprites = mainGame.getLevelOne().getFigures();
    	}else if(currentLevel == 2) {
    		sprites = mainGame.getLevelTwo().getFigures();
    	}else {
    		sprites = mainGame.getLevelThree().getFigures();
    	}
    	for (int i = 0; i < sprites.size() && !intersects; i++) {
    		
			if(sprites.get(i) instanceof Mario )
				continue;
			f = sprites.get(i);
			
			Figure mario = m;
			intersects = ((Mario) mario).isThereSomethingInBetween(f.getPosX(), f.getPosY(), f.getWidth(), f.getHeight(), yFinal, yActual);
		}
    	
    	if(!intersects) {
    		f = null;
    	}
    	
    	return f;
    }
       
    /**
     * <b>Description:</b>
     * This function tells if Mario is on top of any figure or if he's falling.
     * @return The state of Mario.
     */
    public String isFalling() {
    	String intersects = "";
    	Figure f = null;
    	Mario m = null;
    	if(currentLevel == 1) {
    		m = mainGame.getLevelOne().getMario();
    	}else if(currentLevel == 2) {
    		m = mainGame.getLevelTwo().getMario();
    	}else {
    		m = mainGame.getLevelThree().getMario();
    	}

    	List<Figure> sprites = null;
		if(currentLevel == 1) {
    		sprites = mainGame.getLevelOne().getFigures();
    	}else if(currentLevel == 2) {
    		sprites = mainGame.getLevelTwo().getFigures();
    	}else {
    		sprites = mainGame.getLevelThree().getFigures();
    	}

    	for (int i = 0; i < mainGame.getLevelOne().getFigures().size() && intersects.isEmpty(); i++) {
			if(sprites.get(i) instanceof Mario)
				continue;
			else if(sprites.get(i) instanceof Enemy) {
				Enemy ene = (Enemy) sprites.get(i);
				if(ene.isDead()) {
					continue;
				}
			}
			f = sprites.get(i);
			
			Figure mario = m;
			intersects = ((Mario) mario).isGrounded(f.getPosX(), f.getPosY(), f.getWidth(), f.getHeight());
		}
    	
    	return intersects;
    }
    
    /**
     * <b>Description:</b>
     * This function tests if a figure is on something or it's falling.
     * @param figure The figure that is going to be tested.
     * @param figureRec The rectangle that represents the figure in the graphical interface.
     * @return False if the figure is falling and true if it isn't.
     */
    public boolean isFigureFalling(Figure figure, Rectangle figureRec) {
    	
    	boolean intersects = false;
    	Figure f = null;

    	List<Figure> sprites = null;
		if(currentLevel == 1) {
    		sprites = mainGame.getLevelOne().getFigures();
    	}else if(currentLevel == 2) {
    		sprites = mainGame.getLevelTwo().getFigures();
    	}else {
    		sprites = mainGame.getLevelThree().getFigures();
    	}
    	for (int i = 0; i < sprites.size() && !intersects; i++) {
    		
			if(sprites.get(i) != figure) {
				f = sprites.get(i);
				if(figure instanceof Enemy)
					intersects = ((Enemy) figure).enemyIsGrounded(f.getPosX(), f.getPosY(), f.getWidth(), f.getHeight());
				else if(figure instanceof Mushroom) {
					intersects = ((Mushroom) figure).mushroomIsGrounded(f.getPosX(), f.getPosY(), f.getWidth(), f.getHeight());
				}else if(figure instanceof OneUp) {
					intersects = ((OneUp) figure).oneUpIsGrounded(f.getPosX(), f.getPosY(), f.getWidth(), f.getHeight());
				}else if(figure instanceof Star) {
					intersects = ((Star) figure).starIsGrounded(f.getPosX(), f.getPosY(), f.getWidth(), f.getHeight());
				}
			}
		}
    	return intersects;
    }
    
    /**
     * <b>Description:</b>
     * This function initializes a new jumping thread.
     */
    public void runThread(){
    	jumping = new JumpingThread(this);
    	jumping.start();
    	
    }

	/**
	 * <b>Description:</b>
	 * This function initializes a new mystery block thread.
	 */
	public void misteryBlockThread() {
    	Thread mba = new MisteryBlockAnimation(this);
    	threads.add(mba);
		mba.start();
    }
	
    /**
     * <b>Description:</b>
     * This function initializes a new coin animation thread.
     */
    public void coinThread() {
    	Thread ca = new CoinAnimation(this);
    	threads.add(ca);
    	ca.start();
    }
    
    /**
     * <b>Description:</b>
     * This function changes the image of the mystery blocks.
     */
    public void setFill0() {
    	BufferedImage[] blocks = imloMark.getSprites();
    	
		Image card = SwingFXUtils.toFXImage(blocks[0], null);
		
		for (int i = 0; i < rectan.size(); i++) {
			rectan.get(i).setFill(new ImagePattern(card));
			
		}	
    }
    
    /**
     * <b>Description:</b>
     * This function changes the image of the coin.
     */
    public void setFillCoin0() {
    	
    	BufferedImage[] blocks1 = imloCoin.getSprites();
		
		Image card1 = SwingFXUtils.toFXImage(blocks1[0], null);
		for (int i = 0; i < rectanCoin.size(); i++) {
			
			rectanCoin.get(i).setFill(new ImagePattern(card1));
		}	
    }
    
    /**
     * <b>Description:</b>
     * This function changes the image of the mystery blocks.
     */
    public void setFill1() {
    	BufferedImage[] blocks = imloMark.getSprites();
    	
		Image card = SwingFXUtils.toFXImage(blocks[1], null);
		
		for (int i = 0; i < rectan.size(); i++) {
			rectan.get(i).setFill(new ImagePattern(card));
		}	
    }
    
    /**
     * <b>Description:</b>
     * This function changes the image of the coin.
     */
    public void setFillCoin1() {
    	
    	BufferedImage[] blocks1 = imloCoin.getSprites();
		
		Image card1 = SwingFXUtils.toFXImage(blocks1[1], null);
		for (int i = 0; i < rectanCoin.size(); i++) {
			
			rectanCoin.get(i).setFill(new ImagePattern(card1));
		}	
    }
    
    /**
     * <b>Description:</b>
     * This function changes the image of the mystery blocks.
     */
    public void setFill2() {
    	BufferedImage[] blocks = imloMark.getSprites();
    	
		Image card = SwingFXUtils.toFXImage(blocks[2], null);
		
		for (int i = 0; i < rectan.size(); i++) {
			rectan.get(i).setFill(new ImagePattern(card));
		
		}
    }
    
    /**
     * <b>Description:</b>
     * This function changes the image of the coin.
     */
    public void setFillCoin2() {

    	BufferedImage[] blocks1 = imloCoin.getSprites();
		Image card1 = SwingFXUtils.toFXImage(blocks1[2], null);
		for (int i = 0; i < rectanCoin.size(); i++) {
		
			rectanCoin.get(i).setFill(new ImagePattern(card1));
		}
    }
    
    /**
     * <b>Description:</b>
     * This function modifies the time of level label.
     * @param time The time that is to going to be set.
     */
    public void setTime(int time) {
    	timeOfLevel.setText(time+"");
    }
    
    /**
     * <b>Description:</b>
     * This function tests if Mario is near enough to an enemy for it to start moving.
     */
    public void distanceToEnemies() {
    	Mario m = null;
    	if(currentLevel == 1) {
    		m = mainGame.getLevelOne().getMario();
    	}else if(currentLevel == 2) {
    		m = mainGame.getLevelTwo().getMario();
    	}else {
    		m = mainGame.getLevelThree().getMario();
    	}
    	List<Enemy> enemies = mainGame.getLevelOne().getEnemies();
		if(currentLevel == 1) {
    		enemies = mainGame.getLevelOne().getEnemies();
    	}else if(currentLevel == 2) {
    		enemies = mainGame.getLevelTwo().getEnemies();
    	}else {
    		enemies = mainGame.getLevelThree().getEnemies();
    	}

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
    
    /**
     * <b>Description:</b>
     * This function makes the fire wheels in the third level spin.
     * @param fireRec The rectangle that represents the fire in the fire wheel.
     * @param fire The figure that represents the fire.
     * @param f The number of degrees that the fire has.
     * @param radius The radius of the wheel.
     * @param centerX The x center coordinate of the fire wheel.
     * @param centerY The y center coordinate of the fire wheel.
     */
    public void spinFire(Rectangle fireRec, Figure fire, int f, int radius, int centerX, int centerY) {
    	double x = Math.sin(Math.toRadians((double)f)) * (radius);
        double y = Math.cos(Math.toRadians((double)f)) * (radius);
        fire.setPosX(radius - Math.pow(x-centerX, 2));
        fire.setPosY(radius - Math.pow(y-centerY, 2));
        fireRec.setX(fire.getPosX());
        fireRec.setY(fire.getPosY());
    }
    
 	/**
 	 * <b>Description:</b>
 	 * This function moves the platform in the second level.
 	 * @param platformRectangle The rectangle that represents each of the little rectangles in the platform.
 	 * @param platform The figure of the platform.
 	 */
 	public void movePlatform(Rectangle platformRectangle, MovingPlatform platform) {
 		platform.setPosY(platform.getPosY()+8);
			platformRectangle.setY(platform.getPosY());
 		if(platform.getPosY() > 550) {
 			platform.setPosY(0);
 			platformRectangle.setY(platform.getPosY());
 		}
 	}
 	
 	/**
 	 * <b>Description:</b>
 	 * This function makes a figure fall if it is affected by gravity.
 	 * @param figureRec The rectangle that represents the figure in the graphical interface.
 	 * @param figure The figure in the model.
 	 */
 	public void makeFigureFall(Rectangle figureRec, Figure figure) {
 		figure.setPosY(figure.getPosY()+8);
 		figureRec.setY(figure.getPosY());
 	}
 	
 	/**
 	 * <b>Description:</b>
 	 * This function moves an enemy, it's utilized by the enemy thread. 
 	 * @param enemyRec The rectangle that represents the figure in the graphical interface.
 	 * @param enemy The enemy figure in the model.
 	 * @param changer The image that it has to be changed to.
 	 * @throws IOException If the image file is not found.
 	 */
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
	List<Figure> sprites = null;
	if(currentLevel == 1) {
		sprites = mainGame.getLevelOne().getFigures();
	}else if(currentLevel == 2) {
		sprites = mainGame.getLevelTwo().getFigures();
	}else {
		sprites = mainGame.getLevelThree().getFigures();
	}

	for (int i = 0; i < threads.size(); i++) {
		if(threads.get(i) instanceof EnemyThread && ((EnemyThread) threads.get(i)).getEnemy().getPosY() > 480) {
			((EnemyThread) threads.get(i)).deactivate();
			//mainGame.getLevelOne().getFigures().remove(((EnemyThread) threads.get(i)).getEnemy());
			boolean exit = false;
			for (int j = 0; j < sprites.size() && !exit; j++) {
				if(sprites.get(j) instanceof Mario) {
					if(currentLevel == 1)
						mainGame.getLevelOne().setMarioPosition(j);
					else if(currentLevel == 2)
						mainGame.getLevelTwo().setMarioPosition(j);
					else
						mainGame.getLevelThree().setMarioPosition(j);
					exit = true;
				}
			}
		}
	}
 	}
 	
 	/**
 	 * <b>Description:</b>
 	 * This function determines if the figure in the parameter is touching any other figure.
 	 * @param figure The figure that is to be tested.
 	 * @return True if the figure is touching, false otherwise.
 	 */
 	public boolean figureIsTouching(Figure figure) {
 		boolean intersects = false;
 		List<Figure> sprites = null;
		if(currentLevel == 1) {
    		sprites = mainGame.getLevelOne().getFigures();
    	}else if(currentLevel == 2) {
    		sprites = mainGame.getLevelTwo().getFigures();
    	}else {
    		sprites = mainGame.getLevelThree().getFigures();
    	}
		Mario m = null;
		if(currentLevel == 1) {
    		m = mainGame.getLevelOne().getMario();
    	}else if(currentLevel == 2) {
    		m = mainGame.getLevelTwo().getMario();
    	}else {
    		m = mainGame.getLevelThree().getMario();
    	}

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
						//mainGame.getLevelOne().getFigures().remove(figure);
						boolean exit = false;
						for (int j = 0; j < sprites.size() && !exit; j++) {
							if(sprites.get(j) instanceof Mario) {
								if(currentLevel == 1)
									mainGame.getLevelOne().setMarioPosition(j);
								else if(currentLevel == 2)
									mainGame.getLevelTwo().setMarioPosition(j);
								else
									mainGame.getLevelThree().setMarioPosition(j);
								exit = true;
							}
						}
						Figure mario = m;
						
						if(figure instanceof Mushroom) {
							Clip clip = sound.loadSounds(12);
							clip.start();
							m.setPowerState((PowerUp) figure);
							mario.setImage(Mario.BIGMARIO);
							Image cardd = SwingFXUtils.toFXImage(bigMarioPictures[0], null);
							mainMario.setFill(new ImagePattern(cardd));
							mainMario.setHeight(64);
							m.setHeight(64);
							m.setPosY(m.getPosY()-32);
							mainMario.setY(m.getPosY());
						}else if(figure instanceof OneUp) {
							Clip clip = sound.loadSounds(1);
							clip.start();
							
						}else if(figure instanceof Flower) {
							Clip clip = sound.loadSounds(12);
							clip.start();
							m.setPowerState((PowerUp) figure);
							mario.setImage(Mario.FIREMARIO);
							Image cardd = SwingFXUtils.toFXImage(fireMarioPictures[0], null);
							mainMario.setFill(new ImagePattern(cardd));
							mainMario.setHeight(64);
							m.setHeight(64);
							m.setPosY(m.getPosY()-32);
							mainMario.setY(m.getPosY());
						}else if(figure instanceof Star) {
							Clip clip = sound.loadSounds(18);
							clip.start();
							if(m.getPowerState() == null) {
								
							}else {
								
							}
							
						}
					}
				}
			}
		}
 		return intersects;
 	}
 
    /**
     * <b>Description:</b>
     * This function moves the Mario image depending on the parameters.
     * @param a The direction in which Mario has to move.
     * @param jump The jump that Mario has to make if he's jumping.
     */
    public void moveImage(int a, double jump){
		Mario m = null;
		if(currentLevel == 1) {
    		m = mainGame.getLevelOne().getMario();
    	}else if(currentLevel == 2) {
    		m = mainGame.getLevelTwo().getMario();
    	}else {
    		m = mainGame.getLevelThree().getMario();
    	}
		
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
	    	else if(mainMario.getX() <= minLeft && a==-1) {
	    	}
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
	        }else if(a==3 ){
	        	mainMario.setY(mainMario.getY()-8);
	        	m.setPosY(mainMario.getY());
	        }else if(a==4 && !touch.equals(Mario.ISMOVINGDOWN)) {
	        	mainMario.setY(mainMario.getY()+8);
	        	m.setPosY(mainMario.getY());
	        }
	        else if(a==5  && (!touch.equals(Mario.ISMOVINGRIGHT))) {
	    		mainMario.setX(mainMario.getX()+20);
	    		m.setPosX(mainMario.getX());
	    		//m.setState(Mario.ISMOVINGRIGHT);
	    	}
			distanceToEnemies();
    	}
    
    /**
     * <b>Description:</b>
     * This function obtains the movement and gravity thread of Mario.
	 * @return the movement and gravity thread.
	 */
	public Thread getMv() {
		return mv;
	}
	
	/**
	 * <b>Description:</b>
	 * This function changes the Mario image depending on which way he's moving.
	 * @param key The key that determines the Mario image.
	 */
	public void changeMarioImage(int key) {
		Mario m = null;
		if(currentLevel == 1) {
    		m = mainGame.getLevelOne().getMario();
    	}else if(currentLevel == 2) {
    		m = mainGame.getLevelTwo().getMario();
    	}else {
    		m = mainGame.getLevelThree().getMario();
    	}
    	Image changed = null;
    	if(key ==0 ) {    // normal position
    		if(m.getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[0], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[0], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[0], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}
    	else if(key==1) {  // right movement
    		if(m.getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[4], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[4], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[4], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}
    	else if(key==2) {   // right movement2
    		if(m.getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[5], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[5], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[5], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}else if(key==3) {   // Right movement3
    		if(m.getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[6], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[6], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[6], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}else if(key ==4) {   // Right movement3
    		if(m.getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[2], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[2], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[2], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}else if(key ==5) {   // left movement1
    		if(m.getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[8], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[8], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[8], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}
    	else if(key ==6) {   // left movement2
    		if(m.getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[9], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[9], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[9], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}
    	else if(key ==7) {   // left movement3
    		if(m.getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[10], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[10], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[10], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}else if(key == 8) {   // change to right
    		if(m.getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[7], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[7], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[7], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}else if(key == 9) {  // change to left
    		if(m.getPowerState() == null) {
	    		changed = SwingFXUtils.toFXImage(marioPictures[11], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Mushroom) {
    			changed = SwingFXUtils.toFXImage(bigMarioPictures[11], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}else if(m.getPowerState() instanceof Flower) {
    			changed = SwingFXUtils.toFXImage(fireMarioPictures[11], null);
	    		mainMario.setFill(new ImagePattern(changed));
    		}
    	}
    	
    }
    
    /**
     * <b>Description:</b>
     * This function loads the world 1 objects into the graphical interface.
     * @throws IOException Throws if the image file is not found.
     */
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
    
    /**
     * <b>Description:</b>
     * This function loads the world 2 objects into the graphical interface.
     * @throws IOException Throws if the image file is not found.
     */
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
				figureRectangles.put((MisteryBlock) f, rec);
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
				figureRectangles.put((Enemy) f, rec);
			}else if(f instanceof Koopa){
				sl = new ImagesLoader(32, 32, 9, 15, f.getImage());
				BufferedImage[] koopas = sl.getSprites();
				Image card = SwingFXUtils.toFXImage(koopas[5], null);
				rec.setFill(new ImagePattern(card));
				mainBackground.getChildren().add(rec);
				figureRectangles.put((Enemy) f, rec);
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

    /**
     * <b>Description:</b>
     * This function loads the world 3 objects into the graphical interface.
     * @throws IOException Throws if the image file is not found.
     */
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
				figureRectangles.put((MisteryBlock) f, rec);
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
    
	/**
	 * <b>Description:</b>
	 * This function obtains the user interface's main scene.
	 * @return The main scene shown on screen.
	 */
	public Scene getMainScene() {
		return mainScene;
	}

	/**
	 * <b>Description:</b>
	 * This function modifies the user interface's main scene.
	 * @param mainScene The new main scene of the game.
	 */
	public void setMainScene(Scene mainScene) {
		this.mainScene = mainScene;
	}

	/**
	 * <b>Description:</b>
	 * This function obtains the user interface's Mario rectangle.
	 * @return the mainMario rectangle.
	 */
	public Rectangle getMainMario() {
		return mainMario;
	}

	/**
	 * <b>Description:</b>
	 * This function obtains the user interface's main game.
	 * @return the mainGame of the graphical interface.
	 */
	public Game getMainGame() {
		return mainGame;
	}

	/**
	 * <b>Description:</b>
	 * This function obtains the set of the pressed keys. 
	 * @return the pressed keys in the game.
	 */
	public Set<String> getPressed() {
		return pressed;
	}
	
	/**
	 * <b>Description:</b>
	 * This function obtains this controller.
	 * @return this controller.
	 */
	public GameController getController() {
		return this;
	}
	
	 /**
	  * <b>Description:</b>
	  * This function obtains the current jumping thread.
		 * @return the jumping thread of the user interface.
		 */
		public JumpingThread getJumping() {
			return jumping;
		}

	

	/**
	 * <b>Description:</b>
	 * This function animates the flower sprite.
	 * @param powerUpRectangle The rectangle of the flower power up.
	 * @param counter The counter that represents the image that is to be shown.
	 */
	public void animateFlower(Rectangle powerUpRectangle, int counter) {
		if(counter == -1) {
			mainBackground.getChildren().remove(powerUpRectangle);
			int w = Integer.parseInt(scoreOfMario.getText())+1000;
			scoreOfMario.setText(w + "");
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
	
	/**
	 * <b>Description:</b>
	 * This function is responsible of animating the star.
	 * @param powerUp The star power up to change the image.
	 * @param powerUpRectangle The rectangle that represents the star power up.
	 * @param counter The counter that represents the image that has to be changed.
	 * @param moment The moment of the animation that is taking place.
	 * @param orientation The orientation of the star power, that is, the direction in which it's moving.
	 */
	public void animateStar(PowerUp powerUp, Rectangle powerUpRectangle, int counter, int moment, int orientation) {
		if(counter == -1) {
			mainBackground.getChildren().remove(powerUpRectangle);
		}else {
			ImagesLoader sl = null;
			try {
				sl = new ImagesLoader(32, 32, 1, 4, Star.STAR);
			} catch (IOException e) {
				e.printStackTrace();
			}
			BufferedImage[] blocks = sl.getSprites();
			Image card = SwingFXUtils.toFXImage(blocks[counter], null);
			powerUpRectangle.setFill(new ImagePattern(card));
			if(moment == 0) {
				powerUpRectangle.setX(powerUpRectangle.getX()+12);
				powerUp.setPosX(powerUp.getPosX()+12);
			}else if(moment == 1){
				Gravity g = new Gravity(this, powerUpRectangle, powerUp);
				g.start();
			}else if(moment >=2){
				if(orientation == 0) {
					if(moment % 2 == 0) {
						powerUpRectangle.setX(powerUpRectangle.getX()+8);
						powerUp.setPosX(powerUp.getPosX()+8);
						powerUpRectangle.setY(powerUpRectangle.getY()-8);
						powerUp.setPosY(powerUp.getPosY()-8);
					}else {
						powerUpRectangle.setX(powerUpRectangle.getX()+8);
						powerUp.setPosX(powerUp.getPosX()+8);
						powerUpRectangle.setY(powerUpRectangle.getY()+8);
						powerUp.setPosY(powerUp.getPosY()+8);
					}
				}else {
					if(moment % 2 == 0) {
						powerUpRectangle.setX(powerUpRectangle.getX()-8);
						powerUp.setPosX(powerUp.getPosX()-8);
						powerUpRectangle.setY(powerUpRectangle.getY()-8);
						powerUp.setPosY(powerUp.getPosY()-8);
					}else {
						powerUpRectangle.setX(powerUpRectangle.getX()-8);
						powerUp.setPosX(powerUp.getPosX()-8);
						powerUpRectangle.setY(powerUpRectangle.getY()+8);
						powerUp.setPosY(powerUp.getPosY()+8);
					}
				}
				
			}
		}
	}
	
	/**
	 * <b>Description:</b>
	 * This function is responsible of moving the mushroom power up. 
	 * @param powerUp The power up that is to be moved.
	 * @param powerUpRectangle The rectangle that represents the power up in the graphical interface.
	 * @param counter The counter that indicates which images to change.
	 */
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
			int w = Integer.parseInt(scoreOfMario.getText())+1000;
			scoreOfMario.setText(w + "");
		}
		
	}

	/**
	 * <b>Description:</b>
	 * This function obtains the current level being played.
	 * @return The current level being played.
	 */
	public int getCurrentLevel() {
		return currentLevel;
	}
}