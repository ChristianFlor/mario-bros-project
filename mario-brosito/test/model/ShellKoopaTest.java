package model;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

class ShellKoopaTest {
	
	private ShellKoopa shellKoopa;
	
	private void setupScenary1() {
		
	}

	@Test
	public void testShellKoopa() {
		setupScenary1();

			double posX = 500;
			double posY = 200;
			double width = 32;
			double height = 32;
			
			shellKoopa = new ShellKoopa(posX, posY, width, height); 
			
			assertNotNull("The shell koopa was not created", shellKoopa);
		}

}
