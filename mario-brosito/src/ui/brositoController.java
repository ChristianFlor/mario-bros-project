package ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
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

public class brositoController {

	/*@FXML
    private Canvas canvas;*/

	@FXML
    private Pane mainBackground;
	
	private Rectangle mainMario;
	
	private int maxRight;
	
	private Game mainGame;
	
	private Scene mainScene;
	
    @FXML
    public void initialize() {
    
    	
    	try {
			mainGame = new Game();
			loadWorld();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	maxRight = 1538/2;
    	
    	
    	javafx.scene.paint.Color c = javafx.scene.paint.Color.rgb(93, 148, 251);
		mainScene.setFill(c);
	//	scene.setFill(new ImagePattern(new Image("uiImg/Mountain.png",1538,448,false,false))); name = new (); 
		mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				if(e.getCode().equals(KeyCode.D)) {
					moveImage(1);
				}
				else if(e.getCode().equals(KeyCode.A)) {
					moveImage(-1);
				}else if(e.getCode().equals(KeyCode.W)){
					moveImage(2);
				}
			}
	    	
	    });
		
    	/*Image im = new Image("uiImg/background/back1.jpg",7168,448,true,true);
    	BackgroundImage myBI= new BackgroundImage(im,
    	        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
    	          BackgroundSize.DEFAULT);
    	mainBackground.setBackground(new Background(myBI));
    	
    	/*
    	//mainBackground.setTranslateX(-3000);
		minX = 0; minY = 408; width = height = 32;
    	//GraphicsContext gc = canvas.getGraphicsContext2D();
    	//gc.drawImage(new Image("/uiImg/Mountain2.png"), 0, 0, 1536, 512);
    	ImagesLoader sl = null;
    	try {
			sl = new ImagesLoader(32, 32, 7, 4);
			BufferedImage[] sprites = sl.getSprites();
	    	//gc = canvas.getGraphicsContext2D();
	    	double posx = 16;
	    	double posy = 16;
	    	for (int i = 0; i < sprites.length; i++) {
	    		Image card = SwingFXUtils.toFXImage(sprites[i], null);
	    		//Rectangle2D r = new Rectangle2D(posx, posy, 32, 32);
	    		
	    		if(i==0) {
	    			Rectangle rec = new Rectangle(0, 384, 32, 32);
	    			rec.setFill(new ImagePattern(card));
	    			mainBackground.getChildren().add(rec);
	    			//gc.drawImage(card, 0, 408);
	    			mainMario = rec;
	    		}
	    		else {
	    			Rectangle rec = new Rectangle(posx, posy, 32, 32);
	    			rec.setFill(new ImagePattern(card));
	    			mainBackground.getChildren().add(rec);
	    		}
	    			//gc.drawImage(card, posx, posy);
				posx += 40; 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	Image card = new Image("/uiImg/stone.png");
    	double posx = 0; double posy = 416;
    	for (int i = 0; posx < 7168; i++) {
    		Rectangle rec = new Rectangle(posx, posy, 32, 32);
			rec.setFill(new ImagePattern(card));
			mainBackground.getChildren().add(rec);
			//gc.drawImage(card, posx, posy);
			posx+=32;
		}
    	posy = 448; posx = 0;
    	for (int i = 0; posx < 7168; i++) {
    		Rectangle rec = new Rectangle(posx, posy, 32, 32);
			rec.setFill(new ImagePattern(card));
			mainBackground.getChildren().add(rec);
			//gc.drawImage(card, posx, posy);
			posx+=32;
		}
    	Rectangle rec = new Rectangle(7000, 32, 32, 32);
		rec.setFill(new ImagePattern(card));
		mainBackground.getChildren().add(rec);*/
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
        }//drawImage();
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
