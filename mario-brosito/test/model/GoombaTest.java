package model;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

public class GoombaTest {
	
	private Goomba goomba;
	
	private void setupScenary1() {
		
	}
	
	@Test
	public void testGoomba() {
		setupScenary1();
		
		double posX = 200;
		double posY = 418;
		double width = 32;
		double height = 32;
		
		goomba = new Goomba(posX, posY, width, height);
		
		assertNotNull("The goomba is null", goomba);
	}

}
