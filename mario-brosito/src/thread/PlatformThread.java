package thread;

import javafx.scene.shape.Rectangle;
import model.MovingPlatform;
import ui.GameController;
import javafx.application.Platform;

public class PlatformThread extends Thread{

	private GameController gameC;
	private MovingPlatform platform;
	private Rectangle platformRectangle;
	private boolean active;
	
	public PlatformThread(GameController gameC, MovingPlatform platform, Rectangle platformRectangle) {
		this.gameC = gameC;
		this.platform = platform;
		this.platformRectangle = platformRectangle;
		active = true;
	}

	@Override
	public void run() {
		while(active) {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					gameC.movePlatform(platformRectangle, platform);
				}
				
			});
			try {
				sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deactivate() {
		active = false;
		
	}
	public void activate() {
		active = true;
		new Thread(this).start();
	}
}
