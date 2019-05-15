package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level {
	
	//private Enemy firstEnemy;
	
	private List<Figure> figures;
	
	private int positionOfMario;
	
	public static final String LEVEL_ONE_PATH = "data/Level1.txt";
	public static final String LEVEL_TWO_PATH = "data/Level2.txt";
	public static final String LEVEL_THREE_PATH = "data/Level3.txt";
	
	public Level() {
		figures = new ArrayList<Figure>();
	}
	
	public void loadLevel(String path) throws IOException {
		
		BufferedReader bf = new BufferedReader(new FileReader(path));
		
		bf.readLine();
		bf.readLine();
		bf.readLine();
		
		String line;
		int counterPosY = 0;
		while((line = bf.readLine()) != null && !line.isEmpty()) {
			
			for (int i = 0; i < line.length(); i++) {
				char element = line.charAt(i);
				int counterPosX = i*32;
				if(element == '0')
					continue;
				if(element == 'M') {
					
					Figure marioFigure = new Mario(counterPosX, counterPosY, 32, 32);
					marioFigure.setImage(Mario.IMAGE);
					figures.add(marioFigure);
					positionOfMario = figures.size()-1;
				}else if(element == 'H') {
					
					// hidden block
					
					PowerUp powerLife = new OneUp(counterPosX, counterPosY, 32,32);
					powerLife.setImage(OneUp.ONEUP);
					Obstacle blockWithLife = new MisteryBlock(counterPosX, counterPosY, 32,32, null, powerLife );
					blockWithLife.setImage(MisteryBlock.MISTERYBLOCK);
					figures.add(powerLife);
					figures.add(blockWithLife);
					
				}else if(element == 'T') {
					// tube
					Obstacle tube = new Slide(counterPosX, counterPosY, 64, 64);
					tube.setImage(Slide.TOPTUBE);
					figures.add(tube);
					
				}else if(element == 't') {
					// tube
					Figure tube = new StaticFigure(counterPosX, counterPosY, 64, 32);
					tube.setImage(Slide.BOTTOMTUBE);
					figures.add(tube);
					
				}else if(element == 'p') {
					//Surprise block with power up
					
					Obstacle blockWithPower = new MisteryBlock(counterPosX, counterPosY, 32,32, null, null);
					blockWithPower.setImage(MisteryBlock.MISTERYBLOCK);
					figures.add(blockWithPower);
					
				}else if(element == 'b') {
					// Brick block
					
					Obstacle brickBlock = new SimpleBlock(counterPosX, counterPosY, 32,32);
					brickBlock.setImage(SimpleBlock.BRICK);
					figures.add(brickBlock);
				}else if(element == 'B') {
				
					Figure base = new StaticFigure(counterPosX, counterPosY, 32,32);
					base.setImage(StaticFigure.BASE);
					figures.add(base);
					
				}else if(element == 'E') {
					// star block
					PowerUp starPower = new Star(counterPosX, counterPosY, 32,32);
					starPower.setImage(Star.STAR);
					Obstacle starBlock = new MisteryBlock(counterPosX, counterPosY, 32,32, null, starPower);
					starBlock.setImage(MisteryBlock.MISTERYBLOCK);
					figures.add(starPower);
					figures.add(starBlock);
					
				}else if(element == '?') {
					// surprise block with coin
					
					Coin coin = new Coin(counterPosX, counterPosY, 32,32);
					coin.setImage(Coin.COIN);
					Obstacle coinBlock = new MisteryBlock(counterPosX, counterPosY, 32,32, coin, null);
					coinBlock.setImage(MisteryBlock.MISTERYBLOCK);
					figures.add(coin);
					figures.add(coinBlock);
					
				}else if(element == 's') {
					// stair block
					
					Figure stairBlock = new StaticFigure(counterPosX, counterPosY, 32,32);
					stairBlock.setImage(StaticFigure.STAIR);
					figures.add(stairBlock);
					
				}else if(element == 'f') {
					//  flag pole
					
					Figure flagPole = new StaticFigure(counterPosX + 14, counterPosY, 4,288);
					flagPole.setImage(StaticFigure.FLAGPOLE);
					figures.add(flagPole);
					
				}else if(element == 'F') {
					// flag
					
					Figure flag = new StaticFigure(counterPosX-14, counterPosY, 32,32);
					flag.setImage(StaticFigure.FLAGTOP);
					figures.add(flag);
					
				}else if(element == 'g') {
					//green circle up of the flag
					
					Figure circle = new StaticFigure(counterPosX, counterPosY, 32,32);
					circle.setImage(StaticFigure.FLAGSPHERE);
					figures.add(circle);
					
					
				}else if(element == 'C') {
					//castle
					
					Figure castle = new StaticFigure(counterPosX, counterPosY, 160, 160);
					castle.setImage(StaticFigure.CASTLE);
					figures.add(castle);
					
				}else if(element == 'c') {
					//castle
					
					Figure castleFlag = new StaticFigure(counterPosX, counterPosY, 32, 32);
					castleFlag.setImage(StaticFigure.CASTLEFLAG);
					figures.add(castleFlag);
					
				}
			}
			counterPosY+= 32;
			
		}
		bf.close();
	}
	public Mario getMario() {

		return (Mario)figures.get(positionOfMario);
	}

	public List<Figure> getFigures(){
		return figures;
	}
}
