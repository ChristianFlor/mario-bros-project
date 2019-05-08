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
				
				if(element == 'M') {
					
					Figure marioFigure = new Mario(counterPosX, counterPosY, 32, 32);
					figures.add(marioFigure);
					
				}else if(element == 'H') {
					
					// hidden block
					
					PowerUp powerLife = new OneUp(counterPosX, counterPosY, 32,32);
					Obstacle blockWithLife = new MisteryBlock(counterPosX, counterPosY, 32,32, null, powerLife );
					figures.add(powerLife);
					figures.add(blockWithLife);
					
				}else if(element == 'T') {
					// tube
					Obstacle tube = new Slide(counterPosX, counterPosY, 32, 32);
					figures.add(tube);
					
				}else if(element == 'p') {
					//Surprise block with power up
					
					Obstacle blockWithPower = new MisteryBlock(counterPosX, counterPosY, 32,32, null, null);
					figures.add(blockWithPower);
					
				}else if(element == 'b') {
					// Brick block
					
					Obstacle brickBlock = new SimpleBlock(counterPosX, counterPosY, 32,32);
					figures.add(brickBlock);
				}else if(element == 'B') {
					// Base block
				
					Figure base = new StaticFigure(counterPosX, counterPosY, 32,32);
					figures.add(base);
					
				}else if(element == 'E') {
					// star block
					PowerUp starPower = new Star(counterPosX, counterPosY, 32,32);
					Obstacle starBlock = new MisteryBlock(counterPosX, counterPosY, 32,32, null, starPower);
					figures.add(starPower);
					figures.add(starBlock);
					
				}else if(element == '?') {
					// surprise block with coin
					
					Coin coin = new Coin(counterPosX, counterPosY, 32,32);
					Obstacle coinBlock = new MisteryBlock(counterPosX, counterPosY, 32,32, coin, null);
					figures.add(coin);
					figures.add(coinBlock);
					
				}else if(element == 'S') {
					// stair block
					
					Figure stairBlock = new StaticFigure(counterPosX, counterPosY, 32,32);
					figures.add(stairBlock);
					
				}else if(element == 'f') {
					//  flag pole
					
					Figure flagPole = new StaticFigure(counterPosX, counterPosY, 32,32);
					figures.add(flagPole);
					
				}else if(element == 'F') {
					// flag
					
					Figure flag = new StaticFigure(counterPosX, counterPosY, 32,32);
					figures.add(flag);
					
				}else if(element == 'g') {
					//green circle up of the flag
					
					Figure circle = new StaticFigure(counterPosX, counterPosY, 32,32);
					figures.add(circle);
					
					
				}else if(element == 'C') {
					//castle
					
					Figure castle = new StaticFigure(counterPosX, counterPosY, 32,32);
					figures.add(castle);
					
				}
			}
			counterPosY+= 32;
			
		}
		
	}
	
	
}
