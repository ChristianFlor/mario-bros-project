package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Level {
	
	private Enemy firstEnemy;
	
	private List<Figure> figures;
	
	
	private static final String LEVEL_ONE_PATH = "data/Level1.txt";
	private static final String LEVEL_TWO_PATH = "data/Level2.txt";
	private static final String LEVEL_THREE_PATH = "data/Level3.txt";
	
	public void loadLevel(String path) throws IOException {
		
		BufferedReader bf = new BufferedReader(new FileReader(path));
		
		String n1 = bf.readLine();
		String n2 = bf.readLine();
		String n3 = bf.readLine();
		
		String line;
		int counterPosY = 0;
		while((line = bf.readLine()) != null) {
			
			for (int i = 0; i < line.length(); i++) {
				char element = line.charAt(i);
				int counterPosX = i*32;
				
				if(element == 'H') {
					
				}else if(element == 'T') {
					
				}else if(element == 'p') {
					
					Coin coin = new Coin(counterPosX, counterPosY-32, 32,32);
					MisteryBlock blockWhitCoin = new MisteryBlock(counterPosX, counterPosY, 32,32, coin, null );
					
				}else if(element == 'b') {
					SimpleBlock brickBlock = new SimpleBlock(counterPosX, counterPosY, 32,32);
				}else if(element == 'B') {
					
				}else if(element == 'E') {
					
				}else if(element == '?') {
					
				}else if(element == 'S') {
					
				}else if(element == 'f') {
					
				}else if(element == 'F') {
					
				}else if(element == 'g') {
					
				}else if(element == 'C') {
					
				}
					
					
					
				
			}
			counterPosY+= 32;
			
		}
		
		
		
	}
}
