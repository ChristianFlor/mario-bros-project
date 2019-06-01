package model;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

class ToadTest {
	
	private Toad toad;
	
	private void setupScenary1() {
		
	}

	@Test
	public void testToad() {
			setupScenary1();
			
			double posX = 400;
			double posY = 150;
			double width = 32;
			double height = 32;
			
			toad = new Toad(posX, posY, width, height); 
			
			assertNotNull("The toad was not created", toad);
	}

}
