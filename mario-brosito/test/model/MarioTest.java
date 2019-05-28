package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MarioTest {
	
	private Mario mario;
	
	public void setupScenary1() {
		double posX = 32; 
		double posY = 0;
		double width = 32;
		double height = 32;
		mario = new Mario(posX, posY, width, height);
	}
	
	public void setupScenary2() {

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

}
