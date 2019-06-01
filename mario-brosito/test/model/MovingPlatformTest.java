package model;

import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.Test;

class MovingPlatformTest {
	
	private MovingPlatform movingPlatform;
	
	private void setupScenary1() {
		
	}

	@Test
	public void testMovingPlatform() {
		setupScenary1();
		double posX = 500; 
		double posY = 400;
		double width = 17;
		double height = 17;
		
		movingPlatform = new MovingPlatform(posX, posY, width, height);
		assertNotNull("The moving platform is null", movingPlatform);
		
	}

}
