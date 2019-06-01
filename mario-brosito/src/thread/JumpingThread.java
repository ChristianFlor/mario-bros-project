package thread;

import javafx.application.Platform;
import model.Mario;
import ui.GameController;

public class JumpingThread extends Thread {
	
	 /**
     * The thread's game controller.
     */
	private GameController controller;
	
	/**
	 * <b>Description:</b>
	 * This function initializes a new jumping thread.
	 * @param controller The thread's game controller.
	 */
	public JumpingThread(GameController controller){
		this.controller = controller;
	}
	
	 /**
		 *<b>Description:</b>
		 *The run method which starts this thread until it dies.
		 */
	@Override
	public void run() {
		
		Mario m = null;
		if(controller.getCurrentLevel() == 1) {
    		m = controller.getMainGame().getLevelOne().getMario();
    	}else if(controller.getCurrentLevel() == 2) {
    		m = controller.getMainGame().getLevelTwo().getMario();
    	}else {
    		m = controller.getMainGame().getLevelThree().getMario();
    	}
				double z = m.getPosY();
				int counter = 0;
				
				double jump = 0;
			
				while(counter <=14) {
				
					 jump =z+3*counter*counter-42*counter;
					
					double jumpFinal = z+3*(counter+1)*(counter+1)-42*(counter+1);
					boolean enter = false;
					
					if(counter<7) {
						m.setState(Mario.ISMOVINGUP);
						
						while(!enter && m.getPosY() >= jumpFinal) {
		
							m.setPosY(m.getPosY()-1);
							if(controller.isTouching().equals(Mario.ISMOVINGUP)) {
								jump = m.getPosY();
								counter = (7-counter)+7;
								enter = true;
							}
						}
					}else if(counter<=13 && counter >=7){
						m.setState(Mario.ISMOVINGDOWN);
						while(!enter && m.getPosY() <= jumpFinal) {
							m.setPosY(m.getPosY()+1);
							
							if(controller.isTouching().equals(Mario.ISMOVINGDOWN)) {
							
								jump = m.getPosY();
							
								enter = true;
							}
						}
						
						if(enter) {
							double jump2 = m.getPosY();
							Platform.runLater(new Runnable() {

								@Override
								public void run() {
									controller.moveImage(2, jump2);
								}
							});
							break;
						}
						
					}
					double jump2 = m.getPosY();
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
						m.setState(Mario.ISDIAGONALRIGHT);
						
					}else if(controller.getPressed().contains("A")) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								controller.moveImage(-1,0);
							}
						});
						m.setState(Mario.ISDIAGONALLEFT);
					}
				
					
					try {
						sleep(60);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				
					
				}
			
				m.setState(Mario.ISSTANDINGSTILL);
		
	}
	
	
	
	
}
