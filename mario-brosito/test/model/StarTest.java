package model;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

class StarTest {
	
	private Star star;
	
	private void setupScenary1() {
		
	}
	

	@Test
	public void testStar() {
		setupScenary1();
			
		double posX = 500;
		double posY = 200;
		double width = 32;
		double height = 32;
			
		star = new Star(posX, posY, width, height); 
			
			assertNotNull("The star is null", star);

	}

}
