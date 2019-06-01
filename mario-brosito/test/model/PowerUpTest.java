package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class PowerUpTest {
	
	private PowerUp powerUp;
	
	private void setupScenary1() {
		
	}
	
	private void setupScenary2() {
		powerUp = new Star(32, 0, 32, 32);
	}


	@Test
	public void testPowerUp() {
		setupScenary1();
		double posX = 140;
		double posY = 240;
		double width = 32;
		double height = 32;
		
		powerUp = new Star(posX, posY, width, height);
		assertNotNull("The power up was not created", powerUp);
	}
	
	@Test
	public void testPowerUpIsGrounded() {
		setupScenary2();
		double posX = 40;
		double posY = 0;
		double width = 70;
		double height = 70;
		
		String expected = "true";
		boolean tested = powerUp.powerUpIsColliding(posX, posY, width, height);
		
		assertTrue("The method didn't do the expected value", expected.equals(tested+""));
		
	}

}
