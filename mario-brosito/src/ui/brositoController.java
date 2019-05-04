package ui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.ImagesLoader;

public class brositoController {

	private double minX;
	private double minY;
	private double width;
	private double height;
	
	 @FXML
	 private Group group;

	@FXML
    private Canvas canvas;

	private Image main;
	
    @FXML
    public void initialize() {
		minX = 0; minY = 408; width = height = 32;
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	gc.drawImage(new Image("/uiImg/Mountain2.png"), 0, 0, 1536, 512);
    	ImagesLoader sl = null;
    	try {
			sl = new ImagesLoader(32, 32, 7, 4);
			BufferedImage[] sprites = sl.getSprites();
	    	gc = canvas.getGraphicsContext2D();
	    	double posx = 16;
	    	double posy = 16;
	    	for (int i = 0; i < sprites.length; i++) {
	    		Image card = SwingFXUtils.toFXImage(sprites[i], null);
	    		Rectangle2D r = new Rectangle2D(posx, posy, 32, 32);
	    		if(i==0) {
	    			
	    			gc.drawImage(card, 0, 408);
	    			main = card;
	    		}
	    		else
	    			gc.drawImage(card, posx, posy);
				posx += 40; 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	Image card = new Image("/uiImg/stone.png");
    	double posx = 0; double posy = 440;
    	for (int i = 0; posx < 1536; i++) {
			gc.drawImage(card, posx, posy);
			posx+=32;
		}
    	posy = 472; posx = 0;
    	for (int i = 0; posx < 1536; i++) {
			gc.drawImage(card, posx, posy);
			posx+=32;
		}
    }

    public void drawImage() {
        canvas.getGraphicsContext2D().drawImage(main, minX, minY, width, height);
    }

    public void moveImage(int a) {
    	
        canvas.getGraphicsContext2D().clearRect(minX, minY, width, height);
        if(a==1)
        	minX += 10;
        else
        	minX -= 10;
        drawImage();
    }
    
    
}
