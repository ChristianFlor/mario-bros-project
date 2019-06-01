package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

public class MushroomTest {
	
	private Mushroom mushroom;
	
	private void setupScenary1() {
		
	}
	
	private void setupScenary2() {
		double posX = 32;
		double posY = 384;
		double width = 32;
		double height = 32;
		
		mushroom = new Mushroom(posX, posY, width, height);
	}

	@Test
	public void testMushroom() {
		setupScenary1();
		
		double posX = 140;
		double posY = 350;
		double width = 32;
		double height = 32;
		mushroom = new Mushroom(posX, posY, width, height);
		
		assertNotNull("The mushroom is null", mushroom);
		
	}
	
	@Test
	public void testMushroomIsGrounded() {
		setupScenary2();
		double posX = 32;
		double posY = 416;
		double width = 32;
		double height = 32;
		
		String expected = "true";
		boolean tested = mushroom.mushroomIsGrounded(posX, posY, width, height);
		
		assertTrue("The method didn't do the expected value", expected.equals(tested+""));
		
	}

}
