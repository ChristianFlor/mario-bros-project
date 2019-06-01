package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

class PlayerTest {
	
	private Player player;
	
	private void setupScenary1() {
		
	}

	@Test
	public void testPlayer() {
		setupScenary1();
		
		String name = "Miguel";
		String nick = "angel";
		int score = 1000;
		
		player = new Player(name, nick, score);
		assertNotNull("The player was not created", player);
		assertTrue("The name is not the same for the player created", name.equals(player.getName()));
		assertTrue("The nick is not the same for the player created", nick.equals(player.getNickName()));
		assertTrue("The score is not the same for the player created", score == player.getScore());
	}

}
