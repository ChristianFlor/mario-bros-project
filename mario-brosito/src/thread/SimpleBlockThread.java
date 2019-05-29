package thread;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import model.SimpleBlock;
import ui.GameController;

public class SimpleBlockThread extends Thread{
	
	private GameController controller;
	private SimpleBlock simpleBlock;
	private Rectangle rectangle;
	
	public SimpleBlockThread(GameController c, SimpleBlock sb, Rectangle r) {
		this.controller = c;
		this.simpleBlock = sb;
		this.rectangle = r;
	}
	
	@Override
	public void run() {
		
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						controller.marioTouchSimpleBlock(rectangle, simpleBlock);
					}
				});	
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		
	}
	
}
