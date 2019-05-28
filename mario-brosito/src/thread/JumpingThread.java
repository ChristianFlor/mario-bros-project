package thread;

import javafx.application.Platform;
import model.Figure;
import model.Mario;
import ui.GameController;

public class JumpingThread extends Thread {
	
	private GameController controller;
	
	public JumpingThread(GameController controller){
		this.controller = controller;

	}
	@Override
	public void run() {
		
		int counter = 0;
		double z = controller.getMainGame().getLevelOne().getMario().getPosY();
	
		while(counter <=14) {
			double jump =z+3*counter*counter-42*counter;
			double jumpFinal = z+3*(counter+1)*(counter+1)-42*(counter+1);
			
			boolean enter = false;
			
			if(counter<7) {
				while(!enter && controller.getMainGame().getLevelOne().getMario().getPosY() != jumpFinal) {
					controller.getMainGame().getLevelOne().getMario().setPosY(controller.getMainGame().getLevelOne().getMario().getPosY()-1);
					if(controller.isTouching().equals(Mario.ISMOVINGUP)) {
						jump = controller.getMainGame().getLevelOne().getMario().getPosY();
						counter = (7-counter)+7;
						enter = true;
					}
				}
			}else {
				
				while(!enter && controller.getMainGame().getLevelOne().getMario().getPosY() != jumpFinal) {
					controller.getMainGame().getLevelOne().getMario().setPosY(controller.getMainGame().getLevelOne().getMario().getPosY()+1);
					
					if(controller.isTouching().equals(Mario.ISMOVINGDOWN)) {
					
						jump = controller.getMainGame().getLevelOne().getMario().getPosY();
						counter = (7-counter)+7;
						enter = true;
					}
				}
				
				if(enter) {
					controller.moveImage(2, jump);
					//counter++;
					break;
				}
				
			}
			try {
				sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			controller.moveImage(2, jump);
			counter++;
			
			if(controller.getPressed().contains("D")) {
				controller.moveImage(1,0);
				controller.getMainGame().getLevelOne().getMario().setState(Mario.ISDIAGONALRIGHT);
				
			}else if(controller.getPressed().contains("A")) {
				controller.moveImage(-1,0);
				controller.getMainGame().getLevelOne().getMario().setState(Mario.ISDIAGONALLEFT);
			}
		
			
			
			
		}
	
		controller.getMainGame().getLevelOne().getMario().setState(Mario.ISSTANDINGSTILL);
		
	}
	
	
	
	
}
