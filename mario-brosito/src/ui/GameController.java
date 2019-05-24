package ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Figure;
import model.Game;
import model.Goomba;
import model.ImagesLoader;
import model.Mario;
import model.MisteryBlock;
import model.SimpleBlock;
import model.Slide;
import model.StaticFigure;
import thread.JumpingThread;
import thread.MisteryBlockAnimation;
import thread.MovementAndGravityThread;

public class GameController {

	

	@FXML
    private Pane mainBackground;
	
	private Rectangle mainMario;
	
	private int maxRight;
	
	private int minLeft;
	
	private Game mainGame;
	
	private Scene mainScene;
	private List<Rectangle> rectan;
	private ImagesLoader imlo;
	
	private Set<String> pressed;
	
	private BufferedImage[] marioPictures;
	
    @FXML
    public void initialize() {
    
    	pressed = new HashSet<String>();
    	try {
			mainGame = new Game();
			imlo= new ImagesLoader(32, 32, 1, 3,"src/uiImg/QuestionMark.png");
			rectan= new ArrayList<Rectangle>();
			loadWorld();
			misteryBlockThread();
			
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
    	
    	MovementAndGravityThread mv = new MovementAndGravityThread(this);
    	mv.start();
    	
    }
    
    public void configureScene() {
    	javafx.scene.paint.Color c = javafx.scene.paint.Color.rgb(93, 148, 251);
		mainScene.setFill(c);
		mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				pressed.add(e.getCode().toString());
			
					
					if(e.getCode().equals(KeyCode.D) || pressed.contains("D")) {
						moveImage(1);
					}
					if(e.getCode().equals(KeyCode.A) || pressed.contains("A")) {
						moveImage(-1);
					}if(e.getCode().equals(KeyCode.W) && !mainGame.getLevelOne().getMario().getState().equals(Mario.ISMOVINGUP) && !mainGame.getLevelOne().getMario().getState().equals(Mario.ISMOVINGDOWN)){
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
		
    public boolean isTouching() {
    	boolean intersects = false;
    	List<Figure> sprites = mainGame.getLevelOne().getFigures();
    	for (int i = 0; i < sprites.size() && !intersects; i++) {
			if(sprites.get(i) instanceof Mario)
				continue;
			Figure f = sprites.get(i);
			Figure mario = mainGame.getLevelOne().getMario();
			intersects = mario.isColliding(f.getPosX(), f.getPosY(), f.getWidth(), f.getHeight());
		}
    	return intersects;
    }
    
    public void runThread(){
    	JumpingThread mv = new JumpingThread(this);
    	mv.start();
    }
    
    
    public void misteryBlockThread() {
    	MisteryBlockAnimation mba = new MisteryBlockAnimation(this);
		mba.start();
    }
    public void setFill0() {
    	BufferedImage[] blocks = imlo.getSprites();
		Image card = SwingFXUtils.toFXImage(blocks[0], null);
		for (int i = 0; i < rectan.size(); i++) {
			rectan.get(i).setFill(new ImagePattern(card));
		}	
    }
    public void setFill1() {
    	BufferedImage[] blocks = imlo.getSprites();
		Image card = SwingFXUtils.toFXImage(blocks[1], null);
		for (int i = 0; i < rectan.size(); i++) {
			rectan.get(i).setFill(new ImagePattern(card));
		}	
    }
    public void setFill2() {
    	BufferedImage[] blocks = imlo.getSprites();
		Image card = SwingFXUtils.toFXImage(blocks[2], null);
		for (int i = 0; i < rectan.size(); i++) {
			rectan.get(i).setFill(new ImagePattern(card));
		}
    }

    public void drawImage() {
       // canvas.getGraphicsContext2D().drawImage(main, minX, minY, width, height);
    }

    public void moveImage(int a) {

		Mario m = (Mario) mainGame.getLevelOne().getMario();
		if(a==1 && !mainGame.getLevelOne().getMario().getState().equals(Mario.ISMOVINGUP) && !mainGame.getLevelOne().getMario().getState().equals(Mario.ISMOVINGDOWN)) {
    		//m.setState(Mario.ISMOVINGRIGHT);
    	}else if(a==-1){
        	//m.setState(Mario.ISMOVINGLEFT);
        }else if(a==2){
        	m.setState(Mario.ISMOVINGUP);
        }else if(a==3){
        	m.setState(Mario.ISMOVINGDOWN);
        }
    	if(!isTouching()) {
	    	if(mainMario.getX() >= maxRight && a==1) {
	    		mainMario.setX(mainMario.getX()+10);
	    		maxRight +=10;
	    		minLeft += 10;
	    		mainBackground.setTranslateX(mainBackground.getTranslateX()-10);
	    		m.setPosX(mainMario.getX());
	    	}
	    	else if(mainMario.getX() <= minLeft && a==-1) {
	    	}
	    	else if(a==1) {
	    		mainMario.setX(mainMario.getX()+10);
	    		m.setPosX(mainMario.getX());
	    	}else if(a==-1){
	        	mainMario.setX(mainMario.getX()-10);
	        	m.setPosX(mainMario.getX());
	        }else if(a==2){
	        	mainMario.setY(mainMario.getY()-10);
	        	m.setPosY(mainMario.getY());
	        }else if(a==3){
	        	mainMario.setY(mainMario.getY()+10);
	        	m.setPosY(mainMario.getY());
	        }
    	}else {
    		mainMario.setX(m.getPosX());
    		mainMario.setY(m.getPosY());
    	}
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
    	}else if(key==3) {   // rigth movement3
    		changed = SwingFXUtils.toFXImage(marioPictures[6], null);
    		mainMario.setFill(new ImagePattern(changed));
    	}else if(key ==4) {   // rigth movement3
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
    	}else if(key == 8) {// change to right
    		changed = SwingFXUtils.toFXImage(marioPictures[7], null);
    		mainMario.setFill(new ImagePattern(changed));
    	}else if(key == 9) {// change to right
    		changed = SwingFXUtils.toFXImage(marioPictures[11], null);
    		mainMario.setFill(new ImagePattern(changed));
    	}
    	
    }
    
    public void loadWorld() throws IOException {
    	List<Figure> sprites = mainGame.getLevelOne().getFigures();
    	ImagesLoader sl = null;
    	
    	for (int i = 0; i < sprites.size(); i++) {
			Figure f = sprites.get(i);
			Rectangle rec = new Rectangle(f.getWidth(), f.getHeight());
			rec.setX(f.getPosX()); rec.setY(f.getPosY());
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
				System.out.println(f.getWidth()); System.out.println(f.getHeight());
				rec.setFill(new ImagePattern(new Image(f.getImage())));
				mainBackground.getChildren().add(rec);
			}else if(f instanceof Goomba){
				sl = new ImagesLoader(32, 32, 4, 2, f.getImage());
				BufferedImage[] goombas = sl.getSprites();
				Image card = SwingFXUtils.toFXImage(goombas[0], null);
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
	
    
}
