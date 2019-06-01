package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class ScoreTest {
	
	private Score score;
	
	private void setupScenary1() {
		
	}

	@Test
	public void testScore() {
		setupScenary1();
		String name = "Cesar";
		int sco = 2400;
		
		score = new Score(name, sco);
		assertNotNull("The score couldn't be created", score);
		assertTrue("The name is not the same of the object", name.equals(score.getName()));
		assertTrue("The score is not the same for the object created", sco == score.getScore());
	}

}
