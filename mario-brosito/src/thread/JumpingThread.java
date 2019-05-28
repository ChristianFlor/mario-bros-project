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
				double jump = 0;
			
				while(counter <=14) {
				
					 jump =z+3*counter*counter-42*counter;
					
					double jumpFinal = z+3*(counter+1)*(counter+1)-42*(counter+1);
					boolean enter = false;
					
					if(counter<7) {
						controller.getMainGame().getLevelOne().getMario().setState(Mario.ISMOVINGUP);
						
						while(!enter && controller.getMainGame().getLevelOne().getMario().getPosY() >= jumpFinal) {
		
							controller.getMainGame().getLevelOne().getMario().setPosY(controller.getMainGame().getLevelOne().getMario().getPosY()-1);
							if(controller.isTouching().equals(Mario.ISMOVINGUP)) {
								jump = controller.getMainGame().getLevelOne().getMario().getPosY();
								counter = (7-counter)+7;
								enter = true;
							}
						}
					}else if(counter<=13 && counter >=7){
						controller.getMainGame().getLevelOne().getMario().setState(Mario.ISMOVINGDOWN);
						while(!enter && controller.getMainGame().getLevelOne().getMario().getPosY() <= jumpFinal) {
							controller.getMainGame().getLevelOne().getMario().setPosY(controller.getMainGame().getLevelOne().getMario().getPosY()+1);
							
							if(controller.isTouching().equals(Mario.ISMOVINGDOWN)) {
							
								jump = controller.getMainGame().getLevelOne().getMario().getPosY();
							
								enter = true;
							}
						}
						
						if(enter) {
							double jump2 = controller.getMainGame().getLevelOne().getMario().getPosY();
							Platform.runLater(new Runnable() {

								@Override
								public void run() {
									controller.moveImage(2, jump2);
								}
							});
							break;
						}
						
					}
					double jump2 = controller.getMainGame().getLevelOne().getMario().getPosY();
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								controller.moveImage(2, jump2);
							}
							
						});
								counter++;
						
					
					if(controller.getPressed().contains("D")) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								controller.moveImage(1,0);
							}
						});
						controller.getMainGame().getLevelOne().getMario().setState(Mario.ISDIAGONALRIGHT);
						
					}else if(controller.getPressed().contains("A")) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								controller.moveImage(-1,0);
							}
						});
						controller.getMainGame().getLevelOne().getMario().setState(Mario.ISDIAGONALLEFT);
					}
				
					
					try {
						sleep(60);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					
				}
			
				controller.getMainGame().getLevelOne().getMario().setState(Mario.ISSTANDINGSTILL);
				
	}
	
	
	
	
}
