package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

public class MisteryBlockTest {
	
	private MisteryBlock misteryBlock;
	
	private void setupScenary1() {
		
	}

	@Test
	public void testMisteryBlock() {
		setupScenary1();
		
		double posX = 20; 
		double posY = 410;
		double width = 32;
		double height = 32; 
		Coin coin = new Coin(20, 410, 32, 32);
		PowerUp power = new Star(30, 410, 32, 32);
		
		misteryBlock = new MisteryBlock(posX, posY, width, height, coin, power);
		
		assertNotNull("The mistery block is null", misteryBlock);
		assertTrue("The coin is null", coin == misteryBlock.getCoin());
		assertTrue("The power up is null", power == misteryBlock.getPower());
	}

}
