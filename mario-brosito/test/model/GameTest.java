package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class GameTest {
	
	private Game game;
	
	public void setupScenary1() throws IOException {
		game = new Game();
		String name1 = "Ana";
		String name2 = "Gabriel";
		String name3 = "Sara";
		String name4 = "Sol";
		String name5 = "Jairo";
		String nickname1 = "ani11";
		String nickname2 = "gabomarc";
		String nickname3 = "samn";
		String nickname4 = "sunshine";
		String nickname5 ="jardz";
		int score1 = 80;
		int score2 = 40;
		int score3 = 50;
		int score4 = 95;
		int score5 = 110;
		
		game.addScore(name1, nickname1, score1);
		game.addScore(name2, nickname2, score2);
		game.addScore(name3, nickname3, score3);
		game.addScore(name4, nickname4, score4);
		game.addScore(name5, nickname5, score5);
		
	}
	
	public void setupScenary2() throws IOException {
		game = new Game();
	}
	
	@Test
	public void testAddScore() {
		
		try {
			setupScenary2();
			String name1 = "Javi";
			String name2 = "Luis";
			String name3 = "Isabella";
			String name4 = "William";
			String name5 = "Sofia";
			String nickname1 = "lara98";
			String nickname2 = "luisk4";
			String nickname3 = "isagrwm";
			String nickname4 = "willav";
			String nickname5 ="sofy24";
			int score1 = 90;
			int score2 = 100;
			int score3 = 80;
			int score4 = 30;
			int score5 = 58;
			
			game.addScore(name1, nickname1, score1);
			game.addScore(name2, nickname2, score2);
			game.addScore(name3, nickname3, score3);
			game.addScore(name4, nickname4, score4);
			game.addScore(name5, nickname5, score5);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void testSearchScore() {
		try {
			setupScenary1();
			String id = "sunshine";
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	


}
