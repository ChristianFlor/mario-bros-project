package ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
	
	private Game mainGame;
	
	private Scene mainScene;
	private List<Rectangle> rectan;
	private ImagesLoader imlo;
	
    @FXML
    public void initialize() {
    
    	
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
    
    	
    }
    
    public void configureScene() {
    	javafx.scene.paint.Color c = javafx.scene.paint.Color.rgb(93, 148, 251);
		mainScene.setFill(c);
		
		mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		
			@Override
			public void handle(KeyEvent e) {
				if(e.getCode().equals(KeyCode.D)) {
					moveImage(1);
				}
				else if(e.getCode().equals(KeyCode.A)) {
					moveImage(-1);
				}else if(e.getCode().equals(KeyCode.W)){
					marioThread();
				}
			}
	    	
	    });
		
    }
    
    public void marioThread(){
    	MarioMovement mv = new MarioMovement(this);
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
    	if(mainMario.getLayoutX() >= maxRight && a==1) {
    		mainMario.setLayoutX(mainMario.getLayoutX()+10);
    		maxRight +=10;
    		mainBackground.relocate(mainBackground.getLayoutX()-10, mainBackground.getLayoutY());
    		//mainBackground.setTranslateX(mainBackground.getTranslateX()-10);
    	}
        //canvas.getGraphicsContext2D().clearRect(minX, minY, width, height);
    	else if(a==1)
        	mainMario.setLayoutX(mainMario.getLayoutX()+10);
        else if(a==-1){
        	mainMario.setLayoutX(mainMario.getLayoutX()-10);
        }else if(a==2){
        	mainMario.setLayoutY(mainMario.getLayoutY()-128);
        }else if(a==3){
        	mainMario.setLayoutY(mainMario.getLayoutY()+128);
        }
    	//drawImage();
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
    
}
