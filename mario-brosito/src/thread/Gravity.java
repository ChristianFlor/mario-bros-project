package thread;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import model.Figure;
import ui.GameController;

public class Gravity extends Thread{
    
    /**
     * The thread's game controller.
     */
    private GameController controller;
    
    /**
     * The thread's rectangle from the user interface.
     */
    private Rectangle figureRec;
    
    /**
     * The thread's figure that is to be fell.
     */
    private Figure figure;
    
    /**
     * <b>Description:</b>
     * This function initializes a new gravity thread.
     * @param controller The thread's game controller.
     * @param figureRec The thread's rectangle from the user interface.
     * @param figure The thread's figure that is to be fell.
     */
    public Gravity(GameController controller, Rectangle figureRec, Figure figure) {
        this.controller = controller;
        this.figureRec = figureRec;
        this.figure = figure;
    }

    /**
	 *<b>Description:</b>
	 *The run method which starts this thread until it dies.
	 */
    public void run() {
        while(!controller.isFigureFalling(figure, figureRec)) {
        	Platform.runLater(new Runnable() {
				@Override
				public void run() {
					controller.makeFigureFall(figureRec, figure);
				}
        	});
        	if(figure.getPosY()> 550)
        		break;
        	try {
        		sleep(150);
        	} catch(InterruptedException e) {
        		e.printStackTrace();
        	}
            
        }
    }
}