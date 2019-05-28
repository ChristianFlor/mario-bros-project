package model;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LevelTest {
	
	private Level level;
	
	public void setupScenary1() {
		
	}
	public void setupScenary2() {
		
		level = new Level();
		
	}
	
	@Test
	public void testLoadLevel1() {
		setupScenary2();
		String path = "data/Level1.txt";
			try {
			level.loadLevel(path);
			} catch (IOException e) {
				fail("The level wasn't loaded");
			}
			assertTrue("The list's size is not bigger than 0", level.getFigures().size()>0);
	
	}
	
	@Test
	public void testLoadLevel2() {
		setupScenary2();
		String path = "data/Level2.txt";
			try {
			level.loadLevel(path);
			} catch (IOException e) {
				fail("The level wasn't loaded");
			}
			assertTrue("The list's size is not bigger than 0", level.getFigures().size()>0);
	
	}
	
	@Test
	public void testLoadLevel3() {
		setupScenary2();
		String path = "data/Level3.txt";
			try {
			level.loadLevel(path);
			} catch (IOException e) {
				fail("The level wasn't loaded");
			}
			assertTrue("The list's size is not bigger than 0", level.getFigures().size()>0);
	}

	@Test
	public void testAddEnemy() {
		setupScenary2();
		Goomba e = new Goomba(50, 20, 30, 40);
		Goomba g = new Goomba(40, 20, 30, 40);
		Koopa k = new Koopa(60, 30, 30, 40);
		Bowser b = new Bowser(150, 20, 35, 50);
		
		level.addEnemy(e);
		level.addEnemy(g);
		level.addEnemy(k);
		level.addEnemy(b);
		
		assertNotNull("The list of enemies is null", level.getEnemies());
		
	}
	
	@Test
	public void testLevel() {
		setupScenary1();
		Figure marioFigure = new Mario(10, 30, 32, 32);
		PowerUp powerLife = new OneUp(20, 30, 32,32);
		Obstacle tube = new Slide(30, 30, 64, 64);
		Figure coin = new Coin(40, 30, 32, 32);
		Obstacle brickBlock = new SimpleBlock(50, 30, 32,32);
		Enemy goomba = new Goomba(60, 30, 32, 32);
		
		List<Figure> figures = new ArrayList<>();
		figures.add(marioFigure);
		figures.add(powerLife);
		figures.add(tube);
		figures.add(coin);
		figures.add(brickBlock);
		figures.add(goomba);
		
		Level l = new Level();
		
		assertNotNull("The list of enemies is null", l.getFigures());
		
	}
	

}