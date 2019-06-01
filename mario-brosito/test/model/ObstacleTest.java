package model;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

class ObstacleTest {
	
	private Obstacle obstacle;
	
	private void setupScenary1() {
		
	}

	@Test
	public void testObstacle() {
		setupScenary1();
		double posX = 200;
		double posY = 300;
		double width = 46;
		double height = 46;
		
		obstacle = new Obstacle(posX, posY, width, height);
		
		assertNotNull("The obstacle is null", obstacle);
	}

}
