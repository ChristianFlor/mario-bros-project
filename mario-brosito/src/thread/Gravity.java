package thread;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import model.Figure;
import ui.GameController;

public class Gravity extends Thread{
    
    private GameController controller;
    private Rectangle figureRec;
    private Figure figure;
    
    public Gravity(GameController controller, Rectangle figureRec, Figure figure) {
        this.controller = controller;
        this.figureRec = figureRec;
        this.figure = figure;
    }

    public void run() {
        while(!controller.isEnemyFalling(figure, figureRec)) {
        	Platform.runLater(new Runnable() {
				@Override
				public void run() {
					controller.makeFigureFall(figureRec, figure);
				}
        	});
        	try {
        		sleep(150);
        	} catch(InterruptedException e) {
        		e.printStackTrace();
        	}
            
        }
    }
}