package model;

import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.Test;

public class KoopaTest {
	
	private Koopa koopa;
	
	private void setupScenary1() {
		
	}
	

	@Test
	public void testKoopa() {
		setupScenary1();
		double posX = 200;
		double posY = 50;
		double width = 32;
		double height = 32;
		
		koopa = new Koopa(posX, posY, width, height);
		assertNotNull("The koopa is null", koopa);
	}

}
