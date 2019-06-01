package model;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

public class CoinTest {
	
	private Coin coin;
	
	private void setupScenary1() {
		
	}
	
	@Test
	public void testCoin() {
		setupScenary1();
		
		double posX = 100;
		double posY = 30;
		double width = 32;
		double height = 32;
		
		coin = new Coin(posX, posY, width, height);
		assertNotNull("The coin is null", coin);
		
	}

}
