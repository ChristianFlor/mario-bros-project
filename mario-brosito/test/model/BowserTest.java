package model;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

public class BowserTest {
	
	private Bowser bowser;
	
	private void setupScenary1() {
		
	}

	@Test
	public void testBowser() {
		setupScenary1();
		double posX = 430; 
		double posY = 50;
		double width = 70;
		double height = 70;
		
		bowser = new Bowser(posX, posY, width, height);
		assertNotNull("The object bowser couldn't be created", bowser);
	}

}
