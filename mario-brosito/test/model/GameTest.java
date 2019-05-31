package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.security.SecureRandom;

import org.junit.jupiter.api.Test;

import customExceptions.IllegalInputException;
import customExceptions.IntegerValuesException;

public class GameTest {
	
	private Game game;
	
	public void setupScenary1() {
		
	}
	
	public void setupScenary2() throws IOException, IllegalInputException, IntegerValuesException {
		game = new Game();
	}
	
	public void setupScenary3() throws IOException, IllegalInputException, IntegerValuesException {
		
		game = new Game();
		
		String name1 = "Cesar";
		String name2 = "Carlos";
		String name3 = "Leonardo";
		String name4 = "Andres";
		String name5 = "David";
		String nickname1 = "cesarin";
		String nickname2 = "carlangas";
		String nickname3 = "leopardo";
		String nickname4 = "andrew";
		String nickname5 ="reydavid";
		int score1 = 50;
		int score2 = 70;
		int score3 = 91;
		int score4 = 54;
		int score5 = 85;
		
		game.addPlayer(name1, nickname1, score1);
		game.addPlayer(name2, nickname2, score2);
		game.addPlayer(name3, nickname3, score3);
		game.addPlayer(name4, nickname4, score4);
		game.addPlayer(name5, nickname5, score5);
		
		

	}
	
