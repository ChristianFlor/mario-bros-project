package model;

import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.Test;

class PiranhaPlantTest {
	
	private PiranhaPlant piranhaPlant;
	
	private void setupScenary1() {
		
	}

	@Test
	public void testPiranhaPlant() {
		setupScenary1();
		
		double posX = 140; 
		double posY = 200;
		double width = 32;
		double height = 32;
		
		piranhaPlant = new PiranhaPlant(posX, posY, width, height);
		assertNotNull("The piranha plant couldn't be created", piranhaPlant);
	}

}
