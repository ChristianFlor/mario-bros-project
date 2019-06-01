package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class MarioTest {
	
	private Mario mario;
	
	private void setupScenary1() {
		double posX = 32; 
		double posY = 0;
		double width = 32;
		double height = 32;
		mario = new Mario(posX, posY, width, height);
	}
	
	private void setupScenary2() {

	}
	
	@Test
	public void testMario() {
		setupScenary2();
		double posX = 30; 
		double posY = 410;
		double width = 32;
		double height = 32;
		
		mario = new Mario(posX, posY, width, height);
		assertNotNull("Mario is null", mario);
		
	}
	
	@Test 
	public void testIsCollidingDown() {
		setupScenary1();
		String out = Mario.ISMOVINGDOWN;
		double x2 = 32;
		double y2 = 32;
		double w2 = 70;
		double h2 = 70;
	
		String test = mario.isColliding(x2, y2, w2, h2);
		assertTrue("The state of Mario is not clear", test.equals(out));
		
	}
	
	@Test 
	public void testIsCollidingRight() {
		setupScenary1();
		String out = Mario.ISMOVINGRIGHT;
		double x2 = 40;
		double y2 = 0;
		double w2 = 70;
		double h2 = 70;
	
		String test = mario.isColliding(x2, y2, w2, h2);
		assertTrue("The state of Mario is not clear", test.equals(out));
		
	}
	
	@Test 
	public void testIsCollidingLeft() {
		setupScenary1();
		String out = Mario.ISMOVINGLEFT;
		double x2 = 20;
		double y2 = 0;
		double w2 = 70;
		double h2 = 70;
	
		String test = mario.isColliding(x2, y2, w2, h2);
		assertTrue("The state of Mario is not clear", test.equals(out));
		
	}
	
	@Test 
	public void testIsCollidingUp() {
		setupScenary1();
		String out = Mario.ISMOVINGUP;
		mario.setPosY(32);
		double x2 = 32;
		double y2 = 0;
		double w2 = 70;
		double h2 = 32;
	
		String test = mario.isColliding(x2, y2, w2, h2);
		assertTrue("The state of Mario is not clear", test.equals(out));
		
	}
	
	@Test
	public void testIsGroundedFloor() {
		setupScenary1();
		String out = "floor";
		mario.setPosY(384);
		double x2 = 32;
		double y2 = 416;
		double w2 = 32;
		double h2 = 32;
		
		String test = mario.isGrounded(x2, y2, w2, h2);
		assertTrue("The state of Mario is not clear", test.equals(out));
	}
	
	@Test
	public void testIsGroundedIntersect() {
		setupScenary1();
		String out = "intersect";
		double x2 = 32;
		double y2 = 32;
		double w2 = 70;
		double h2 = 70;
		
		String test = mario.isGrounded(x2, y2, w2, h2);
		assertTrue("The state of Mario is not clear", test.equals(out));
	}
	
	@Test
	public void testIsThereSomethingInBetween() {
		setupScenary1();
		
		double x2 = 64;
		double y2 = 70;
		double w2 = 70;
		double h2 = 32;
		double yFinal = 70;
		double yActual = 102;
		
		boolean test = mario.isThereSomethingInBetween(x2, y2, w2, h2, yFinal, yActual);
		assertTrue("There is nothing in between Mario", test);
		
	}
	
	@Test
	public void testNextPowerUp() {
		setupScenary1();
		
		PowerUp powerToTest = mario.nextPowerUp();
		
		assertTrue("The power ups are not the same", powerToTest instanceof Mushroom);
	}
	
	@Test
	public void testNextPowerUp2() {
		setupScenary1();
		
		PowerUp powerExpected = new Mushroom(mario.getPosX(), mario.getPosY(), mario.getWidth(), mario.getHeight());
		mario.setPowerState(powerExpected);
		PowerUp powerToTest = mario.nextPowerUp();
		
		assertTrue("The power ups are not the same", powerToTest instanceof Flower);
	
	}
	
	@Test
	public void testIsEnemyNear() {
		setupScenary1();
		double posX = 100;
		
		boolean test = mario.isEnemyNear(posX);
		assertTrue("There is nothing in between Mario", test);
	}

}
