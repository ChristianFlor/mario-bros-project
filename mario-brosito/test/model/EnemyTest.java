package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import org.junit.jupiter.api.Test;

public class EnemyTest {
	
	private Enemy enemy;
	
	public void setupScenary1() {
		enemy = new Goomba(32, 0, 32, 32);
	}
	
	public void setupScenary2() {
		
	}
	
	@Test
	public void testEnemy() {
		setupScenary2();
		Enemy enemy = new Koopa(32, 0 , 32, 32);
		
		assertNotNull("The enemy is null", enemy);
	}
	
	@Test
	public void testEnemyIsColliding() {
		setupScenary1();
		double x2 = 40;
		double y2 = 0;
		double w2 = 70;
		double h2 = 70;
		
		boolean test = enemy.enemyIsColliding(x2, y2, w2, h2);
		assertTrue("The enemy is not colliding", test);
		
	}
	
	@Test
	public void testEnemyIsColliding2() {
		setupScenary1();
		double x2 = 50;
		double y2 = 0;
		double w2 = 70;
		double h2 = 70;
		
		boolean test = enemy.enemyIsColliding(x2, y2, w2, h2);
		assertTrue("The enemy is not colliding", test);
		
	}
	
	@Test
	public void testEnemyIsGrounded() {
		setupScenary1();
		enemy.setPosY(384);
		double x2 = 32;
		double y2 = 416;
		double w2 = 32;
		double h2 = 32;
		
		boolean test = enemy.enemyIsGrounded(x2, y2, w2, h2);
		assertTrue("The enemy is not colliding", test);
		
	}

}
