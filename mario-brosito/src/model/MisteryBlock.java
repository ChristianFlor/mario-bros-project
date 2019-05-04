package model;

public class MisteryBlock extends Obstacle implements DestructibleBrick {
	
	private Coin coin;
	private PowerUp power;

	public MisteryBlock(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}

}
