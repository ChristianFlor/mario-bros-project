package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import org.junit.jupiter.api.Test;

public class OneUpTest {
	
	private OneUp oneUp;
	
	private void setupScenary1() {
		
	}
	
	private void setupScenary2() {
		oneUp = new OneUp(32, 384, 32, 32);
	}

	@Test
	public void testOneUp() {
		setupScenary1();
		double posX = 100;
		double posY = 500;
		double width = 32;
		double height = 32;
		
		oneUp = new OneUp(posX, posY, width, height);
		assertNotNull("The one up was not created", oneUp);
	}
	
	@Test
	public void testOneUpIsGrounded() {
		setupScenary2();
		double posX = 32;
		double posY = 416;
		double width = 32;
		double height = 32;
		
		String expected = "true";
		boolean tested = oneUp.oneUpIsGrounded(posX, posY, width, height);
		
		assertTrue("The method didn't do the expected value", expected.equals(tested+""));
		
	}

}
