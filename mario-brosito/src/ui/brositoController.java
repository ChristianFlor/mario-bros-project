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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Figure;
import model.Game;
import model.ImagesLoader;
import model.Mario;
import model.MisteryBlock;
import model.SimpleBlock;
import model.Slide;
import model.StaticFigure;
import thread.MarioMovement;
import thread.MisteryBlockAnimation;

public class brositoController {

	/*@FXML
    private Canvas canvas;*/

	@FXML
    private Pane mainBackground;
	
	private Rectangle mainMario;
	
	private int maxRight;
	
	private int minLeft;
	
	private Game mainGame;
	
	private Scene mainScene;
	private List<Rectangle> rectan;
	private ImagesLoader imlo;
	
	private Set<Integer> pressed;
	
    @FXML
    public void initialize() {
    
    	pressed = new HashSet<Integer>();
    	try {
			mainGame = new Game();
			imlo= new ImagesLoader(32, 32, 1, 3,"src/uiImg/QuestionMark.png");
			rectan= new ArrayList<Rectangle>();
			loadWorld();
			misteryBlockThread();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	maxRight = 1538/2;
    	minLeft = 0;
    	
    }
    
    public void configureScene() {
    	javafx.scene.paint.Color c = javafx.scene.paint.Color.rgb(93, 148, 251);
		mainScene.setFill(c);
		
		mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		
			@Override
			public void handle(KeyEvent e) {
				pressed.add((int) e.getText().charAt(0));

				if(pressed.size() == 1) { //press only 1 key
					
					if(pressed.contains(100)) { // d
						moveImage(1);

					}
					else if(pressed.contains(97)) { // a
						moveImage(-1);
					}else if(pressed.contains(119) && !mainGame.getLevelOne().getMario().getState().equals(Mario.ISMOVINGUP) ){
						marioThread(0);   // w 
						mainGame.getLevelOne().getMario().setState(Mario.ISMOVINGUP);
					}
				}else {   // press more than 1 key
					
					 if(pressed.contains(100) && pressed.contains(119) && !mainGame.getLevelOne().getMario().getState().equals(Mario.ISMOVINGUP)) {
						 marioThread(1); 
						 mainGame.getLevelOne().getMario().setState(Mario.ISMOVINGUP);
					 }else if(pressed.contains(97) && pressed.contains(119) && !mainGame.getLevelOne().getMario().getState().equals(Mario.ISMOVINGUP)) {
						 marioThread(-1); 
						 mainGame.getLevelOne().getMario().setState(Mario.ISMOVINGUP);
					 }
				}
			}
			});
		
		
		mainScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent ev) {
				 pressed.remove(((int) ev.getText().charAt(0)));

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
    
    public void marioThread(int keys){
    	MarioMovement mv = new MarioMovement(this, keys);
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
		if(a==1) {
    		m.setState(Mario.ISMOVINGRIGHT);
    	}else if(a==-1){
        	m.setState(Mario.ISMOVINGLEFT);
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
    
    public void loadWorld() throws IOException {
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
				rec.setFill(new ImagePattern(new Image(f.getImage())));
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
	
	
    
}
