package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level {
	
	/**
	 * The first enemy of the linked list of enemies.
	 */
	private Enemy firstEnemy;
	
	/**
	 * All the figures of the levels
	 */
	private List<Figure> figures;
	
	/**
	 * The position of mario in the list of figures.
	 */
	private int positionOfMario;
	
	/**
	 * The file path of the first level.
	 */
	public static final String LEVEL_ONE_PATH = "data/Level1.txt";
	
	/**
	 * The file path of the second level.
	 */
	public static final String LEVEL_TWO_PATH = "data/Level2.txt";
	
	/**
	 * The file path of the third level.
	 */
	public static final String LEVEL_THREE_PATH = "data/Level3.txt";
	
	/**
	 * <b>Description:</b>
	 * This function initializes a new Level with it's list of figures.
	 */
	public Level() {
		figures = new ArrayList<Figure>();
	}
	
	/**
	 * <b>Description:</b>
	 * This function loads a level with the specified file path.
	 * @param path The level to be loaded.
	 * @throws IOException The exception it throws if the file isn't found.
	 */
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
				//	figures.add(powerLife);
					figures.add(blockWithLife);
					
				}else if(element == 'T') {
					// tube
					Obstacle tube = new Slide(counterPosX, counterPosY, 64, 160);
					tube.setImage(Slide.BIGTUBE);
					figures.add(tube);
					
				}else if(element == 'w') {
					// tube
					Obstacle tube = new Slide(counterPosX, counterPosY, 64, 128);
					tube.setImage(Slide.MEDIUMTUBE);
					figures.add(tube);
					
				}else if(element == 't') {
					// tube
					Obstacle tube = new Slide(counterPosX, counterPosY, 64, 96);
					tube.setImage(Slide.SMALLTUBE);
					figures.add(tube);
					
				}else if(element == 'p') {
					//Surprise block with power up
					
					Obstacle blockWithPower = new MisteryBlock(counterPosX, counterPosY, 32,32, null, null);
					blockWithPower.setImage(MisteryBlock.MISTERYBLOCK);
					figures.add(blockWithPower);
					
				}else if(element == 'b') {
					// Brick block
					
					Obstacle brickBlock = new SimpleBlock(counterPosX, counterPosY, 32,32);
					if(path.equals(LEVEL_ONE_PATH))
						brickBlock.setImage(SimpleBlock.BRICK1);
					else if(path.equals(LEVEL_TWO_PATH))
						brickBlock.setImage(SimpleBlock.BRICK2);
					figures.add(brickBlock);
				}else if(element == 'B') {
				
					Figure base = new StaticFigure(counterPosX, counterPosY, 32,32);
					if(path.equals(LEVEL_ONE_PATH))
					base.setImage(StaticFigure.BASE1);
					else if(path.equals(LEVEL_TWO_PATH))
						base.setImage(StaticFigure.BASE2);
					else if(path.equals(LEVEL_THREE_PATH))
						base.setImage(StaticFigure.BASE3);
					figures.add(base);
					
				}else if(element == 'E') {
					// star block
					PowerUp starPower = new Star(counterPosX, counterPosY, 32,32);
					starPower.setImage(Star.STAR);
					Obstacle starBlock = new MisteryBlock(counterPosX, counterPosY, 32,32, null, starPower);
					starBlock.setImage(MisteryBlock.MISTERYBLOCK);
					//figures.add(starPower);
					figures.add(starBlock);
					
				}else if(element == '?') {
					// surprise block with coin
					
					Coin coin = new Coin(counterPosX, counterPosY, 32,32);
					coin.setImage(Coin.COIN);
					Obstacle coinBlock = new MisteryBlock(counterPosX, counterPosY, 32,32, coin, null);
					coinBlock.setImage(MisteryBlock.MISTERYBLOCK);
					//figures.add(coin);
					figures.add(coinBlock);
					
				}else if(element == 's') {
					// stair block
					
					Figure stairBlock = new StaticFigure(counterPosX, counterPosY, 32,32);
					stairBlock.setImage(StaticFigure.STAIR1);
					if(path.equals(LEVEL_TWO_PATH))
						stairBlock.setImage(StaticFigure.STAIR2);
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
					System.out.println(counterPosY);
					Figure castle = new StaticFigure(counterPosX, counterPosY, 160, 160);
					castle.setImage(StaticFigure.CASTLE);
					figures.add(castle);
					
				}else if(element == 'c') {
					//castle
					
					Figure castleFlag = new StaticFigure(counterPosX, counterPosY, 32, 32);
					castleFlag.setImage(StaticFigure.CASTLEFLAG);
					figures.add(castleFlag);
					
				}else if(element == 'G') {
					//goomba
					
					Enemy goomba = new Goomba(counterPosX, counterPosY, 32, 32);
					goomba.setImage(Goomba.IMAGE);
					figures.add(goomba);
					addEnemy(goomba);
				}else if(element == 'K') {
					//castle
					
					Enemy koopa = new Koopa(counterPosX, counterPosY-16, 32, 48);
					koopa.setImage(Koopa.IMAGE);
					figures.add(koopa);
					addEnemy(koopa);
				}else if(element == 'm') {
					//castle
					Figure coin = new Coin(counterPosX, counterPosY, 32, 32);
					coin.setImage(Coin.COIN);
					figures.add(coin);
				}else if(element == 'n') {
					//castle
					
					Figure platform = new StaticFigure(counterPosX, counterPosY, 32, 32);
					platform.setImage(StaticFigure.PLATFORM);
					figures.add(platform);
				}else if(element == 'A') {
					//castle
					
					Figure lava = new StaticFigure(counterPosX, counterPosY, 32, 128);
					lava.setImage(StaticFigure.LAVA);
					figures.add(lava);
				}else if(element == 'X') {
					//castle
					
					Enemy bowser = new Bowser(counterPosX, counterPosY-32, 64, 64);
					bowser.setImage(Bowser.BOWSER); 
					figures.add(bowser);
					addEnemy(bowser);
				}else if(element == 'l') {
					//castle
					Figure platform = new MovingPlatform(counterPosX, counterPosY, 16, 16);
					Figure platform2 = new MovingPlatform(counterPosX+16, counterPosY, 16, 16);
					platform.setImage(MovingPlatform.PLATFORM); 
					platform2.setImage(MovingPlatform.PLATFORM); 
					figures.add(platform);
					figures.add(platform2);
				}else if(element == 'i') {
					//castle
					Figure iron = new StaticFigure(counterPosX, counterPosY, 32, 32);
					iron.setImage(StaticFigure.IRON); 
					figures.add(iron);
				}else if(element == 'Z') {
					//castle
					Figure iron = new StaticFigure(counterPosX, counterPosY, 32, 32);
					iron.setImage(StaticFigure.IRON); 
					figures.add(iron);
					for (int j = 0; j < 6; j++) {
						Figure fire = new SimpleBlock(counterPosX+(8+(16*j)), counterPosY+8, 16, 16);
						fire.setImage(SimpleBlock.SPINNINGFIRE);
						figures.add(fire);
					}
				}
			}
			counterPosY+= 32;
			
		}
		bf.close();
	}

	/**
	 * <b>Description:</b>
	 * This function adds an enemy to the linked list of enemies.
	 * @param enemy The enemy to be added.
	 */
	public void addEnemy(Enemy enemy) {
        if(firstEnemy == null) {
            firstEnemy = enemy;
        }else {
            Enemy current = firstEnemy;
            while(current.getNextEnemy() != null) {
                current = current.getNextEnemy();
            }
            current.setNextEnemy(enemy);
        }
    }
	
    /**
     * <b>Description:</b>
     * This function obtains the mario of the level.
     * @return The mario of this level.
     */
    public Mario getMario() {
        return (Mario)figures.get(positionOfMario);
    }

    /**
     * <b>Description:</b>
     * This function obtains the list of figures of the level.
     * @return The list of figures of this level.
     */
    public List<Figure> getFigures(){
        return figures;
    }
    
    /**
     * <b>Description:</b>
     * This function obtains the list of enemies of the level.
     * @return The list of enemies of the level.
     */
    public List<Enemy> getEnemies(){
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy current = firstEnemy;
        while(current != null) {
            enemies.add(current);
            current = current.getNextEnemy();
        }
        return enemies;
    }
    
    /**
     * <b>Description:</b>
     * This function modifies the position of mario in the list of figures.
     * @param pos The position to be set.
     */
    public void setMarioPosition(int pos) {
    	this.positionOfMario = pos;
    }
}

