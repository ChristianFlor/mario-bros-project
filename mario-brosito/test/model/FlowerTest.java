package model;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

class FlowerTest {
	
	private Flower flower;
	
	private void setupScenary1() {
		
	}

	@Test
	public void testFlower() {
		setupScenary1();
		
		double posX = 500;
		double posY = 200;
		double width = 32;
		double height = 32;
		
		flower = new Flower(posX, posY, width, height); 
		
		assertNotNull("The flower is null", flower);
	}

}
