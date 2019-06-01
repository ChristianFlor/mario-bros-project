package model;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

class StaticFigureTest {
	
	private StaticFigure staticFig;
	
	private void setupScenary1() {
		
	}

	@Test
	public void testStaticFigure() {
		setupScenary1();
		
		double posX = 500;
		double posY = 200;
		double width = 70;
		double height = 20;
		
		staticFig = new StaticFigure(posX, posY, width, height); 
		
		assertNotNull("The static figure was not created", staticFig);
	}
}
