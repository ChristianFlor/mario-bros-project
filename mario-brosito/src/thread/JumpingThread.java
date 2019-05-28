package thread;

import java.text.DecimalFormat;

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
		int h = 0;
		while(counter <=35) {
			double jump =z+1.2*counter*counter-42*counter;
			DecimalFormat df = new DecimalFormat("#.0");
			jump = Math.round(jump);
			
			if(jump == Mario.MAXHEIGHT) {
				if(h==0) {
					double rest = Mario.GROUNDPOSITION-jump;
					double ecuation = (42 - Math.sqrt((42*42)-(4*1.2*rest)))/(2*1.2);
					System.out.println(ecuation);
				}else {
					double rest = Mario.GROUNDPOSITION-jump;
					double ecuation = (42 + Math.sqrt((42*42)-(4*1.2*rest)))/(2*1.2);
					System.out.println(ecuation);
				}
				h++;
			}
			else if(jump > Mario.MAXHEIGHT) {
				double rest = Mario.GROUNDPOSITION-jump;
				double ecuation = (42 - Math.sqrt((42*42)-(4*1.2*rest)))/(2*1.2);
				System.out.println(ecuation);
			}else {
				double rest = Mario.GROUNDPOSITION-jump;
				double ecuation = (42 + Math.sqrt((42*42)-(4*1.2*rest)))/(2*1.2);
				System.out.println(ecuation);
			}
			
			System.out.println(jump+ " " + counter);
		//	double aux =z+3*counter*counter-42*counter;
			double jumpFinal = z+3*(counter+1)*(counter+1)-42*(counter+1);
			//double posMario = controller.getMainGame().getLevelOne().getMario().getPosY();
			boolean enter = false;
			
		/*	if(counter<7) {
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
		*/
			controller.moveImage(2, jump);
			counter++;
			
			if(controller.getPressed().contains("D")) {
				controller.moveImage(1,0);
				controller.getMainGame().getLevelOne().getMario().setState(Mario.ISDIAGONALRIGHT);
				
			}else if(controller.getPressed().contains("A")) {
				controller.moveImage(-1,0);
				controller.getMainGame().getLevelOne().getMario().setState(Mario.ISDIAGONALLEFT);
			}
		
			
			try {
				sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
			
				
				
		
		
		/*while(!controller.isTouching().equals(Mario.ISMOVINGDOWN) && counter!=0){
			controller.moveImage(3,0,0);
			if(controller.getPressed().contains("D")) {
				controller.moveImage(1,0,0);
				controller.getMainGame().getLevelOne().getMario().setState(Mario.ISDIAGONALRIGHT);
				
			}else if(controller.getPressed().contains("A")) {
				controller.moveImage(-1,0,0);
				controller.getMainGame().getLevelOne().getMario().setState(Mario.ISDIAGONALLEFT);
				
			}else {
				controller.getMainGame().getLevelOne().getMario().setState(Mario.ISMOVINGDOWN);
				
			}
		try {

			sleep(40);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

			counter -=10;
			//controller.getMainGame().getLevelOne().getMario().setState(Mario.ISMOVINGDOWN);
		}
*/
		controller.getMainGame().getLevelOne().getMario().setState(Mario.ISSTANDINGSTILL);
		
	}
	
	
	
	
}