	@Test
	public void testAddPlayer() {
		try {
			setupScenary1();
			try {
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
				
				game.addPlayer(name1, nickname1, score1);
				game.addPlayer(name2, nickname2, score2);
				game.addPlayer(name3, nickname3, score3);
				game.addPlayer(name4, nickname4, score4);
				game.addPlayer(name5, nickname5, score5);
				
				Player[] players = game.getPlayersToArray();
				
				assertTrue("The list is null", players.length > 0);
			} catch (IllegalInputException | IntegerValuesException e) {
				
				e.printStackTrace();
			}
			
			
			
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAddScore() {
		
		try {
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
				
				Player p1 = new Player(name1, nickname1, score1);
				Player p2 = new Player(name2, nickname2, score2);
				Player p3 = new Player(name3, nickname3, score3);
				Player p4 = new Player(name4, nickname4, score4);
				Player p5 = new Player(name5, nickname5, score5);
				
				game.addScore(p1);
				game.addScore(p2);
				game.addScore(p3);
				game.addScore(p4);
				game.addScore(p5);
				
				Score root = game.getRoot();
				assertNotNull("The root must not be null since the first element has just been added to the tree", root);
		
			} catch (IllegalInputException | IntegerValuesException e) {
				
				e.printStackTrace();
			}
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void testSortByScore() {
		try {
			setupScenary3();
			
			game.sortByScore();
			Player[] players = game.getPlayersToArray();
			
			for (int i = 0; i < players.length-1; i++) {
				assertTrue("The players are not sort by score", players[i].getScore()<players[i+1].getScore());
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (IllegalInputException e) {
			
			e.printStackTrace();
		} catch (IntegerValuesException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testSortByName() {
		try {
			setupScenary3();
			
			game.sortByName();
			Player[] players = game.getPlayersToArray();
			
			for (int i = 0; i < players.length-1; i++) {
				assertTrue("The players are not sort by name", players[i].getName().compareTo(players[i+1].getName())<=0);
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (IllegalInputException e) {
	
			e.printStackTrace();
		} catch (IntegerValuesException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testSortByNick() {
		try {
			setupScenary3();
			
			game.sortByNick();
			Player[] players = game.getPlayersToArray();
			
			for (int i = 0; i < players.length-1; i++) {
				assertTrue("The players are not sort by nickname", players[i].getNickName().compareTo(players[i+1].getNickName())<=0);
			}
		
		} catch (IOException | IllegalInputException | IntegerValuesException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testSortByCode() {
		try {
			setupScenary3();
			
			game.sortByCode();
			Player[] players = game.getPlayersToArray();
			
			for (int i = 0; i < players.length-1; i++) {
				assertTrue("The players are not sort by code", players[i].getId().compareTo(players[i+1].getId())<=0);
			}
		
		} catch (IOException | IllegalInputException | IntegerValuesException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testSearchPlayer() {
		try {
			setupScenary3();
			
			String nameToSearch1 = "Cesar";
			String nameToSearch2 = "Carlos";
			String nameToSearch3 = "Leonardo";
			String nameToSearch4 = "Andres";
			String nameToSearch5 = "David";
			
			Player searched1 = game.searchPlayer(nameToSearch1);
			Player searched2 = game.searchPlayer(nameToSearch2);
			Player searched3 = game.searchPlayer(nameToSearch3);
			Player searched4 = game.searchPlayer(nameToSearch4);
			Player searched5 = game.searchPlayer(nameToSearch5);

			assertTrue("The player was not found", searched1.getName().equals(nameToSearch1));
			assertTrue("The player was not found", searched2.getName().equals(nameToSearch2));
			assertTrue("The player was not found", searched3.getName().equals(nameToSearch3));
			assertTrue("The player was not found", searched4.getName().equals(nameToSearch4));
			assertTrue("The player was not found", searched5.getName().equals(nameToSearch5));
		
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (IllegalInputException e) {
			
			e.printStackTrace();
		} catch (IntegerValuesException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchByCode() {
		try {
			setupScenary2();
			
			String name1 = "Gloria";
			String name2 = "Susana";
			String name3 = "Nancy";
			String name4 = "Milena";
			String name5 = "Luz";
			String nickname1 = "glory1";
			String nickname2 = "suse8";
			String nickname3 = "nanlm";
			String nickname4 = "mile12";
			String nickname5 ="light";
			int score1 = 90;
			int score2 = 80;
			int score3 = 60;
			int score4 = 40;
			int score5 = 55;
			
			game.addPlayer(name1, nickname1, score1);
			game.addPlayer(name2, nickname2, score2);
			game.addPlayer(name3, nickname3, score3);
			game.addPlayer(name4, nickname4, score4);
			game.addPlayer(name5, nickname5, score5);
			
			Player[] players = game.getPlayersToArray();
			
			for (int i = 0; i < players.length; i++) {
				SecureRandom r = new SecureRandom();
				String in= players[i].getName().substring(0, 2);
				String id = in+(r.nextInt(10000));
				players[i].setId(id);
				Player searched = game.searchByCode(id);
				assertTrue("The player couldn't be found", searched.getId() == players[i].getId());
			}

		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (IllegalInputException e) {
			
			e.printStackTrace();
		} catch (IntegerValuesException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchByNick() {
		try {
			setupScenary3();
			
			String nickNameToSearch1 = "cesarin";
			String nickNameToSearch2 = "carlangas";
			String nickNameToSearch3 = "leopardo";
			String nickNameToSearch4 = "andrew";
			String nickNameToSearch5 ="reydavid";
			
			Player searched1 = game.searchByNick(nickNameToSearch1);
			Player searched2 = game.searchByNick(nickNameToSearch2);
			Player searched3 = game.searchByNick(nickNameToSearch3);
			Player searched4 = game.searchByNick(nickNameToSearch4);
			Player searched5 = game.searchByNick(nickNameToSearch5);

			assertTrue("The player was not found", searched1.getNickName().equals(nickNameToSearch1));
			assertTrue("The player was not found", searched2.getNickName().equals(nickNameToSearch2));
			assertTrue("The player was not found", searched3.getNickName().equals(nickNameToSearch3));
			assertTrue("The player was not found", searched4.getNickName().equals(nickNameToSearch4));
			assertTrue("The player was not found", searched5.getNickName().equals(nickNameToSearch5));
		
		} catch (IOException | IllegalInputException | IntegerValuesException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchByScore() {
		try {
			setupScenary3();
			
			int scoreToSearch1 = 50;
			int scoreToSearch2 = 70;
			int scoreToSearch3 = 91;
			int scoreToSearch4 = 54;
			int scoreToSearch5 = 85;

			Player searched1 = game.searchByScore(scoreToSearch1+"");
			Player searched2 = game.searchByScore(scoreToSearch2+"");
			Player searched3 = game.searchByScore(scoreToSearch3+"");
			Player searched4 = game.searchByScore(scoreToSearch4+"");
			Player searched5 = game.searchByScore(scoreToSearch5+"");

			assertTrue("The player was not found", searched1.getScore() == scoreToSearch1);
			assertTrue("The player was not found", searched2.getScore() == scoreToSearch2);
			assertTrue("The player was not found", searched3.getScore() == scoreToSearch3);
			assertTrue("The player was not found", searched4.getScore() == scoreToSearch4);
			assertTrue("The player was not found", searched5.getScore() == scoreToSearch5);
		
		} catch (IOException | IllegalInputException | IntegerValuesException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	public void testIllegalInputException() {
		try {
			setupScenary2();
			String name1 = "";
			String nickname1 = "mazn";
			int score1 = 100;
			
			
			assertThrows(IllegalInputException.class,()->{
				game.addPlayer(name1, nickname1, score1);;
			});
		} catch (IOException | IllegalInputException | IntegerValuesException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testIllegalInputException2() {
		try {
			setupScenary2();
			String name1 = "Ana";
			String nickname1 = "";
			int score1 = 100;
			
			
			assertThrows(IllegalInputException.class,()->{
				game.addPlayer(name1, nickname1, score1);;
			});
		} catch (IOException | IllegalInputException | IntegerValuesException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testIntegerValuesException() {
		try {
			setupScenary2();
			String name1 = "Ana";
			String nickname1 = 12+"";
			int score1 = 100;
			
			
			assertThrows(IntegerValuesException.class,()->{
				game.addPlayer(name1, nickname1, score1);;
			});
		} catch (IOException | IllegalInputException | IntegerValuesException e) {
			
			e.printStackTrace();
		}
		
	}
	
	



}
